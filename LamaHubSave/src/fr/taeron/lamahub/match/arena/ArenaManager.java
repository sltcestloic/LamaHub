package fr.taeron.lamahub.match.arena;

import java.util.*;

public interface ArenaManager
{
    List<Arena> getArenas();
    
    List<String> getArenaNames();
    
    Arena getArena(String p0);
    
    boolean containsArena(Arena p0);
    
    boolean createArena(Arena p0);
    
    boolean removeArena(Arena p0);
    
    Arena getRandomArena();
    
    void reloadArena();
    
    void saveArena();
    
}
