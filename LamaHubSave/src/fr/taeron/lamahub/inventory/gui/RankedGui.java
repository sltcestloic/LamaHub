package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.taeron.core.util.ItemBuilder;
import fr.taeron.lamahub.LamaHub;

public class RankedGui {

	private static Inventory i;
	
	public RankedGui(){
		i = Bukkit.createInventory(null, 9, "�aRanked");
		i.setItem(0, new ItemBuilder(Material.STONE_SWORD, LamaHub.getInstance().getQueueHandler().rankedEarlyQueue.getPlaying() == 0 ? 1 : LamaHub.getInstance().getQueueHandler().rankedEarlyQueue.getPlaying()).displayName("§bEarlyHG").lore("", "§eEn jeu: §a" + LamaHub.getInstance().getQueueHandler().rankedEarlyQueue.getPlaying(), "§eEn queue: §a" + LamaHub.getInstance().getQueueHandler().rankedEarlyQueue.getQueue().size()).build());
		i.setItem(1, new ItemBuilder(Material.IRON_CHESTPLATE, LamaHub.getInstance().getQueueHandler().rankedIronQueue.getPlaying() == 0 ? 1 : LamaHub.getInstance().getQueueHandler().rankedIronQueue.getPlaying()).displayName("§bIron").lore("", "§eEn jeu: §a" + LamaHub.getInstance().getQueueHandler().rankedIronQueue.getPlaying(), "§eEn queue: §a" + LamaHub.getInstance().getQueueHandler().rankedIronQueue.getQueue().size()).build());
	}
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}
}
