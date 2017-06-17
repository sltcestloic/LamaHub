package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import fr.taeron.core.util.ItemBuilder;

public class SonsGui{

	private static Inventory i;
	
	public SonsGui(){
		i = Bukkit.createInventory(null, 9, "§9Paramètres §7(Sons)");
		i.setItem(1, new ItemBuilder(Material.INK_SACK).data((short) 10).displayName("§aMentions (Activé)").lore("§f➔ §7 Lorsque quelqu'un vous mentionne vous entendrez un petit §9'§nding§r§9'").build());
		i.setItem(3, new ItemBuilder(Material.INK_SACK).data((short) 8).displayName("§7Prochainement").lore("§f➔ §7 Sera ajouté plus tard.").build());
		i.setItem(5, new ItemBuilder(Material.INK_SACK).data((short) 8).displayName("§7Prochainement").lore("§f➔ §7 Sera ajouté plus tard.").build());
		i.setItem(7, new ItemBuilder(Material.INK_SACK).data((short) 8).displayName("§7Prochainement").lore("§f➔ §7 Sera ajouté plus tard.").build());
	}
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}
}