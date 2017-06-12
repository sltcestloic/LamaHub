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
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import fr.taeron.core.Core;
import fr.taeron.lamahub.Config;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.inventory.gui.ColorGui;
import fr.taeron.lamahub.inventory.gui.HatGui;
import fr.taeron.lamahub.inventory.gui.HatGuiPage2;
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
				e.getPlayer().sendMessage("Â§2Tu dois Ãªtre VIP pour utiliser cette commande.");
				e.getPlayer().sendMessage("Â§2Notre boutique: Â§ahttp://lamahub.buycraft.net/");
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
		if(s.equalsIgnoreCase("Â§1Bleu")){
			user.setPrefix("Â§1");
		}
		if(s.equalsIgnoreCase("Â§bAqua")){
			user.setPrefix("Â§b");
		}
		if(s.equalsIgnoreCase("Â§3Cyan")){
			user.setPrefix("Â§3");
		}
		if(s.equalsIgnoreCase("Â§2Vert")){
			user.setPrefix("Â§2");
		}
		if(s.equalsIgnoreCase("Â§aVert Clair")){
			user.setPrefix("Â§a");
		}
		if(s.equalsIgnoreCase("Â§dRose")){
			user.setPrefix("Â§d");
		}
		if(s.equalsIgnoreCase("Â§eJaune")){
			user.setPrefix("Â§e");
		}
		if(s.equalsIgnoreCase("Â§cRouge")){
			user.setPrefix("Â§c");
		}
		if(s.equalsIgnoreCase("Â§6Orange")){
			user.setPrefix("Â§6");
		}
		if(s.equalsIgnoreCase("Â§7Gris")){
			user.setPrefix("Â§7");
		}
		p.sendMessage("Â§2Pseudo changÃ© en " + s);
		p.setPlayerListName(user.getPrefix() + p.getName());
		p.closeInventory();
	}
	
	@EventHandler
	public void inventoryClickedHat(InventoryClickEvent e){
		if(e.getSlotType() == SlotType.OUTSIDE){
			return;
		}
		if(e.getCurrentItem() == null){
			return;
		}
		if(!e.getInventory().getTitle().equalsIgnoreCase(HatGui.title())){
			return;
		}
		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
		String displayName = e.getCurrentItem().getItemMeta().getDisplayName();
		switch (displayName) {
		case "§6Page suivante ➔":
			Bukkit.getScheduler().runTaskLater((Plugin)LamaHub.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.closeInventory();
    				HatGuiPage2.open(p);
					p.updateInventory();
                }
            }, 1L);	
			break;
		case "§eCobweb":
			p.getInventory().setHelmet(new ItemStack(Material.WEB));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeaux !");
			break;
		case "§eMelon":
			p.getInventory().setHelmet(new ItemStack(Material.MELON_BLOCK));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeaux !");
			break;
		case "§eGlass":
			p.getInventory().setHelmet(new ItemStack(Material.GLASS));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeaux !");
			break;
		case "§eRedstone Torch":
			p.getInventory().setHelmet(new ItemStack(Material.REDSTONE_TORCH_ON));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeaux !");
			p.getInventory().setHelmet(new ItemStack(Material.REDSTONE_LAMP_ON));
			break;
		case "§eCoal":
			p.getInventory().setHelmet(new ItemStack(Material.COAL_BLOCK));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeaux !");
			break;
		default:
			break;
		}
	}
	
	@EventHandler
	public void inventoryClickedHatPage2(InventoryClickEvent e){
		if(e.getSlotType() == SlotType.OUTSIDE){
			return;
		}
		if(e.getCurrentItem() == null){
			return;
		}
		if(!e.getInventory().getTitle().equalsIgnoreCase(HatGuiPage2.title())){
			return;
		}
		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
		String displayName = e.getCurrentItem().getItemMeta().getDisplayName();
		switch (displayName) {
		case "§6Page précédente":
			Bukkit.getScheduler().runTaskLater((Plugin)LamaHub.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.closeInventory();
    				HatGui.open(p);
					p.updateInventory();
                }
            }, 1L);	
			break;
		case "§eTnt":
			p.getInventory().setHelmet(new ItemStack(Material.TNT));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeaux !");
			break;
		case "§eEponge":
			p.getInventory().setHelmet(new ItemStack(Material.SPONGE));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeaux !");
			break;
		case "§eFeuille morte":
			p.getInventory().setHelmet(new ItemStack(Material.DEAD_BUSH));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeaux !");
			break;
		case "§eVignes":
			p.getInventory().setHelmet(new ItemStack(Material.VINE));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeaux !");
			break;
		case "§eTable d'enchantement":
			p.getInventory().setHelmet(new ItemStack(Material.ENCHANTMENT_TABLE));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeaux !");
			break;
		default:
			break;
		}
	}
}
