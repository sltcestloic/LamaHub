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
import fr.taeron.core.util.ItemBuilder;
import fr.taeron.lamahub.Config;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.inventory.gui.ColorGui;
import fr.taeron.lamahub.inventory.gui.CommandUtilsGui;
import fr.taeron.lamahub.inventory.gui.HatGui;
import fr.taeron.lamahub.inventory.gui.HatGuiPage2;
import fr.taeron.lamahub.inventory.gui.KitGui;
import fr.taeron.lamahub.inventory.gui.LiensUtilesGui;
import fr.taeron.lamahub.inventory.gui.MainGui;
import fr.taeron.lamahub.inventory.gui.ParametreGui;
import fr.taeron.lamahub.inventory.gui.PlayerGui;
import fr.taeron.lamahub.inventory.gui.RankedGui;
import fr.taeron.lamahub.inventory.gui.SonsGui;
import fr.taeron.lamahub.inventory.gui.UnrankedGui;
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
	public void itemClickerParametreLiens(InventoryClickEvent e){
		if(e.getSlotType() == SlotType.OUTSIDE){
			return;
		}
		if(e.getCurrentItem() == null){
			return;
		}
		if(!e.getInventory().getTitle().equalsIgnoreCase(LiensUtilesGui.title())){
			return;
		}
		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
		if(e.getCurrentItem().getType() == Material.STAINED_CLAY && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lBoutique")){
			p.closeInventory();
			p.sendMessage("§f➥ §fhttp://lamahub.buycraft.net/");
		}else if(e.getCurrentItem().getType() == Material.STAINED_CLAY && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lDiscord")){
			p.closeInventory();
			p.sendMessage("§f➥ §fhttps://discord.gg/QjhvzT6");
		}else if(e.getCurrentItem().getType() == Material.STAINED_CLAY && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lDeveloppeurs")){
			p.closeInventory();
			p.sendMessage("§6koalaQ_Q§r §f➥ §fhttps://www.youtube.com/user/lawhitteam");
			p.sendMessage("§6Skazzy§r §f➥ §fhttps://www.youtube.com/channel/UCcqG1czcpNWAlpausEx5UuQ");
		}
	}
	
	@EventHandler
	public void itemClickerParametre(InventoryClickEvent e){
		if(e.getSlotType() == SlotType.OUTSIDE){
			return;
		}
		if(e.getCurrentItem() == null){
			return;
		}
		if(!e.getInventory().getTitle().equalsIgnoreCase(ParametreGui.title())){
			return;
		}
		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
		if(e.getCurrentItem().getType() == Material.SIGN){
			Bukkit.getScheduler().runTaskLater((Plugin)LamaHub.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
        			LiensUtilesGui.open(p);
                }
            }, 1L);	
		} else if(e.getCurrentItem().getType() == Material.JUKEBOX){
			Bukkit.getScheduler().runTaskLater((Plugin)LamaHub.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
        			SonsGui.open(p);
                }
            }, 1L);	
		} else if(e.getCurrentItem().getType() == Material.SKULL_ITEM){
			Bukkit.getScheduler().runTaskLater((Plugin)LamaHub.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
        			PlayerGui.open(p);
                }
            }, 1L);	
		}
	}
	
	@EventHandler
	public void itemClickerParametrePlayer(InventoryClickEvent e){
		if(e.getSlotType() == SlotType.OUTSIDE){
			return;
		}
		if(e.getCurrentItem() == null){
			return;
		}
		if(!e.getInventory().getTitle().equalsIgnoreCase(PlayerGui.title())){
			return;
		}
		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
		if(e.getCurrentItem().getType() == Material.NAME_TAG){
			Bukkit.getScheduler().runTaskLater((Plugin)LamaHub.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
        			CommandUtilsGui.open(p);
                }
            }, 1L);	
		}
	}
	
	@EventHandler
	public void itemClickerParametreCommandes(InventoryClickEvent e){
		if(e.getSlotType() == SlotType.OUTSIDE){
			return;
		}
		if(e.getCurrentItem() == null){
			return;
		}
		if(!e.getInventory().getTitle().equalsIgnoreCase(CommandUtilsGui.title())){
			return;
		}
		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
		if(e.getCurrentItem().getType() == Material.PAPER && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c/report")){
			p.closeInventory();
			p.sendMessage("§cSyntaxe : /report <joueur> <raison>");
		}else if(e.getCurrentItem().getType() == Material.PAPER && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c/color §9(§7VIP ONLY§9)")){
			p.closeInventory();
			p.sendMessage("§cSyntaxe : /color");
		}else if(e.getCurrentItem().getType() == Material.PAPER && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c/trail §9(§7VIP ONLY§9)")){
			p.closeInventory();
			p.sendMessage("§cSyntaxe : /trail");
		}
			
	}
	
	@EventHandler
	public void itemClickerSons(InventoryClickEvent e){
		if(e.getSlotType() == SlotType.OUTSIDE){
			return;
		}
		if(e.getCurrentItem() == null){
			return;
		}
		if(!e.getInventory().getTitle().equalsIgnoreCase(SonsGui.title())){
			return;
		}
		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
		LamaUser user = LamaHub.getInstance().getUserManager().getUser(p.getUniqueId());
		if(e.getCurrentItem().getType() == Material.INK_SACK && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aMentions (Activé)")){
			user.setNotification(false);
			Bukkit.getScheduler().runTaskLater(LamaHub.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					p.getOpenInventory().setItem(1, new ItemBuilder(Material.INK_SACK).data((short) 8).displayName("§cMentions (Désactivé)").lore("§f➤ §7 Lorsque quelqu'un vous mentionne vous entendrez un petit §9'§nding§r§9'").build());
				}
			}, 1L);
		}
		if(e.getCurrentItem().getType() == Material.INK_SACK && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cMentions (Désactivé)")){
			user.setNotification(true);
			Bukkit.getScheduler().runTaskLater(LamaHub.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					p.getOpenInventory().setItem(1, new ItemBuilder(Material.INK_SACK).data((short) 10).displayName("§aMentions (Activé)").lore("§f➤ §7 Lorsque quelqu'un vous mentionne vous entendrez un petit §9'§nding§r§9'").build());
				}
			}, 1L);
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
		if(e.getItem().equals(Config.FRIENDS_ITEM)){
			// FRIENDS GUI
		}
		if(e.getItem().equals(Config.SETTINGS_ITEM)){
			ParametreGui.open(e.getPlayer());
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
		if(e.getItem().equals(Config.RANKED_ITEM)){
			RankedGui.open(e.getPlayer());
		}
		if(e.getItem().equals(Config.UNRANKED_ITEM)){
			UnrankedGui.open(e.getPlayer());
		}
		if(e.getItem().equals(Config.QUEUE_LEAVE_ITEM)){
			LamaUser user = LamaHub.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId());
			if(user.getQueue() != null){
				user.getQueue().removePlayer(e.getPlayer());
				user.setCurrentQueue(null);
				LamaHub.getInstance().getInventoryHandler().duelLobbyInventory.applyTo(e.getPlayer(), false, true);
			}
		}
		if(e.getItem().equals(Config.LOBBY_ITEM)){
			LamaHub.getInstance().getInventoryHandler().spawnInventory.applyTo(e.getPlayer(), true, true);
		}
	}
	
	/*@EventHandler
	public void inventoryClickRanked(InventoryClickEvent e){
		if(e.getSlotType() == SlotType.OUTSIDE){
			return;
		}
		if(e.getCurrentItem() == null){
			return;
		}
		if(!e.getInventory().getTitle().equalsIgnoreCase(RankedGui.title())){
			return;
		}
		if(e.getCurrentItem().getType() == Material.STONE_SWORD){
			LamaHub.getInstance().getQueueHandler().rankedEarlyQueue.addPlayer((Player) e.getWhoClicked());
		}
		if(e.getCurrentItem().getType() == Material.IRON_CHESTPLATE){
			LamaHub.getInstance().getQueueHandler().rankedIronQueue.addPlayer((Player) e.getWhoClicked());
		}
		e.getWhoClicked().closeInventory();
	}
	
	@EventHandler
	public void inventoryClickUnranked(InventoryClickEvent e){
		if(e.getSlotType() == SlotType.OUTSIDE){
			return;
		}
		if(e.getCurrentItem() == null){
			return;
		}
		if(!e.getInventory().getTitle().equalsIgnoreCase(RankedGui.title())){
			return;
		}
		if(e.getCurrentItem().getType() == Material.STONE_SWORD){
			LamaHub.getInstance().getQueueHandler().unrankedEarlyQueue.addPlayer((Player) e.getWhoClicked());
		}
		if(e.getCurrentItem().getType() == Material.IRON_CHESTPLATE){
			LamaHub.getInstance().getQueueHandler().unrankedIronQueue.addPlayer((Player) e.getWhoClicked());
		}
		e.getWhoClicked().closeInventory();
	}*/
	
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
		if(!e.getCurrentItem().hasItemMeta()){
			return;
		}
		String kitName = e.getCurrentItem().getItemMeta().getDisplayName().replace("§9", "");
		if(kitName.equalsIgnoreCase("Guerrier") || kitName.equalsIgnoreCase("AntiStomper") || kitName.equalsIgnoreCase("Violeur") || kitName.equalsIgnoreCase("Mongole")){
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
		if(!e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName()){
			return;
		}
		Player p = (Player) e.getWhoClicked();
		String displayName = e.getCurrentItem().getItemMeta().getDisplayName();
		switch (displayName) {
		case "§6Page suivante ->":
			Bukkit.getScheduler().runTaskLater((Plugin)LamaHub.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
    				HatGuiPage2.open(p);
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
    				HatGui.open(p);
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
