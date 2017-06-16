package fr.taeron.lamahub.inventory.type;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import fr.taeron.lamahub.Config;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.inventory.InventorySnapshot;
import fr.taeron.lamahub.scoreboard.provider.FFAScoreboardProvider;

public class FFAInventory extends InventorySnapshot{

	public FFAInventory(LamaHub plugin) {
        super(plugin, new FFAScoreboardProvider(), Bukkit.getWorld("FFASoup").getSpawnLocation().add(0.5, 0.5, 0.5));
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
        LamaHub.getInstance().getUserManager().getUser(player.getUniqueId()).setCurrentKit("Aucun");
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
