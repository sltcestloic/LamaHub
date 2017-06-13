package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.taeron.core.util.ItemBuilder;

public class HatGuiPage2 {
	
	private static Inventory i;
	
	public HatGuiPage2(){
		i = Bukkit.createInventory(null, 9*5, "§6HatGui (2/2)");
		
		i.setItem(25, new ItemBuilder(Material.ARROW).displayName("§6Page précédente").build());
		
		i.setItem(10, new ItemBuilder(Material.ENCHANTMENT_TABLE).displayName("§eTable d'enchantement").build());
		i.setItem(11, new ItemBuilder(Material.COBBLE_WALL).displayName("§eMur en pierre").build());
		i.setItem(12, new ItemBuilder(Material.STEP).data((short)7).displayName("§eDalle de quartz").build());
		i.setItem(13, new ItemBuilder(Material.STEP).displayName("§eDalle de pierre").build());
		i.setItem(14, new ItemBuilder(Material.SNOW).displayName("§eNeige").build());
		i.setItem(19, new ItemBuilder(Material.TRAP_DOOR).displayName("§eTrappe").build());
		i.setItem(20, new ItemBuilder(Material.TRIPWIRE_HOOK).displayName("§eCrochet").build());
		i.setItem(21, new ItemBuilder(Material.FENCE_GATE).displayName("§ePortail").build());
		i.setItem(22, new ItemBuilder(Material.DISPENSER).displayName("§eDispenser").build());
		i.setItem(23, new ItemBuilder(Material.PISTON_STICKY_BASE).displayName("§ePiston collant").build());
		i.setItem(28, new ItemBuilder(Material.IRON_PLATE).displayName("§ePlaque de pression en fer").build());
		i.setItem(29, new ItemBuilder(Material.BEACON).displayName("§eBeacon").build());
		i.setItem(30, new ItemBuilder(Material.LADDER).displayName("§eEchelle").build());
		i.setItem(31, new ItemBuilder(Material.WATER_LILY).displayName("§eNenuphar").build());
		i.setItem(32, new ItemBuilder(Material.ANVIL).displayName("§eEnclume").build());
		
		for(int a = 0; a<3; a++){
			i.setItem(a, new ItemBuilder(Material.STAINED_GLASS_PANE).data((short)14).displayName(" ").build());
		}
		for(int a = 3; a<7; a++){
			i.setItem(a, new ItemBuilder(Material.STAINED_GLASS_PANE).data((short)1).displayName(" ").build());
		}
		for(int a = 7; a<10; a++){
			i.setItem(a, new ItemBuilder(Material.STAINED_GLASS_PANE).data((short)14).displayName(" ").build());
		}
		for(int a = 17; a<19; a++){
			i.setItem(a, new ItemBuilder(Material.STAINED_GLASS_PANE).data((short)14).displayName(" ").build());
		}
		for(int a = 26; a<28; a++){
			i.setItem(a, new ItemBuilder(Material.STAINED_GLASS_PANE).data((short)14).displayName(" ").build());
		}
		for(int a = 35; a<38; a++){
			i.setItem(a, new ItemBuilder(Material.STAINED_GLASS_PANE).data((short)14).displayName(" ").build());
		}
		for(int a = 38; a<43; a++){
			i.setItem(a, new ItemBuilder(Material.STAINED_GLASS_PANE).data((short)1).displayName(" ").build());
		}
		for(int a = 42; a<45; a++){			
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