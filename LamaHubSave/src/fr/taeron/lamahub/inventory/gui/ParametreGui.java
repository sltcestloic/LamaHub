package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.taeron.core.util.ItemBuilder;
import fr.taeron.lamahub.LamaHub;

public class ParametreGui {

	private static Inventory i;
	
	public ParametreGui(){
		i = Bukkit.createInventory(null, 9, "§9Paramètres");
		i.setItem(2, new ItemBuilder(Material.JUKEBOX).displayName("§6§lSons").lore("§c➤ §f Clique droit/gauche").build());
		i.setItem(6, new ItemBuilder(Material.SIGN).displayName("§6§lLiens utiles").lore("§c➤ §f Clique droit/gauche").build());
	}
	
	public static void open(Player p, String ign){
		p.openInventory(i);
		Bukkit.getScheduler().runTaskLater(LamaHub.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				p.getOpenInventory().setItem(4, new ItemBuilder(Material.SKULL_ITEM).displayName("§c§l"+ ign).build());
			}
		}, 1L);
	}
	
	public static String title(){
		return i.getTitle();
	}
}
