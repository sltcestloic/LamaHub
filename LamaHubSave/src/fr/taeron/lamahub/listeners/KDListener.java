package fr.taeron.lamahub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.SpawnHandler;
import fr.taeron.lamahub.user.LamaUser;

public class KDListener implements Listener{

	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void update(PlayerDeathEvent e){
		if(!(e.getEntity() instanceof Player)){
			return;
		}
		for(ItemStack i : e.getDrops()){
			if(i.getType() != Material.MUSHROOM_SOUP && i.getType() != Material.BROWN_MUSHROOM && i.getType() != Material.RED_MUSHROOM){
				e.getDrops().remove(i);
			}
		}
		LamaUser ap = LamaHub.getInstance().getUserManager().getUser(e.getEntity().getUniqueId());
		if(e.getEntity().getLastDamageCause().getCause() != DamageCause.SUICIDE){
			ap.setDeaths(ap.getDeaths() + 1);
		}
		ap.setKS(0);
		if(e.getEntity().getKiller() != null){
			for(Player p : Bukkit.getOnlinePlayers()){
				if(!SpawnHandler.isInSpawn(p)){
					p.sendMessage("§c" + e.getEntity().getName() + " §7a été tué par §a" + e.getEntity().getKiller().getName());
				}
			}
			LamaUser ap2 = LamaHub.getInstance().getUserManager().getUser(e.getEntity().getKiller().getUniqueId());
			ap2.setKills(ap2.getKills() + 1);
			ap2.setKS(ap2.getKS() + 1);
		} else {
			for(Player p : Bukkit.getOnlinePlayers()){
				if(!SpawnHandler.isInSpawn(p)){
					p.sendMessage("§c" + e.getEntity().getName() + " §7est mort");
				}
			}
		}
	}
}
