package fr.taeron.lamahub;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.TimeZone;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import fr.taeron.core.util.ItemBuilder;
import net.minecraft.util.org.apache.commons.lang3.time.FastDateFormat; 

public class Config {

	public static ItemStack SPAWN_COMPASS_ITEM;
	public static ItemStack FFA_SELECTOR_ITEM;
	public static ItemStack TRAILS_ITEM;
	public static ItemStack HAT_ITEM;
	public static ItemStack FRIENDS_ITEM; 
	public static ItemStack SETTINGS_ITEM;
	public static ItemStack UNRANKED_ITEM;
	public static ItemStack RANKED_ITEM;
	public static ItemStack QUEUE_LEAVE_ITEM;
	public static ItemStack LOBBY_ITEM;
	public static final TimeZone SERVER_TIME_ZONE;
    public static final ThreadLocal<DecimalFormat> REMAINING_SECONDS;
    public static final ThreadLocal<DecimalFormat> REMAINING_SECONDS_TRAILING;
    public static final FastDateFormat MIN_SECS;
    public static final FastDateFormat DAY_MTH_YR_HR_MIN_AMPM;
	
	static{
		SPAWN_COMPASS_ITEM = new ItemBuilder(Material.COMPASS).displayName("§bMenu Principal").build();
		FFA_SELECTOR_ITEM = new ItemBuilder(Material.CHEST).displayName("§bKit").build();
		TRAILS_ITEM = new ItemBuilder(Material.REDSTONE).displayName("§bParticules").build();
		HAT_ITEM = new ItemBuilder(Material.NETHER_STAR).displayName("§bChapeaux").build();
		SETTINGS_ITEM = new ItemBuilder(Material.NAME_TAG).displayName("§bParametres").build();
		UNRANKED_ITEM = new ItemBuilder(Material.IRON_SWORD).displayName("§9Unranked").build();
		RANKED_ITEM = new ItemBuilder(Material.DIAMOND_SWORD).displayName("§aRanked").build();
		QUEUE_LEAVE_ITEM = new ItemBuilder(Material.REDSTONE).displayName("§cQuitter la queue").build();
		LOBBY_ITEM = new ItemBuilder(Material.BED).displayName("§bRetour au lobby").build();
		SERVER_TIME_ZONE = TimeZone.getTimeZone("Europe/Copenhagen");
        REMAINING_SECONDS = new ThreadLocal<DecimalFormat>() {
            @Override
            protected DecimalFormat initialValue() {
                return new DecimalFormat("0.#");
            }
        };
        REMAINING_SECONDS_TRAILING = new ThreadLocal<DecimalFormat>() {
            @Override
            protected DecimalFormat initialValue() {
                return new DecimalFormat("0.0");
            }
        };
        MIN_SECS = FastDateFormat.getInstance("mm:ss", Config.SERVER_TIME_ZONE, Locale.FRANCE);
        DAY_MTH_YR_HR_MIN_AMPM = FastDateFormat.getInstance("dd/MM/yy hh:mma", Config.SERVER_TIME_ZONE, Locale.FRANCE);
	}
}
