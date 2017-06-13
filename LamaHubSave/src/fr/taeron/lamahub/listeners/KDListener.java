package fr.taeron.lamahub.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.user.LamaUser;

public class KDListener implements Listener{

	
	@EventHandler
	public void update(PlayerDeathEvent e){
		if(!(e.getEntity() instanceof Player)){
			return;
		}
		e.getDrops().clear();
		ItemStack it = new ItemStack(Material.BOWL, 16);
		ItemStack it2 = new ItemStack(Material.BROWN_MUSHROOM, 16);
		ItemStack it3 = new ItemStack(Material.RED_MUSHROOM, 16);
		e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), it);
		e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), it2);
		e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), it3);
		LamaUser ap = LamaHub.getInstance().getUserManager().getUser(e.getEntity().getUniqueId());
		if(e.getEntity().getLastDamageCause().getCause() != DamageCause.SUICIDE){
			ap.setDeaths(ap.getDeaths() + 1);
		}
		if(e.getEntity().getKiller() != null){
			LamaUser killer = LamaHub.getInstance().getUserManager().getUser(e.getEntity().getKiller().getUniqueId());
			e.getEntity().sendMessage("§c" + e.getEntity().getName() + " (" + ap.getCurrentKitName() + ") §7a été tué par §a" + e.getEntity().getKiller().getName() + " (" + killer.getCurrentKitName() + ")");
			e.getEntity().getKiller().sendMessage("§c" + e.getEntity().getName() + " (" + ap.getCurrentKitName() + ") §7a été tué par §a" + e.getEntity().getKiller().getName() + " (" + killer.getCurrentKitName() + ")");
			LamaUser ap2 = LamaHub.getInstance().getUserManager().getUser(e.getEntity().getKiller().getUniqueId());
			ap2.setKills(ap2.getKills() + 1);
			ap2.setKS(ap2.getKS() + 1);
			if(ap.getKS() < 5){
				ap2.addCoins(5);
				e.getEntity().getKiller().sendMessage("§a+5 §2LamaCoins");
			} else if(ap.getKS() < 10){
				ap2.addCoins(10);
				e.getEntity().getKiller().sendMessage("§a+10 §2LamaCoins");
			} else if (ap.getKS() < 15){
				ap2.addCoins(20);
				e.getEntity().getKiller().sendMessage("§a+20 §2LamaCoins");
			} else if (ap.getKS() < 20){
				ap2.addCoins(30);
				e.getEntity().getKiller().sendMessage("§a+30 §2LamaCoins");
			} else {
				ap2.addCoins(50);
				e.getEntity().getKiller().sendMessage("§a+50 §2LamaCoins");
			}
			
		} else {
			e.getEntity().sendMessage("§c" + e.getEntity().getName() + " §7est mort");
		}
		ap.setKS(0);
		ap.setCurrentKit("Aucun");
	}
}
