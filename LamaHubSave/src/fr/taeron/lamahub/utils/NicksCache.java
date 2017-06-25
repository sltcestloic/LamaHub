package fr.taeron.lamahub.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.taeron.lamahub.LamaHub;

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
    
    @SuppressWarnings({ "deprecation", "unchecked" })
	public static void setNick(Player p, String string) {
        NicksCache.nicknames.put(p.getUniqueId(), string);
        Object handle = NMSUtils.getHandle(p);
        Object profile = NMSUtils.invokeMethod(handle, NMSUtils.getMethod(handle.getClass(), "getProfile", (Class<?>[])new Class[0]));
        NMSUtils.setField(profile, NMSUtils.getField(NMSUtils.getMojangClass("GameProfile"), "name"), getNick(p.getUniqueId()));
        p.setDisplayName(p.getName());
        p.setPlayerListName(p.getName());
        Object pmap = NMSUtils.invokeMethod(profile, NMSUtils.getMethod(profile.getClass(), "getProperties", (Class<?>[])new Class[0]));
        System.out.println(pmap);
        Map<Object, Collection<?>> map = (Map<Object, Collection<?>>)NMSUtils.invokeMethod(pmap, NMSUtils.getMethod(NMSUtils.getMojangClass("properties.PropertyMap"), "asMap", (Class<?>[])new Class[0]));
        Collection<?> textures = map.get("textures");
        if (textures != null) {
            Object pro = textures.iterator().next();
            if (!NicksCache.skinvalue.containsKey(p.getUniqueId())) {
                try {
                    NicksCache.skinvalue.put(p.getUniqueId(), (String)NMSUtils.getField(NMSUtils.getMojangClass("properties.Property"), "value").get(pro));
                }
                catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            NMSUtils.setField(pro, NMSUtils.getField(NMSUtils.getMojangClass("properties.Property"), "value"), "random");
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
    
    private static String getSkinValue(Player p) {
        if (NicksCache.skinvalue.containsKey(p.getUniqueId())) {
            return NicksCache.skinvalue.get(p.getUniqueId());
        }
        return null;
    }
    
    @SuppressWarnings({ "deprecation", "unchecked" })
	public static void unNick(Player p) {
        if (!isNicked(p.getUniqueId())) {
            return;
        }
        NicksCache.nicknames.remove(p.getUniqueId());
        Object handle = NMSUtils.getHandle(p);
        Object profile = NMSUtils.invokeMethod(handle, NMSUtils.getMethod(handle.getClass(), "getProfile", (Class<?>[])new Class[0]));
        NMSUtils.setField(profile, NMSUtils.getField(NMSUtils.getMojangClass("GameProfile"), "name"), getRealName(p.getUniqueId()));
        p.setDisplayName(getRealName(p.getUniqueId()));
        p.setPlayerListName(getRealName(p.getUniqueId()));
        Object pmap = NMSUtils.invokeMethod(profile, NMSUtils.getMethod(profile.getClass(), "getProperties", (Class<?>[])new Class[0]));
        Map<Object, Collection<?>> map = (Map<Object, Collection<?>>) NMSUtils.invokeMethod(pmap, NMSUtils.getMethod(NMSUtils.getMojangClass("properties.PropertyMap"), "asMap", (Class<?>[])new Class[0]));
        Collection<?> textures = map.get("textures");
        if (textures != null) {
            Object pro = textures.iterator().next();
            NMSUtils.setField(pro, NMSUtils.getField(NMSUtils.getMojangClass("properties.Property"), "value"), getSkinValue(p));
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
    
    public static String getRealName(UUID id) {
        return NicksCache.realnames.get(id);
    }
    
    public static void setRealName(UUID uniqueId, String name) {
        NicksCache.realnames.put(uniqueId, name);
    }
}
