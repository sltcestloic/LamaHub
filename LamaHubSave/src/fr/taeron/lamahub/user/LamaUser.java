package fr.taeron.lamahub.user;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.*;
import org.bukkit.entity.Player;

import java.util.*;
import com.google.common.collect.*;

public class LamaUser implements ConfigurationSerializable{
	
	
    private final UUID uniqueId;
    private int kills;
    private int deaths;
    private int ks;
    private int bestStreak;
    private String prefix;
    private int coins;
    private String currentKit;
    private Player lastAttacker;
    private long lastKangaroo;
    private boolean notification;

    public LamaUser(final UUID uniqueId) {
        this.uniqueId = uniqueId;
        this.prefix = "§7";
        this.coins = 0;
        this.currentKit = "Aucun";
        this.lastKangaroo = System.currentTimeMillis();
        this.notification = true;
    }

	public LamaUser(final Map<String, Object> map) {
        this.uniqueId = UUID.fromString(String.valueOf(map.get("uniqueID")));
        this.kills = Integer.valueOf(String.valueOf(map.get("kills")));
        this.deaths = Integer.valueOf(String.valueOf(map.get("deaths")));
        this.bestStreak = Integer.valueOf(String.valueOf(map.get("bestStreak")));
        this.prefix = String.valueOf(map.get("prefix"));
        this.coins = Integer.valueOf(String.valueOf(map.get("coins")));
        this.currentKit = String.valueOf(map.get("currentKit"));
        this.lastKangaroo = System.currentTimeMillis();
    }
    
    public Map<String, Object> serialize() {
        final Map<String, Object> map = Maps.newHashMapWithExpectedSize(4);
        map.put("uniqueID", this.uniqueId.toString());
        map.put("kills", this.kills);
        map.put("deaths", this.deaths);
        map.put("bestStreak", this.bestStreak);
        map.put("prefix", this.prefix);
        map.put("coins", this.coins);
        map.put("currentKit", this.currentKit);
        return map;
    }
    
    public boolean isNotificationEnabled() {
		return notification;
	}

	public void setNotification(boolean notification) {
		this.notification = notification;
	}
	
    public long lastKangarooTime(){
    	return this.lastKangaroo;
    }
    
    public void setLastKangarooTime(long l){
    	this.lastKangaroo = l;
    }
    
    public void setLastAttacker(Player p){
    	this.lastAttacker = p;
    }
    
    public Player getLastAttacker(){
    	return this.lastAttacker;
    }
    
    public void setCurrentKit(String s){
    	this.currentKit = s;
    }
    
    public String getCurrentKitName(){
    	return this.currentKit;
    }
    
    public UUID getUniqueId() {
        return this.uniqueId;
    }
    
    public void setBestKS(int i){
    	this.bestStreak = i;
    }
    
    public void addCoins(int i){
    	this.coins += i;
    }
    
    public int getCoins(){
    	return this.coins;
    }
    
    public void removeCoins(int i){
    	this.coins -= i;
    }
    
    public void setCoins(int i){
    	this.coins = i;
    }
    
    public int getKills() {
        return this.kills;
    }
    
    public void setPrefix(String s){
    	this.prefix = s;
    }
    
    public String getPrefix(){
    	if(!Bukkit.getPlayer(this.uniqueId).hasPermission("vip")){
    		return "§7";
    	}
    	return this.prefix;
    }
    
    public void setKills(final int kills) {
        this.kills = kills;
    }
    
    public int getDeaths() {
        return this.deaths;
    }
    
    public void setKS(int i){
    	this.ks = i;
    	if(i > this.bestStreak){
    		this.bestStreak = i;
    	}
    }
    
    public int getBestKS(){
    	return this.bestStreak;
    }
    
    public int getKS(){
    	return this.ks;
    }
    
    public void setDeaths(final int deaths) {
        this.deaths = deaths;
    }
    
    public String getKDRAsString(){
    	final double ratio = (this.getDeaths() > 0) ? (this.getKills() / this.getDeaths()) : ((double)this.getKills());
        final String ratioString = (String.valueOf(ratio).length() > 4) ? String.valueOf(ratio).substring(0, 4) : String.valueOf(ratio);
        return ratioString;
    }
    
    public double getKDR(){
    	return (this.getDeaths() > 0) ? (this.getKills() / this.getDeaths()) : ((double)this.getKills());
    }
}
