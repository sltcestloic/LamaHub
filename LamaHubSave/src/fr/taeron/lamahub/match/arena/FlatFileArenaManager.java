package fr.taeron.lamahub.match.arena;

import java.util.*;

import org.bukkit.plugin.java.*;

import fr.taeron.core.util.Config;
import fr.taeron.core.util.GenericUtils;
import fr.taeron.lamahub.LamaHub;

public class FlatFileArenaManager implements ArenaManager{
	
    private final LamaHub plugin;
    private List<Arena> arenas;
    private Config config;
    
    public FlatFileArenaManager(final LamaHub plugin) {
        this.arenas = new ArrayList<Arena>();
        this.plugin = plugin;
        this.reloadArena();
    }
    
    @Override
    public List<Arena> getArenas() {
        return this.arenas;
    }
    
    @Override
    public List<String> getArenaNames() {
        final List<String> results = new ArrayList<String>(this.arenas.size());
        for (final Arena arena : this.arenas) {
            results.add(arena.getName());
        }
        return results;
    }
    
    @Override
    public Arena getArena(final String name) {
        for (final Arena arena : this.arenas) {
            if (name.equalsIgnoreCase(arena.getName())) {
                return arena;
            }
        }
        return null;
    }
    
    @Override
    public boolean containsArena(final Arena arena) {
        return this.arenas.contains(arena);
    }
    
    @Override
    public boolean createArena(final Arena arena) {
        return this.arenas.add(arena);
    }
    
    @Override
    public boolean removeArena(final Arena arena) {
        return this.arenas.remove(arena);
    }
    
    @Override
    public Arena getRandomArena() {
        final List<Arena> sorted = new ArrayList<Arena>(this.arenas);
        Collections.shuffle(sorted);
        for(Arena a : sorted){
        	return a;
        }      
        return null;
    }
    
    @Override
    public void reloadArena() {
        this.config = new Config((JavaPlugin)this.plugin, "arena");
        this.arenas = GenericUtils.createList(this.config.get("arena"), Arena.class);
    }
    
    @Override
    public void saveArena() {
        this.config.set("arena", (Object)this.arenas);
        this.config.save();
    }
}
