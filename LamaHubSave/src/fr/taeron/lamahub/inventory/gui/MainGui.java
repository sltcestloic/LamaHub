package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.taeron.core.util.ItemBuilder;

public class MainGui {

	private static Inventory i;
	
	public MainGui(){
		i = Bukkit.createInventory(null, 9, "§9Choix du jeu");
		i.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).displayName(" ").build());
		i.setItem(1, new ItemBuilder(Material.MUSHROOM_SOUP).displayName("§9Soup FFA").build());
		i.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).displayName(" ").build());
		i.setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE).displayName(" ").build());
		i.setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE).displayName(" ").build());
		i.setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE).displayName(" ").build());
		i.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).displayName(" ").build());
		i.setItem(7, new ItemBuilder(Material.DIAMOND_CHESTPLATE).displayName("§91v1 §9(§7En Developpement§9)").build());
		i.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).displayName(" ").build());
	}
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}
}
