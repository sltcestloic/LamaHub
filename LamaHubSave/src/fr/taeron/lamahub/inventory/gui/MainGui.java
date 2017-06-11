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
		i.setItem(0, new ItemBuilder(Material.MUSHROOM_SOUP).displayName("§9Soup FFA").build());
	}
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}
}
