package fr.taeron.lamahub.inventory.type;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.taeron.lamahub.Config;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.inventory.InventorySnapshot;
import fr.taeron.lamahub.scoreboard.provider.SpawnScoreboardProvider;

public class SpawnInventory extends InventorySnapshot{

	public SpawnInventory(LamaHub plugin) {
        super(plugin, new SpawnScoreboardProvider(), new Location(Bukkit.getWorld("Hub"), 0.0, 108.0, 0.0));
	}
	
	@Override
    public void applyTo(Player player, final boolean teleport, final boolean setItems) {
        super.applyTo(player, teleport, setItems);
        player.setExp(0.0f);
        player.setLevel(0);
        player.setFoodLevel(20);
        player.setSaturation(0.0f);
        player.setFireTicks(0);
        player.setHealth(20.0);
        player.setFlying(false);
        if(player.hasPermission("vip")){
        	player.setAllowFlight(true);
        }    
        player.getActivePotionEffects().clear();
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
        final PlayerInventory inventory = player.getInventory();
        if (setItems) {
            inventory.setItem(0, Config.SPAWN_COMPASS_ITEM);
            inventory.setItem(8, Config.TRAILS_ITEM);
            inventory.setItem(7, Config.HAT_ITEM);
            inventory.setItem(6, Config.SETTINGS_ITEM);
        }
        player.updateInventory();
        if(player.getName().length() > 14){
            player.setPlayerListName(LamaHub.getInstance().getUserManager().getUser(player.getUniqueId()).getPrefix() + player.getName().substring(0, 14));
        } else {
     	  player.setPlayerListName(LamaHub.getInstance().getUserManager().getUser(player.getUniqueId()).getPrefix() + player.getName());
        }
    }
}
