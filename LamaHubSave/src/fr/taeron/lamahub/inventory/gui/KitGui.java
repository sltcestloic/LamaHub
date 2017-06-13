package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.taeron.core.util.ItemBuilder;

public class KitGui {

private static Inventory i;
	
	public KitGui(){
		i = Bukkit.createInventory(null, 9, "§9Choix du kit");
		i.setItem(0, new ItemBuilder(Material.STONE_SWORD).displayName("§9Guerrier").build());
		i.setItem(1, new ItemBuilder(Material.DIAMOND_HELMET).displayName("§9AntiStomper").build());
		i.setItem(2, new ItemBuilder(Material.STICK).displayName("§9Violeur").build());
		i.setItem(3, new ItemBuilder(Material.ANVIL).displayName("§9Stomper").build());
	}
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}
}
