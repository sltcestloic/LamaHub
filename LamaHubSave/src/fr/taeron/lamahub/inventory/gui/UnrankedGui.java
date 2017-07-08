package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.taeron.core.util.ItemBuilder;
import fr.taeron.lamahub.LamaHub;

public class UnrankedGui {

private static Inventory i;
	
	public UnrankedGui(){
		i = Bukkit.createInventory(null, 9, "§9Unranked");
		i.setItem(0, new ItemBuilder(Material.STONE_SWORD, LamaHub.getInstance().getQueueHandler().unrankedEarlyQueue.getPlaying() == 0 ? 1 : LamaHub.getInstance().getQueueHandler().unrankedEarlyQueue.getPlaying()).displayName("§bEarlyHG").lore("", "§eEn jeu: §a" + LamaHub.getInstance().getQueueHandler().unrankedEarlyQueue.getPlaying(), "§eEn queue: §a" + LamaHub.getInstance().getQueueHandler().unrankedEarlyQueue.getQueue().size()).build());
		i.setItem(1, new ItemBuilder(Material.IRON_CHESTPLATE, LamaHub.getInstance().getQueueHandler().unrankedIronQueue.getPlaying() == 0 ? 1 : LamaHub.getInstance().getQueueHandler().unrankedIronQueue.getPlaying()).displayName("§bIron").lore("", "§eEn jeu: §a" + LamaHub.getInstance().getQueueHandler().unrankedIronQueue.getPlaying(), "§eEn queue: §a" + LamaHub.getInstance().getQueueHandler().unrankedIronQueue.getQueue().size()).build());
	}
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}
}
