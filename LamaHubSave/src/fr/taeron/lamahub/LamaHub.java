package fr.taeron.lamahub;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.taeron.lamahub.inventory.InventoryHandler;
import fr.taeron.lamahub.inventory.gui.KitGui;
import fr.taeron.lamahub.inventory.gui.MainGui;
import fr.taeron.lamahub.listeners.CoreListener;
import fr.taeron.lamahub.listeners.GUIListener;
import fr.taeron.lamahub.listeners.KDListener;
import fr.taeron.lamahub.listeners.WorldListener;
import fr.taeron.lamahub.scoreboard.ScoreboardHandler;
import fr.taeron.lamahub.user.UserManager;

public class LamaHub extends JavaPlugin{

	private UserManager userManager;
	private static LamaHub instance;
	private ScoreboardHandler scoreboardHandler;
	private InventoryHandler inventoryHandler;
	
	
	
	@SuppressWarnings("deprecation")
	public void onEnable(){
		this.setInstances();
		this.registerListeners();
		this.runAutoSave();
		if(Bukkit.getOnlinePlayers().length > 0){
			for(Player p : Bukkit.getOnlinePlayers()){
				this.inventoryHandler.spawnInventory.applyTo(p, true, true);
			}
		}
	} 
	
	public void onDisable(){
		this.userManager.saveUserDataAsync();
	}
	 
	public void runAutoSave(){
		new BukkitRunnable(){
			public void run(){
				LamaHub.this.userManager.saveUserData();;
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all");
				Command.broadcastCommandMessage(Bukkit.getConsoleSender(), "§aSauvegarde automatique effectuée.");
			}
		}.runTaskTimerAsynchronously(this, 20, 6000);
	}
	
	private void registerListeners(){
		Bukkit.getPluginManager().registerEvents(new CoreListener(), this);
		Bukkit.getPluginManager().registerEvents(new WorldListener(), this);
		Bukkit.getPluginManager().registerEvents(new KDListener(), this);
		Bukkit.getPluginManager().registerEvents(new GUIListener(), this);
	}
	 
	 private void setInstances(){
		 this.userManager = new UserManager(this);
		 LamaHub.instance = this;
		 this.scoreboardHandler = new ScoreboardHandler(this);
		 this.inventoryHandler = new InventoryHandler(this);
		 new MainGui();
		 new KitGui();
	 }
	 
	 public InventoryHandler getInventoryHandler(){
		 return this.inventoryHandler;
	 }
	 
	 public UserManager getUserManager(){
		 return this.userManager;
	 }
	 
	 public ScoreboardHandler getScoreboardHandler(){
		 return this.scoreboardHandler;
	 }
	 
	 public static LamaHub getInstance(){
		 return LamaHub.instance;
	 }
}
