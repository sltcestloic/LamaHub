package fr.taeron.lamahub.inventory.type;

import org.bukkit.Bukkit;

import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.inventory.InventorySnapshot;
import fr.taeron.lamahub.scoreboard.provider.FFAScoreboardProvider;

public class QueueInventory extends InventorySnapshot{

	public QueueInventory(LamaHub plugin) {
        super(plugin, new FFAScoreboardProvider(), Bukkit.getWorld("FFASoup").getSpawnLocation().add(0.5, 0.5, 0.5));
	}

}
