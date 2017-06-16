package fr.taeron.lamahub.match.arena;

import org.bukkit.configuration.serialization.*;
import java.util.*;
import com.google.common.collect.*;
import fr.taeron.core.util.PersistableLocation;

import org.bukkit.*;

public class Arena implements ConfigurationSerializable
{ 
    private String name;
    private PersistableLocation point1;
    private PersistableLocation point2;
    
    public Arena(final String name) {
        this.name = name;
        this.point1 = null;
        this.point2 = null;
    }
    
    public Arena(final Map<String, Object> map) {
        this.name = (String) map.get("name");
        this.point1 = (PersistableLocation) map.get("point1");
        this.point2 = (PersistableLocation) map.get("point2");
    }
    
    public Map<String, Object> serialize() {
        final Map<String, Object> map = Maps.newHashMap();
        map.put("name", this.name);
        map.put("point1", this.point1);
        map.put("point2", this.point2);
        return map;
    }
    
    public String getName() {
        return this.name;
    }
    
    public World getWorld() {
        if (this.point1.getWorld().equals(this.point2.getWorld())) {
            return this.point1.getWorld();
        }
        return null;
    }
    
    public Location getPoint(final Integer number) {
        if (number <= 1) {
            return this.point1.getLocation();
        }
        return this.point2.getLocation();
    }
    
    public void addPoint(final Integer number, final Location location) {
        if (number <= 1) {
            this.point1 = new PersistableLocation(location);
            return;
        }
        this.point2 = new PersistableLocation(location);
    }
    
    public Location getPoint1() {
        return this.point1.getLocation();
    }
    
    public Location getPoint2() {
        return this.point2.getLocation();
    }
}
