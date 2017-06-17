package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.taeron.core.util.ItemBuilder;

public class RankedGui {

	private static Inventory i;
	
	public RankedGui(){
		i = Bukkit.createInventory(null, 9, "§aRanked");
		i.setItem(0, new ItemBuilder(Material.STONE_SWORD, 1).displayName("§bEarlyHG").lore("", "§eEn jeu: §aDev", "§eEn queue: §aDev").build());
		i.setItem(1, new ItemBuilder(Material.IRON_CHESTPLATE, 1).displayName("§bIron").lore("", "§eEn jeu: §aDev", "§eEn queue: §aDev").build());
	}
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}
}
