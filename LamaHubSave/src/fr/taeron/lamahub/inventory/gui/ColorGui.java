package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.taeron.core.util.ItemBuilder;

public class ColorGui {

	private static Inventory i;
	
	public ColorGui(){
		i = Bukkit.createInventory(null, 18, "§aChoisis une couleur");
		i.setItem(0, new ItemBuilder(Material.WOOL).data((short)1).displayName("§6Orange").build());
		i.setItem(1, new ItemBuilder(Material.WOOL).data((short)11).displayName("§1Bleu").build());
		i.setItem(2, new ItemBuilder(Material.WOOL).data((short)3).displayName("§bAqua").build());
		i.setItem(3, new ItemBuilder(Material.WOOL).data((short)9).displayName("§3Cyan").build());
		i.setItem(4, new ItemBuilder(Material.WOOL).data((short)13).displayName("§2Vert").build());
		i.setItem(5, new ItemBuilder(Material.WOOL).data((short)5).displayName("§aVert Clair").build());
		i.setItem(6, new ItemBuilder(Material.WOOL).data((short)6).displayName("§dRose").build());
		i.setItem(7, new ItemBuilder(Material.WOOL).data((short)4).displayName("§eJaune").build());
		i.setItem(8, new ItemBuilder(Material.WOOL).data((short)14).displayName("§cRouge").build());
		i.setItem(9, new ItemBuilder(Material.WOOL).data((short)8).displayName("§7Gris").build());
	}
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}
}
