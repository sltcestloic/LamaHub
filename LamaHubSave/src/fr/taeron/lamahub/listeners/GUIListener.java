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
	public void itemClickedHat(InventoryClickEvent e){
		if(e.getClickedInventory() != null){
			if(e.getCurrentItem().equals(e.getWhoClicked().getInventory().getHelmet())){
				e.setCancelled(true); 
			}
		}
	}
	
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
		if(e.getItem().equals(Config.HAT_ITEM)){
			e.setCancelled(true);
			if(!e.getPlayer().hasPermission("vip")){
				e.getPlayer().sendMessage("§2Tu dois être VIP pour avoir accès aux §a§nChapeaux!");
				e.getPlayer().sendMessage("§2Notre boutique: §ahttp://lamahub.buycraft.net/");
				return;
			}
			HatGui.open(e.getPlayer());
		}
		if(e.getItem().equals(Config.TRAILS_ITEM)){
			if(!e.getPlayer().hasPermission("vip")){
				e.getPlayer().sendMessage("§2Tu dois être VIP pour avoir accès aux §a§nParticules!");
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
		Player p = (Player) e.getWhoClicked();
		LamaUser user = LamaHub.getInstance().getUserManager().getUser(p.getUniqueId());
		String kitName = e.getCurrentItem().getItemMeta().getDisplayName().replace("§9", "");
		if(kitName.equalsIgnoreCase("Guerrier") || kitName.equalsIgnoreCase("AntiStomper") || kitName.equalsIgnoreCase("Violeur")){
			e.getWhoClicked().getInventory().clear();
			Core.getPlugin().getKitManager().getKit(kitName).applyTo((Player) e.getWhoClicked(), true, true);
			user.setCurrentKit(kitName);
		} else if (!e.getWhoClicked().hasPermission("vip")){
			p.sendMessage("§cCe kit est réservé aux VIP pour le moment, mais pas de panique ! Tu pourras bientot l'acheter avec tes LamaCoins !");
		} else {
			e.getWhoClicked().getInventory().clear();
			Core.getPlugin().getKitManager().getKit(kitName).applyTo((Player) e.getWhoClicked(), true, true);
			user.setCurrentKit(kitName);
		}
		e.getWhoClicked().closeInventory();
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
			user.setPrefix("§1");
		}
		if(s.equalsIgnoreCase("§bAqua")){
			user.setPrefix("§b");
		}
		if(s.equalsIgnoreCase("§3Cyan")){
			user.setPrefix("§3");
		}
		if(s.equalsIgnoreCase("§2Vert")){
			user.setPrefix("§2");
		}
		if(s.equalsIgnoreCase("§aVert Clair")){
			user.setPrefix("§a");
		}
		if(s.equalsIgnoreCase("§dRose")){
			user.setPrefix("§d");
		}
		if(s.equalsIgnoreCase("§eJaune")){
			user.setPrefix("§e");
		}
		if(s.equalsIgnoreCase("§cRouge")){
			user.setPrefix("§c");
		}
		if(s.equalsIgnoreCase("§6Orange")){
			user.setPrefix("§6");
		}
		if(s.equalsIgnoreCase("§7Gris")){
			user.setPrefix("§7");
		}
		p.sendMessage("§2Pseudo changé en " + s);
		p.setPlayerListName(user.getPrefix() + p.getName());
		p.closeInventory();
	}
	
	@EventHandler
	public void inventoryClickedHatPage1(InventoryClickEvent e){
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
		case "§6Page suivante ->":
			Bukkit.getScheduler().runTaskLater((Plugin)LamaHub.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.closeInventory();
    				HatGuiPage2.open(p);
					p.updateInventory();
                }
            }, 1L);	
			break;
		case "§eEscalier de Quartz":
			p.getInventory().setHelmet(new ItemStack(Material.QUARTZ_STAIRS));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eBloc d'Emeraude":
			p.getInventory().setHelmet(new ItemStack(Material.EMERALD_BLOCK));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eMelon":
			p.getInventory().setHelmet(new ItemStack(Material.MELON_BLOCK, 1, (short)7));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eCitrouille":
			p.getInventory().setHelmet(new ItemStack(Material.PUMPKIN));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eBloc de foin":
			p.getInventory().setHelmet(new ItemStack(Material.HAY_BLOCK));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eBloc de redstone":
			p.getInventory().setHelmet(new ItemStack(Material.REDSTONE_BLOCK));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eBloc de diamand":
			p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_BLOCK));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eEtagère de livre":
			p.getInventory().setHelmet(new ItemStack(Material.BOOKSHELF, 1, (short)7));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eBloc de note":
			p.getInventory().setHelmet(new ItemStack(Material.NOTE_BLOCK));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§ePanneau de verre (Bleu)":
			p.getInventory().setHelmet(new ItemStack(Material.STAINED_GLASS, 1 , (short)3));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§ePanneau de verre (Vert)":
			p.getInventory().setHelmet(new ItemStack(Material.STAINED_GLASS, 1 , (short)5));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§ePanneau de verre (Rouge)":
			p.getInventory().setHelmet(new ItemStack(Material.STAINED_GLASS, 1 , (short)14));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§ePanneau de verre (Jaune)":
			p.getInventory().setHelmet(new ItemStack(Material.STAINED_GLASS, 1 , (short)4));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§ePanneau de verre (Orange)":
			p.getInventory().setHelmet(new ItemStack(Material.STAINED_GLASS, 1 , (short)1));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§ePanneau de verre (Noir)":
			p.getInventory().setHelmet(new ItemStack(Material.STAINED_GLASS, 1 , (short)15));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
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
		case "§eTable d'enchantement":
			p.getInventory().setHelmet(new ItemStack(Material.ENCHANTMENT_TABLE));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eMur en pierre":
			p.getInventory().setHelmet(new ItemStack(Material.COBBLE_WALL));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eDalle de quartz":
			p.getInventory().setHelmet(new ItemStack(Material.STEP, 1, (short)7));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eDalle de pierre":
			p.getInventory().setHelmet(new ItemStack(Material.STEP));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eNeige":
			p.getInventory().setHelmet(new ItemStack(Material.SNOW));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eTrappe":
			p.getInventory().setHelmet(new ItemStack(Material.TRAP_DOOR));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eCrochet":
			p.getInventory().setHelmet(new ItemStack(Material.TRIPWIRE_HOOK));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§ePortail":
			p.getInventory().setHelmet(new ItemStack(Material.FENCE_GATE, 1, (short)7));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eDispenser":
			p.getInventory().setHelmet(new ItemStack(Material.DISPENSER));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§ePiston collant":
			p.getInventory().setHelmet(new ItemStack(Material.PISTON_STICKY_BASE));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§ePlaque de pression en fer":
			p.getInventory().setHelmet(new ItemStack(Material.IRON_PLATE));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eBeacon":
			p.getInventory().setHelmet(new ItemStack(Material.BEACON));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eEchelle":
			p.getInventory().setHelmet(new ItemStack(Material.LADDER, 1, (short)7));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eNenuphar":
			p.getInventory().setHelmet(new ItemStack(Material.WATER_LILY));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		case "§eEnclume":
			p.getInventory().setHelmet(new ItemStack(Material.ANVIL));
			p.sendMessage("§7[§9Hat§7] Vous avez correctement changé votre §a§nchapeau !");
			p.closeInventory();
			break;
		default:
			break;
		}
	}
}
