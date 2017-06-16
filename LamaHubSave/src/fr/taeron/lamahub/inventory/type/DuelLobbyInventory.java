package fr.taeron.lamahub.inventory.type;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import fr.taeron.lamahub.Config;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.inventory.InventorySnapshot;
import fr.taeron.lamahub.scoreboard.SidebarProvider;
import fr.taeron.lamahub.scoreboard.provider.SpawnScoreboardProvider;

public class DuelLobbyInventory extends InventorySnapshot{

	public DuelLobbyInventory(LamaHub plugin, SidebarProvider sidebarProvider, Location location) {
		super(plugin, new SpawnScoreboardProvider(), location);
	}

	
	@Override
    public void applyTo(Player player, final boolean teleport, final boolean setItems) {
        super.applyTo(player, teleport, setItems);
        player.getInventory().clear();
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
            inventory.setItem(0, Config.FFA_SELECTOR_ITEM);
            inventory.setItem(7, Config.HAT_ITEM);
            inventory.setItem(8, Config.TRAILS_ITEM);
            inventory.setItem(6, Config.SETTINGS_ITEM);
        }
        player.updateInventory();
    }
}
