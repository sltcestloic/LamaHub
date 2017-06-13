package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import fr.taeron.core.util.ItemBuilder;

public class HatGui {
	
	private static Inventory i;
	
	public HatGui(){
		i = Bukkit.createInventory(null, 9*5, "§6HatGui (1/2)");
		
		i.setItem(25, new ItemBuilder(Material.ARROW).displayName("§6Page suivante ->").build());
		
		i.setItem(10, new ItemBuilder(Material.QUARTZ_STAIRS).displayName("§eEscalier de Quartz").build());
		i.setItem(11, new ItemBuilder(Material.EMERALD_BLOCK).displayName("§eBloc d'Emeraude").build());
		i.setItem(12, new ItemBuilder(Material.MELON_BLOCK).displayName("§eMelon").build());
		i.setItem(13, new ItemBuilder(Material.PUMPKIN).displayName("§eCitrouille").build());
		i.setItem(14, new ItemBuilder(Material.HAY_BLOCK).displayName("§eBloc de foin").build());
		i.setItem(19, new ItemBuilder(Material.REDSTONE_BLOCK).displayName("§eBloc de redstone").build());
		i.setItem(20, new ItemBuilder(Material.DIAMOND_BLOCK).displayName("§eBloc de diamand").build());
		i.setItem(21, new ItemBuilder(Material.BOOKSHELF).displayName("§eEtagère de livre").build());
		i.setItem(22, new ItemBuilder(Material.NOTE_BLOCK).displayName("§eBloc de note").build());
		i.setItem(23, new ItemBuilder(Material.STAINED_GLASS).data((short) 3).displayName("§ePanneau de verre (Bleu)").build());
		i.setItem(28, new ItemBuilder(Material.STAINED_GLASS).data((short) 5).displayName("§ePanneau de verre (Vert)").build());
		i.setItem(29, new ItemBuilder(Material.STAINED_GLASS).data((short) 14).displayName("§ePanneau de verre (Rouge)").build());
		i.setItem(30, new ItemBuilder(Material.STAINED_GLASS).data((short) 4).displayName("§ePanneau de verre (Jaune)").build());
		i.setItem(31, new ItemBuilder(Material.STAINED_GLASS).data((short) 1).displayName("§ePanneau de verre (Orange)").build());
		i.setItem(32, new ItemBuilder(Material.STAINED_GLASS).data((short) 15).displayName("§ePanneau de verre (Noir)").build());
		
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
