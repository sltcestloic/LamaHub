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
		i = Bukkit.createInventory(null, 9, "§9Choix du kit");
		i.setItem(0, new ItemBuilder(Material.STONE_SWORD).displayName("§9Guerrier").lore("Kit de base, rien de spécial").build());
		i.setItem(1, new ItemBuilder(Material.DIAMOND_HELMET).displayName("§9AntiStomper").lore("Les dégâts des stompers ne t'infligent que 2", "coeurs de dégâts").build());
		i.setItem(2, new ItemBuilder(Material.STICK).displayName("§9Violeur").enchant(Enchantment.KNOCKBACK, 2).lore("Spawn avec un baton knockback 2").build());
		i.setItem(3, new ItemBuilder(Material.ANVIL).displayName("§9Stomper").lore("Ne prend que 2 coeurs de dégats de chute maximum", "et inflige les dégâts réels de la chute aux joueurs aux alentours").build());
		i.setItem(4, new ItemBuilder(Material.FIREWORK).displayName("§9Kangaroo").lore("Spawn avec une fusée qui te propulse quand tu click droit dessus.", "La poussée horizontale est doublée si tu es en sneak").build());
	}
	
	public static void open(Player p){
		p.openInventory(i);
	}
	
	public static String title(){
		return i.getTitle();
	}
}
