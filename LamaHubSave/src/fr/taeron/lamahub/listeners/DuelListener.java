package fr.taeron.lamahub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.match.PlayerDuel;

public class DuelListener implements Listener{


	
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
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		if(!(e.getEntity() instanceof Player)){
			return;
		}
		Player p = (Player) e.getEntity();
		if (LamaHub.getInstance().getUserManager().getUser(p.getUniqueId()).getCurrentDuel() != null){
			PlayerDuel d = LamaHub.getInstance().getUserManager().getUser(p.getUniqueId()).getCurrentDuel();
			if(!d.hasStarted()){
				e.setCancelled(true);
			}
		}
	}
}
