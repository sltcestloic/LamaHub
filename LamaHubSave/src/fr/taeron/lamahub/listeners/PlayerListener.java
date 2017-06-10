package fr.taeron.lamahub.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.SpawnHandler;

public class PlayerListener implements Listener{

	@EventHandler
	public void blockBreak(BlockBreakEvent e){
		if(!e.getPlayer().isOp() || e.getPlayer().getGameMode() != GameMode.CREATIVE){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void blockPlace(BlockPlaceEvent e){
		if(!e.getPlayer().isOp() || e.getPlayer().getGameMode() != GameMode.CREATIVE){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
    public void onEntityDamage(final FoodLevelChangeEvent event) {
        final HumanEntity humanEntity = event.getEntity();
        if (humanEntity instanceof Player && SpawnHandler.isInSpawn((Entity)humanEntity)) {
            event.setCancelled(true);
        }
    }
	
	@EventHandler
    public void onDamage(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && SpawnHandler.isInSpawn(e.getEntity())) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onPlayerDropItem(final PlayerDropItemEvent event) {
        if (SpawnHandler.isInSpawn((Entity)event.getPlayer())) {
            event.getItemDrop().remove();
        }
    }
    
    @EventHandler
    public void moveItemsInSpawn(InventoryClickEvent e){
    	if(e.getInventory().getType() == InventoryType.CRAFTING && SpawnHandler.isInSpawn(e.getWhoClicked())){
    		e.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent event) {
        event.getDrops().clear();
        event.setDeathMessage((String)null);
        event.setDroppedExp(0);
        event.getEntity().getWorld().strikeLightningEffect(event.getEntity().getLocation());
        final Player player = event.getEntity();
        new BukkitRunnable() {
            public void run() {
                player.spigot().respawn();
            }
        }.runTask(LamaHub.getInstance());
    }
}
