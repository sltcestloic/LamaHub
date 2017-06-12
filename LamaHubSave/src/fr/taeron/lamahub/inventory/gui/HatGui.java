package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import fr.taeron.core.util.ItemBuilder;

public class HatGui {
	
	private static Inventory i;
	
	public HatGui(){
		i = Bukkit.createInventory(null, 9*3, "§6HatGui");
		i.setItem(16, new ItemBuilder(Material.ARROW).displayName("§6Page suivante ->").build());
		i.setItem(10, new ItemBuilder(Material.WEB).displayName("§eCobweb").build());
		i.setItem(11, new ItemBuilder(Material.MELON_BLOCK).displayName("§eMelon").build());
		i.setItem(12, new ItemBuilder(Material.GLASS).displayName("§eGlass").build());
		i.setItem(13, new ItemBuilder(Material.REDSTONE_TORCH_ON).displayName("§eRedstone Torch").build());
		i.setItem(14, new ItemBuilder(Material.COAL_BLOCK).displayName("§eCoal").build());
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
