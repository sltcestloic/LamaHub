package fr.taeron.lamahub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageFixListener implements Listener{

	
	@EventHandler
	public void fix(EntityDamageByEntityEvent e){
		if(!(e.getDamager() instanceof Player)){
			return;
		}
		e.setDamage(e.getDamage() / 1.55);
	}
}
