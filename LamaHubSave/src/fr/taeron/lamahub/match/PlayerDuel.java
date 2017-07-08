package fr.taeron.lamahub.match;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.taeron.core.kits.Kit;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.match.arena.Arena;
import fr.taeron.lamahub.user.LamaUser;

public class PlayerDuel {

	private Player player1;
	private Player player2;
	private long startMillis;
	private Arena arena;
	private Kit kit;
	private List<Player> participating;
	private boolean ranked;
	private Queue queue;
	
	@SuppressWarnings("deprecation")
	public PlayerDuel(Queue queue, Player first, Player second, Arena a, Kit kit, boolean ranked){
		for(Player p : Bukkit.getOnlinePlayers()){
			p.hidePlayer(first, false);
			p.hidePlayer(second, false);
		}
		this.player1 = first;
		this.player2 = second;
		this.arena = a;
		this.kit = kit;
		this.startMillis = System.currentTimeMillis() + 6000;
		this.participating = new ArrayList<Player>();
		this.participating.add(player1);
		this.participating.add(player2);
		this.ranked = ranked;
		this.queue = queue;
		LamaHub.getInstance().getUserManager().getUser(first.getUniqueId()).setCurrentDuel(this);
		LamaHub.getInstance().getUserManager().getUser(second.getUniqueId()).setCurrentDuel(this);
		this.start();
	}
	
	public void clear(){
		this.player1 = null;
		this.player2 = null;
		this.arena = null;
		this.kit = null;
		this.participating.clear();
		this.queue = null;
	}
	
	public long getElapsedTime(){
		return System.currentTimeMillis() - this.startMillis;
	}
	
	public ArrayList<Player> getParticipating(){
		return (ArrayList<Player>) this.participating;
	}
	
	public boolean isRanked(){
		return this.ranked;
	}
	
	public Queue getQueue(){
		return this.queue;
	}
	
	public Player getPrimaryPlayer(){
		return this.player1;
	}
	
	public Player getSecondaryPlayer(){
		return this.player2;
	}
	
	public long getDuration(){
		return System.currentTimeMillis() - this.startMillis;
	}
	
	public Arena getArena(){
		return this.arena;
	}
	
	public Kit getKit(){
		return this.kit;
	}
	
	public Player getOpponent(Player p){
		if(p == this.player1){
			return this.player2;
		} else {
			return this.player1;
		}
	}
	
	public boolean hasStarted(){
		return this.startMillis < System.currentTimeMillis();
	}
	
	public void start(){
		for(Player p : this.participating){
			if(p.equals(this.getPrimaryPlayer())){
				p.teleport(this.arena.getPoint1());
			} else {
				p.teleport(this.arena.getPoint2());
			}
		}
		new BukkitRunnable(){
			int dms = 5;
			@Override
			public void run() {
				if (dms > 0) {
					for (final Player player : PlayerDuel.this.participating) {
						player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
						player.sendMessage(ChatColor.YELLOW + "Le duel commence dans " + ChatColor.GOLD + dms + ChatColor.YELLOW + " secondes");     
					}
					--dms;
				} else if(dms == 0){
					PlayerDuel.this.startMillis = System.currentTimeMillis();
					for (final Player player2 : PlayerDuel.this.participating) {
						player2.sendMessage(ChatColor.YELLOW + "Le duel commence !");
						player2.playSound(player2.getLocation(), Sound.FIREWORK_LARGE_BLAST, 1.0f, 1.0f);
						player2.setSaturation(4.0f);
					}
					--dms;
				} else {
					return;
	            }				
			}
		}.runTaskTimerAsynchronously(LamaHub.getInstance(), 20, 20);
    }
	
	public void end(Player winner){
		for(Player p : this.participating){
			p.sendMessage("§2Gagnant: §a" + winner.getName());
			LamaUser user = LamaHub.getInstance().getUserManager().getUser(p.getUniqueId());
			user.setCurrentDuel(null);
		}
		new BukkitRunnable(){
			public void run(){
				PlayerDuel.this.getOpponent(winner).spigot().respawn();
				LamaHub.getInstance().getInventoryHandler().duelLobbyInventory.applyTo(winner, true, true);
				LamaHub.getInstance().getInventoryHandler().duelLobbyInventory.applyTo(PlayerDuel.this.getOpponent(winner), true, true);
				if(PlayerDuel.this.isRanked()){
					Player loser = PlayerDuel.this.getOpponent(winner);
		            final int[] eloRatings = QueueHandler.getNewRankings(LamaHub.getInstance().getUserManager().getUser(winner.getUniqueId()).getElo(PlayerDuel.this.kit.getName()), LamaHub.getInstance().getUserManager().getUser(loser.getUniqueId()).getElo(PlayerDuel.this.kit.getName()), true);
		            int dif1 = Math.abs(LamaHub.getInstance().getUserManager().getUser(winner.getUniqueId()).getElo(PlayerDuel.this.kit.getName()) - eloRatings[0]);
		            int dif2 = Math.abs(LamaHub.getInstance().getUserManager().getUser(loser.getUniqueId()).getElo(PlayerDuel.this.kit.getName()) - eloRatings[1]);
		            LamaHub.getInstance().getUserManager().getUser(winner.getUniqueId()).setElo(PlayerDuel.this.kit.getName(), eloRatings[0]);
		            LamaHub.getInstance().getUserManager().getUser(loser.getUniqueId()).setElo(PlayerDuel.this.kit.getName(), eloRatings[1]);
		            for(Player p : PlayerDuel.this.participating){
			            p.sendMessage(ChatColor.YELLOW + "ELO: " + ChatColor.GREEN + winner.getName() + " +" + dif1 + " (" + eloRatings[0] + ") " + ChatColor.RED + loser.getName() + " -" + dif2 + " (" + eloRatings[1] + ")");
		            }
				}
				for(Player p : PlayerDuel.this.participating){
					p.sendMessage("§aInventaires: §cbientôt...");
				}
				LamaHub.getInstance().getQueueHandler().endDuel(PlayerDuel.this);
			}
		}.runTaskLater(LamaHub.getInstance(), 40l);
	}
}
