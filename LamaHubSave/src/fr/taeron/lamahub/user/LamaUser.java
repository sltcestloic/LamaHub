package fr.taeron.lamahub.user;

import org.bukkit.configuration.serialization.*;
import java.util.*;
import com.google.common.collect.*;

public class LamaUser implements ConfigurationSerializable{
	
	
    private final UUID uniqueId;
    private int kills;
    private int deaths;
    private boolean scoreboard;
    private int bestStreak;

    public LamaUser(final UUID uniqueId) {
        this.uniqueId = uniqueId;
        this.scoreboard = true;
    }
    
    public LamaUser(final Map<String, Object> map) {
        this.uniqueId = UUID.fromString((String) map.get("uniqueID"));
        this.kills = Integer.valueOf((String) map.get("kills"));
        this.deaths = Integer.valueOf((String) map.get("deaths"));
        this.scoreboard = Boolean.valueOf((String) map.get("toggledScoreboard"));
        this.bestStreak = Integer.valueOf((String) map.get("bestStreak"));
    }
    
    public Map<String, Object> serialize() {
        final Map<String, Object> map = Maps.newHashMapWithExpectedSize(4);
        map.put("uniqueID", this.uniqueId.toString());
        map.put("kills", this.kills);
        map.put("deaths", this.deaths);
        map.put("toggledScoreboard", this.scoreboard);
        map.put("bestStreak", this.bestStreak);
        return map;
    }
    
    public boolean hasScoreboardEnabled(){
    	return this.scoreboard;
    }
    
    public void toggleScoreboard(){
    	this.scoreboard = !this.scoreboard;
    }
    
    public UUID getUniqueId() {
        return this.uniqueId;
    }
    
    public int getKills() {
        return this.kills;
    }
    
    public void setKills(final int kills) {
        this.kills = kills;
    }
    
    public int getDeaths() {
        return this.deaths;
    }
    
    public void setDeaths(final int deaths) {
        this.deaths = deaths;
    }
    
    public double getKDR(){
    	return this.kills / this.deaths;
    }
}
