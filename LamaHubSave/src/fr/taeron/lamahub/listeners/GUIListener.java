package fr.taeron.lamahub.listeners;

import org.bukkit.Bukkit;
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
import fr.taeron.lamahub.inventory.gui.ColorGui;
import fr.taeron.lamahub.inventory.gui.KitGui;
import fr.taeron.lamahub.inventory.gui.MainGui;
import fr.taeron.lamahub.user.LamaUser;

public class GUIListener implements Listener{

	
	@EventHandler
	public void itemClicked(PlayerInteractEvent e){
		if(e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK){
			return;
		}
		if(e.getItem() == null || e.getItem().getType() == Material.AIR){
			return;
		}
		if(e.getItem().equals(Config.SPAWN_COMPASS_ITEM)){
			MainGui.open(e.getPlayer());
		}
		if(e.getItem().equals(Config.FFA_SELECTOR_ITEM)){
			KitGui.open(e.getPlayer());
		}
		if(e.getItem().equals(Config.TRAILS_ITEM)){
			if(!e.getPlayer().hasPermission("vip")){
				e.getPlayer().sendMessage("§2Tu dois être VIP pour utiliser cette commande.");
				e.getPlayer().sendMessage("§2Notre boutique: §ahttp://lamahub.buycraft.net/");
				return;
			}
			e.getPlayer().performCommand("trail");
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
		if(!e.getInventory().getTitle().equalsIgnoreCase(MainGui.title())){
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
		if(!e.getInventory().getTitle().equalsIgnoreCase(KitGui.title())){
			return;
		}
		e.setCancelled(true);
		if(e.getCurrentItem().getType() == Material.STONE_SWORD){
			e.getWhoClicked().getInventory().clear();
			Core.getPlugin().getKitManager().getKit("Guerrier").applyTo((Player) e.getWhoClicked(), true, false);
			e.getWhoClicked().closeInventory();
		}
	}
	
	@EventHandler
	public void inventoryClickedColor(InventoryClickEvent e){
		if(e.getSlotType() == SlotType.OUTSIDE){
			return;
		}
		if(e.getCurrentItem() == null){
			return;
		}
		if(!e.getInventory().getTitle().equalsIgnoreCase(ColorGui.title())){
			return;
		}
		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
		LamaUser user = LamaHub.getInstance().getUserManager().getUser(p.getUniqueId());
		String s = e.getCurrentItem().getItemMeta().getDisplayName();
		if(s.equalsIgnoreCase("§1Bleu")){
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pp user " + e.getWhoClicked().getName() + " prefix set \"&f[&b&lVIP&f]&1 \"");
			user.setPrefix("§1");
		}
		if(s.equalsIgnoreCase("§bAqua")){
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pp user " + e.getWhoClicked().getName() + " prefix set \"&f[&b&lVIP&f]&b \"");
			user.setPrefix("§b");
		}
		if(s.equalsIgnoreCase("§3Cyan")){
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pp user " + e.getWhoClicked().getName() + " prefix set \"&f[&b&lVIP&f]&3 \"");
			user.setPrefix("§3");
		}
		if(s.equalsIgnoreCase("§2Vert")){
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pp user " + e.getWhoClicked().getName() + " prefix set \"&f[&b&lVIP&f]&2 \"");
			user.setPrefix("§2");
		}
		if(s.equalsIgnoreCase("§aVert Clair")){
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pp user " + e.getWhoClicked().getName() + " prefix set \"&f[&b&lVIP&f]&a \"");
			user.setPrefix("§a");
		}
		if(s.equalsIgnoreCase("§dRose")){
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pp user " + e.getWhoClicked().getName() + " prefix set \"&f[&b&lVIP&f]&d \"");
			user.setPrefix("§d");
		}
		if(s.equalsIgnoreCase("§eJaune")){
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pp user " + e.getWhoClicked().getName() + " prefix set \"&f[&b&lVIP&f]&e \"");
			user.setPrefix("§e");
		}
		if(s.equalsIgnoreCase("§cRouge")){
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pp user " + e.getWhoClicked().getName() + " prefix set \"&f[&b&lVIP&f]&c \"");
			user.setPrefix("§c");
		}
		if(s.equalsIgnoreCase("§6Orange")){
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pp user " + e.getWhoClicked().getName() + " prefix set \"&f[&b&lVIP&f]&6 \"");
			user.setPrefix("§6");
		}
		p.sendMessage("§2Pseudo changé en " + s);
		p.closeInventory();
	}
}
