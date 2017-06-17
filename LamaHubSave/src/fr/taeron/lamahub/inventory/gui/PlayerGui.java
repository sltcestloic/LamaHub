package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.taeron.core.util.ItemBuilder;
import fr.taeron.lamahub.LamaHub;

public class PlayerGui {

	private static Inventory i;
	
	public PlayerGui(){
		i = Bukkit.createInventory(null, 9, "§9Paramètres (Joueur)");
		i.setItem(4, new ItemBuilder(Material.NAME_TAG).displayName("§6§lCommandes Utiles").lore("§a➔ §f Clique droit/gauche").build());
	}
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}
}

