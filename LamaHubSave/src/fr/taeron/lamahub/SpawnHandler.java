package fr.taeron.lamahub;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class SpawnHandler {

	public static boolean isInSpawn(final Location location) {
        return Math.abs(location.getX()) < 200.0 && Math.abs(location.getZ()) < 200.0 && location.getWorld().getName().equals("Hub");
    }
    
    public static boolean isInSpawn(final Block block) {
        return isInSpawn(block.getLocation());
    }
    
    public static boolean isInSpawn(final Entity entity) {
        return isInSpawn(entity.getLocation());
    }
}
