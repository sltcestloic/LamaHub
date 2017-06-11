package fr.taeron.lamahub.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.taeron.core.Core;
import fr.taeron.lamahub.Config;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.inventory.gui.KitGui;
import fr.taeron.lamahub.inventory.gui.MainGui;

public class GUIListener implements Listener{

	
	@EventHandler
	public void itemClicked(PlayerInteractEvent e){
		if(e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK){
			return;
		}
		if(e.getItem() == null || e.getItem().getType() == Material.AIR){
			return;
		}
		if(e.getItem() == Config.SPAWN_COMPASS_ITEM){
			MainGui.open(e.getPlayer());
		}
	}
	
	@EventHandler
	public void inventoryClickedSpawn(InventoryClickEvent e){
		if(e.getSlotType() == SlotType.OUTSIDE){
			return;
		}
		if(e.getCurrentItem() == null){
			return;
		}
		if(e.getInventory().getTitle().equalsIgnoreCase(MainGui.title())){
			return;
		}
		e.setCancelled(true);
		if(e.getCurrentItem().getType() == Material.MUSHROOM_SOUP){
			LamaHub.getInstance().getInventoryHandler().ffaInventory.applyTo((Player) e.getWhoClicked(), true, true);
		}
	}
	
	@EventHandler
	public void inventoryClickedFFA(InventoryClickEvent e){
		if(e.getSlotType() == SlotType.OUTSIDE){
			return;
		}
		if(e.getCurrentItem() == null){
			return;
		}
		if(e.getInventory().getTitle().equalsIgnoreCase(KitGui.title())){
			return;
		}
		e.setCancelled(true);
		if(e.getCurrentItem().getType() == Material.STONE_SWORD){
			Core.getPlugin().getKitManager().getKit("Guerrier").applyTo((Player) e.getWhoClicked(), true, false);
			e.getWhoClicked().closeInventory();
		}
	}
}
