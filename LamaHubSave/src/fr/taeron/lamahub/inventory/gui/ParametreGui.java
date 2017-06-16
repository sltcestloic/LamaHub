package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import fr.taeron.core.util.ItemBuilder;

public class ParametreGui{

	private static Inventory i;
	
	public ParametreGui(){
		i = Bukkit.createInventory(null, 9, "§9Paramètres");
		i.setItem(1, new ItemBuilder(Material.INK_SACK).data((short) 10).displayName("§aNotifications (Activé)").lore("§f➤ §7 Lorsque quelqu'un vous mentionne vous entendrez un petit §9'§nding§r§9'").build());
		i.setItem(3, new ItemBuilder(Material.INK_SACK).data((short) 8).displayName("§6   ✘").lore("§f➤ §7 Sera ajouté plus tard.").build());
		i.setItem(5, new ItemBuilder(Material.INK_SACK).data((short) 8).displayName("§6   ✘").lore("§f➤ §7 Sera ajouté plus tard.").build());
		i.setItem(7, new ItemBuilder(Material.INK_SACK).data((short) 8).displayName("§6   ✘").lore("§f➤ §7 Sera ajouté plus tard.").build());
	}
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}
}