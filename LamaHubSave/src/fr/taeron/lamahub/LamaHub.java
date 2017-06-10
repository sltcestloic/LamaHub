package fr.taeron.lamahub;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.taeron.lamahub.inventory.InventoryHandler;
import fr.taeron.lamahub.scoreboard.ScoreboardHandler;
import fr.taeron.lamahub.user.UserManager;

public class LamaHub extends JavaPlugin{

	private UserManager userManager;
	private static LamaHub instance;
	private ScoreboardHandler scoreboardHandler;
	private InventoryHandler inventoryHandler;
	
	
	
	public void onEnable(){
		this.setInstances();
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
	 
	 private void setInstances(){
		 this.userManager = new UserManager(this);
		 LamaHub.instance = this;
		 this.scoreboardHandler = new ScoreboardHandler(this);
		 this.inventoryHandler = new InventoryHandler(this);
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
