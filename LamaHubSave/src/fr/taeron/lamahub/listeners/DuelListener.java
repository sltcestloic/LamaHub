package fr.taeron.lamahub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.match.PlayerDuel;

public class DuelListener implements Listener{

	
	@EventHandler
	public void drop(PlayerDropItemEvent e){
		if(LamaHub.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId()).getCurrentDuel() != null){
			e.getItemDrop().remove();
			//Je ferais un EntityHider dans le Spigot plus tard
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
