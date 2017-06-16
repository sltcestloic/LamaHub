package fr.taeron.lamahub.match;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.taeron.core.kits.Kit;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.match.arena.Arena;
import fr.taeron.lamahub.scoreboard.PlayerBoard;
import fr.taeron.lamahub.scoreboard.provider.DuelScoreboardProvider;

public class PlayerDuel {

	private Player player1;
	private Player player2;
	private long startMillis;
	private Arena arena;
	private Kit kit;
	private List<Player> participating;
	
	@SuppressWarnings("deprecation")
	public PlayerDuel(Player first, Player second, Arena a, Kit kit){
		for(Player p : Bukkit.getOnlinePlayers()){
			p.hidePlayer(first, false);
			p.hidePlayer(second, false);
		}
		this.player1 = first;
		this.player2 = second;
		this.arena = a;
		this.kit = kit;
		this.startMillis = System.currentTimeMillis() - 5000;
		this.participating = new ArrayList<Player>();
		this.participating.add(player1);
		this.participating.add(player2);
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
	
	public void start(){
		for(Player p : this.participating){
			PlayerBoard playerBoard = LamaHub.getInstance().getScoreboardHandler().getPlayerBoard(p.getUniqueId());
	        if (playerBoard != null) {
	            playerBoard.setDefaultSidebar(new DuelScoreboardProvider(), 2L);
	        }
		}
	}
}
