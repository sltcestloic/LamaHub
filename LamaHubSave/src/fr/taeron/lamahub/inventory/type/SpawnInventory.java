package fr.taeron.lamahub.inventory.type;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import fr.taeron.lamahub.Config;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.inventory.InventorySnapshot;

public class SpawnInventory extends InventorySnapshot{

	public SpawnInventory(LamaHub plugin) {
        super(plugin, null, new Location(Bukkit.getWorld("Hub"), 0.0, 108.0, 0.0));
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
        player.setAllowFlight(false);
        player.getActivePotionEffects().clear();
        final PlayerInventory inventory = player.getInventory();
        if (setItems) {
            inventory.setItem(4, Config.SPAWN_COMPASS_ITEM);
        }
        player.updateInventory();
    }
}
