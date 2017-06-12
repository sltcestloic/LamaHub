package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.taeron.core.util.ItemBuilder;

public class HatGuiPage2 {
	
private static Inventory i;
	
	public HatGuiPage2(){
		i = Bukkit.createInventory(null, 9*3, "§6HatGui | Page n°2");
		i.setItem(16, new ItemBuilder(Material.ARROW).displayName("§6Page précédente").build());
		i.setItem(10, new ItemBuilder(Material.TNT).displayName("§eTnt").build());
		i.setItem(11, new ItemBuilder(Material.SPONGE).displayName("§eEponge").build());
		i.setItem(12, new ItemBuilder(Material.DEAD_BUSH).displayName("§eFeuille morte").build());
		i.setItem(13, new ItemBuilder(Material.VINE).displayName("§eVignes").build());
		i.setItem(14, new ItemBuilder(Material.ENCHANTMENT_TABLE).displayName("§eTable d'enchantement").build());
		for(int a = 0; a<3; a++){
			i.setItem(a, new ItemBuilder(Material.STAINED_GLASS_PANE).data((short)14).displayName(" ").build());
		}
		for(int a = 3; a<7; a++){
			i.setItem(a, new ItemBuilder(Material.STAINED_GLASS_PANE).data((short)1).displayName(" ").build());
		}
		for(int a = 7; a<10; a++){
			i.setItem(a, new ItemBuilder(Material.STAINED_GLASS_PANE).data((short)14).displayName(" ").build());
		}
		for(int a = 17; a<20; a++){
			i.setItem(a, new ItemBuilder(Material.STAINED_GLASS_PANE).data((short)14).displayName(" ").build());
		}
		for(int a = 20; a<24; a++){
			i.setItem(a, new ItemBuilder(Material.STAINED_GLASS_PANE).data((short)1).displayName(" ").build());
		}
		for(int a = 24; a<27; a++){
			i.setItem(a, new ItemBuilder(Material.STAINED_GLASS_PANE).data((short)14).displayName(" ").build());
		}
	}
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}

}
