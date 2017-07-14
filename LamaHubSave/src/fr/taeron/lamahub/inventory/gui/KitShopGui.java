package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.taeron.core.util.ItemBuilder;

public class KitShopGui {

	
	private static Inventory i;
	
	public KitShopGui(){
		i = Bukkit.createInventory(null, 9, "§9Acheter un kit");
		i.setItem(0, new ItemBuilder(Material.ANVIL).displayName("§9Stomper").lore("Ne prend que 2 coeurs de dégats de chute maximum", "et inflige les dégâts réels de la chute aux joueurs aux alentours", " ", "§2Prix: §62500 LamaCoins").build());
		i.setItem(1, new ItemBuilder(Material.FIREWORK).displayName("§9Kangaroo").lore("Spawn avec une fusée qui te propulse quand tu click droit dessus.", "La poussée horizontale est doublée si tu es en sneak", " ", "§2Prix: §62000 LamaCoins").build());
		i.setItem(2, new ItemBuilder(Material.POTION).data((short)16388).displayName("§9Viper").lore("Une chance sur trois de mettre poison 1", "pendant 5 secondes à ton adversaire", "", "§2Prix: §62000 LamaCoins").build());
		i.setItem(3, new ItemBuilder(Material.WOOD_AXE).displayName("§9Thor").lore("Un coup de thor spawn un block de netherrack"," un second coup sur le block de netherrack", "crée une explosion fatal pour les joueurs", "dans un rayon de 3 blocks", " ", "§2Prix: §62500 LamaCoins").build());
	}
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}
}
