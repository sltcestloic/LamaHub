package fr.taeron.lamahub.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.taeron.lamahub.LamaHub;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;

public class NicksCache{
	
    private static Map<UUID, String> nicknames;
    private static Map<UUID, String> skinvalue;
    private static Map<UUID, String> realnames;
    
    static {
        NicksCache.nicknames = new HashMap<UUID, String>();
        NicksCache.skinvalue = new HashMap<UUID, String>();
        NicksCache.realnames = new HashMap<UUID, String>();
    }
    
    public static String getNick(UUID id) {
        if (isNicked(id)) {
            return NicksCache.nicknames.get(id);
        }
        return "No nick name";
    }
    
    public static boolean isNicked(UUID id) {
        return NicksCache.nicknames.containsKey(id);
    }
    
    @SuppressWarnings("deprecation")
	public static void setNick(Player p, String string) {
        NicksCache.nicknames.put(p.getUniqueId(), string);
        ((CraftPlayer)p).setDisplayName(getNick(p.getUniqueId()));
        p.setDisplayName(p.getName());
        if(p.getName().length() > 14){
            p.setPlayerListName("§7" + p.getName().substring(0, 14));
        } else {
     	  p.setPlayerListName("§7" + p.getName());
        }
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.hidePlayer(p);
        }
        new BukkitRunnable() {
            public void run() {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.showPlayer(p);
                }
            }
        }.runTaskLater(LamaHub.getInstance(), 3L);
    }
    
    @SuppressWarnings("unused")
	private static String getSkinValue(Player p) {
        if (NicksCache.skinvalue.containsKey(p.getUniqueId())) {
            return NicksCache.skinvalue.get(p.getUniqueId());
        }
        return null;
    }
    
    @SuppressWarnings("deprecation")
	public static void unNick(Player p) {
        if (!isNicked(p.getUniqueId())) {
            return;
        }
        NicksCache.nicknames.remove(p.getUniqueId());
        ((CraftPlayer)p).setDisplayName(getRealName(p.getUniqueId()));
        p.setDisplayName(getRealName(p.getUniqueId()));
        p.setPlayerListName(getRealName(p.getUniqueId()));
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.hidePlayer(p);
        }
        new BukkitRunnable() {
			public void run() {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.showPlayer(p);
                }
            }
        }.runTaskLater(LamaHub.getInstance(), 3L);
    }
    
    public static String getRealName(UUID id) {
        return NicksCache.realnames.get(id);
    }
    
    public static void setRealName(UUID uniqueId, String name) {
        NicksCache.realnames.put(uniqueId, name);
    }
}
