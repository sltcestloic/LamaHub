package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.taeron.core.util.ItemBuilder;

public class CommandUtilsGui {

	private static Inventory i;
	
	public CommandUtilsGui(){
		i = Bukkit.createInventory(null, 9, "§9Paramètres §7(Commandes)");
		i.setItem(2, new ItemBuilder(Material.PAPER).displayName("§c/report").lore("§f➔ §7 Permet de signaler un joueur à notre equipe de modération.").build());
		i.setItem(4, new ItemBuilder(Material.PAPER).displayName("§c/color §9(§7VIP ONLY§9)").lore("§f➔ §7 Permet de changer la couleur de son pseudo.").build());
		i.setItem(6, new ItemBuilder(Material.PAPER).displayName("§c/trail §9(§7VIP ONLY§9)").lore("§f➔ §7 Permet d'avoir des particules autour de son joueur.").build());
	}
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}
}
