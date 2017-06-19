package fr.taeron.lamahub.inventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.taeron.core.util.ItemBuilder;

public class KitGui {

private static Inventory i;
	
	public KitGui(){
		i = Bukkit.createInventory(null, 9, "¬ß9Choix du kit");
		i.setItem(0, new ItemBuilder(Material.STONE_SWORD).displayName("ß9Guerrier").lore("Kit de base, rien de sp√©cial").build());
		i.setItem(1, new ItemBuilder(Material.DIAMOND_HELMET).displayName("ß9AntiStomper").lore("Les d√©g√¢ts des stompers ne t'infligent que 2", "coeurs de d√©g√¢ts").build());
		i.setItem(2, new ItemBuilder(Material.STICK).displayName("ß9Violeur").enchant(Enchantment.KNOCKBACK, 2).lore("Spawn avec un baton knockback 2").build());
		i.setItem(3, new ItemBuilder(Material.ANVIL).displayName("ß9Stomper").lore("Ne prend que 2 coeurs de d√©gats de chute maximum", "et inflige les d√©g√¢ts r√©els de la chute aux joueurs aux alentours").build());
		i.setItem(4, new ItemBuilder(Material.FIREWORK).displayName("ß9Kangaroo").lore("Spawn avec une fus√©e qui te propulse quand tu click droit dessus.", "La pouss√©e horizontale est doubl√©e si tu es en sneak").build());
		i.setItem(5, new ItemBuilder(Material.POTION).data((short)16388).displayName("ß9Viper").lore("Une chance sur trois de mettre poison 1", "pendant 5 secondes √† ton adversaire").build());
		i.setItem(6, new ItemBuilder(Material.WOOD_AXE).displayName("ß9Thor").lore("Un coup de thor spawn un block de netherrack"," un second coup sur le block de netherrack", "crÈe une explosion fatal pour les joueurs", "dans un rayon de 3 blocks").build());
	}
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}
}
