package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.taeron.core.util.ItemBuilder;

public class LiensUtilesGui {

	private static Inventory i;
	
	public LiensUtilesGui(){
		i = Bukkit.createInventory(null, 9, "§9Paramètres (Liens)");
		i.setItem(2, new ItemBuilder(Material.STAINED_CLAY).data((short)13).displayName("§6§lBoutique").lore("§a➔ §f Clique droit/gauche").build());
		i.setItem(4, new ItemBuilder(Material.STAINED_CLAY).displayName("§6§lDiscord").data((short)3).lore("§a➔ §f Clique droit/gauche").build());
		i.setItem(6, new ItemBuilder(Material.STAINED_CLAY).displayName("§6§lDeveloppeurs :3").data((short)1).lore("§a➔ §f Clique droit/gauche").build());
	}
	
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}
}
