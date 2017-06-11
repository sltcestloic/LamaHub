package fr.taeron.lamahub;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import fr.taeron.core.util.ItemBuilder;

public class Config {

	public static ItemStack SPAWN_COMPASS_ITEM;
	public static ItemStack FFA_SELECTOR_ITEM;
	
	static{
		SPAWN_COMPASS_ITEM = new ItemBuilder(Material.COMPASS).displayName("§bMenu Principal").build();
		FFA_SELECTOR_ITEM = new ItemBuilder(Material.STONE_SWORD).displayName("§bKit").build();
	}
}
