package fr.taeron.lamahub.inventory.type;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import fr.taeron.lamahub.Config;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.inventory.InventorySnapshot;
import fr.taeron.lamahub.scoreboard.provider.DuelLobbyScoreboardProvider;

public class DuelLobbyInventory extends InventorySnapshot{

	public DuelLobbyInventory(LamaHub plugin) {
		super(plugin, new DuelLobbyScoreboardProvider(), new Location(Bukkit.getWorld("Hub"), -67.5, 17, 451.5));
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
            inventory.setItem(0, Config.RANKED_ITEM);
            inventory.setItem(1, Config.UNRANKED_ITEM);
            inventory.setItem(8, Config.LOBBY_ITEM);
        }
        player.updateInventory();
    }
}
