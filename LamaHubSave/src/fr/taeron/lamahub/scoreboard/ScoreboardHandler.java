package fr.taeron.lamahub.scoreboard;


import org.bukkit.*;
import org.bukkit.plugin.*;
import org.bukkit.scheduler.*;

import fr.taeron.lamahub.LamaHub;

import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class ScoreboardHandler implements Listener
{
    private final Map<UUID, PlayerBoard> playerBoards;
    private final LamaHub plugin;
    private static Collection<Player> online = new ArrayList<Player>();
    
    public ScoreboardHandler(final LamaHub plugin) {
        this.playerBoards = new HashMap<UUID, PlayerBoard>();
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)(this.plugin = plugin));
        new BukkitRunnable() {
            @SuppressWarnings("deprecation")
			public void run() {
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    final PlayerBoard playerBoard;
                    ScoreboardHandler.this.setPlayerBoard(player.getUniqueId(), playerBoard = new PlayerBoard(plugin, player));
                    playerBoard.addUpdates(ScoreboardHandler.getOnlinePlayers());
                }
            }
        }.runTaskLater((Plugin)plugin, 20L);
    }
    
    @SuppressWarnings("deprecation")
	public static Collection<Player> getOnlinePlayers(){
    	online.clear();
    	for(Player p : Bukkit.getOnlinePlayers()){
    		online.add(p);
    	}
    	return online;
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();
        for (final PlayerBoard board : this.playerBoards.values()) {
            board.addUpdate(player);
        }
        final PlayerBoard board2 = new PlayerBoard(this.plugin, player);
        board2.addUpdates(ScoreboardHandler.getOnlinePlayers());
        this.setPlayerBoard(uuid, board2);
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerQuit(final PlayerQuitEvent event) {
        this.playerBoards.remove(event.getPlayer().getUniqueId()).remove();
    }
    
    public PlayerBoard getPlayerBoard(final UUID uuid) {
        return this.playerBoards.get(uuid);
    }
    
    public void setPlayerBoard(final UUID uuid, final PlayerBoard board) {
        this.playerBoards.put(uuid, board);
        board.setSidebarVisible(true);
    }
    
    public void clearBoards() {
        final Iterator<PlayerBoard> iterator = this.playerBoards.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().remove();
            iterator.remove();
        }
    }
}
