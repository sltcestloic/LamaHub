package fr.taeron.lamahub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.match.PlayerDuel;

public class DuelListener implements Listener{

	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void drop(PlayerDropItemEvent e){
		if(LamaHub.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId()).getCurrentDuel() != null){
			e.getItemDrop().remove();
			for(Player p : Bukkit.getOnlinePlayers()){
				if(!LamaHub.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId()).getCurrentDuel().getParticipating().contains(p)){
					((CraftPlayer)p).hide(e.getItemDrop());
				}
			}
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		if(LamaHub.getInstance().getUserManager().getUser(e.getEntity().getUniqueId()).getCurrentDuel() != null){
			PlayerDuel duel = LamaHub.getInstance().getUserManager().getUser(e.getEntity().getUniqueId()).getCurrentDuel();
			duel.end(duel.getOpponent(e.getEntity()));
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		if(LamaHub.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId()).getCurrentDuel() != null){
			PlayerDuel duel = LamaHub.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId()).getCurrentDuel();
			duel.end(duel.getOpponent(e.getPlayer()));
		}
	}
}
