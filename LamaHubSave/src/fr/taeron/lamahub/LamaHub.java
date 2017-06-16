package fr.taeron.lamahub;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import fr.taeron.lamahub.commands.ColorCommand;
import fr.taeron.lamahub.commands.HatCommand;
import fr.taeron.lamahub.commands.SpawnCommand;
import fr.taeron.lamahub.commands.StatsCommand;
import fr.taeron.lamahub.inventory.InventoryHandler;
import fr.taeron.lamahub.inventory.gui.ColorGui;
import fr.taeron.lamahub.inventory.gui.HatGui;
import fr.taeron.lamahub.inventory.gui.HatGuiPage2;
import fr.taeron.lamahub.inventory.gui.KitGui;
import fr.taeron.lamahub.inventory.gui.MainGui;
import fr.taeron.lamahub.inventory.gui.ParametreGui;
import fr.taeron.lamahub.inventory.gui.SonsGui;
import fr.taeron.lamahub.listeners.CoreListener;
import fr.taeron.lamahub.listeners.DamageFixListener;
import fr.taeron.lamahub.listeners.DuelListener;
import fr.taeron.lamahub.listeners.GUIListener;
import fr.taeron.lamahub.listeners.KDListener;
import fr.taeron.lamahub.listeners.WorldListener;
import fr.taeron.lamahub.match.arena.ArenaExecutor;
import fr.taeron.lamahub.match.arena.ArenaManager;
import fr.taeron.lamahub.match.arena.FlatFileArenaManager;
import fr.taeron.lamahub.scoreboard.ScoreboardHandler;
import fr.taeron.lamahub.timer.TimerManager;
import fr.taeron.lamahub.user.UserManager;
import net.minecraft.util.org.apache.commons.lang3.time.DurationFormatUtils;

public class LamaHub extends JavaPlugin{

	private UserManager userManager;
	private static LamaHub instance;
	private ScoreboardHandler scoreboardHandler;
	private InventoryHandler inventoryHandler;
	private TimerManager timerManager;
	private int msg;
	private static final long MINUTE;
    private static final long HOUR;
    private ArenaManager arenaManager;
	
	@SuppressWarnings("deprecation")
	public void onEnable(){
		this.setInstances();
		this.registerListeners();
		this.runAutoSave();
		this.registerCommands();
		msg = 1;
		if(Bukkit.getOnlinePlayers().length > 0){
			for(Player p : Bukkit.getOnlinePlayers()){
				this.inventoryHandler.spawnInventory.applyTo(p, true, true);
			}
		}
	} 
	
	public void onDisable(){
		this.userManager.saveUserData();
	}
	
	public static String getRemaining(final long millis, final boolean milliseconds) {
        return getRemaining(millis, milliseconds, true);
    }
    
    public static String getRemaining(final long duration, final boolean milliseconds, final boolean trail) {
        if (milliseconds && duration < LamaHub.MINUTE) {
            return (trail ? Config.REMAINING_SECONDS_TRAILING : Config.REMAINING_SECONDS).get().format(duration * 0.001) + 's';
        }
        return DurationFormatUtils.formatDuration(duration, ((duration > LamaHub.HOUR) ? "HH:" : "") + "mm:ss");
    }
	 
	public void runAutoSave(){
		new BukkitRunnable(){
			public void run(){
				LamaHub.this.userManager.saveUserDataAsync();
				Command.broadcastCommandMessage(Bukkit.getConsoleSender(), "§aSauvegarde automatique effectuée.");
				LamaHub.this.clearEntities();
				if(msg == 4){
					Bukkit.broadcastMessage("§aNotre discord: §bhttps://discord.gg/xFtSFTf");
					msg = 1;
					return;
				}
				if(msg == 3){
					Bukkit.broadcastMessage("§cServeur en §c§ldeveloppement§c ! Le fait de trouver des bugs est totalement NORMAL");
					msg = 4;
					return;
				}
				if(msg == 2){
					Bukkit.broadcastMessage("§6Le §e§l1v1§6 arrive bientôt...");
					msg = 3;
					return;
				}
				if(msg == 1){
					Bukkit.broadcastMessage("§aEnvie de §bsoutenir§a le serveur ? Achète le grade §b§lVIP§a dès maintenant sur le shop ! §b http://lamahub.buycraft.net/");
					msg = 2;
					return;
				}
			}
		}.runTaskTimerAsynchronously(this, 3000, 3000);
	}
	
	private void clearEntities(){
		new BukkitRunnable(){
			@Override
			public void run() {
				for(World w : Bukkit.getWorlds()){
					for(Entity e : w.getEntities()){
						if(!(e instanceof Player)){
							if(e.getPassenger() != null){
								continue;
							}
							e.remove();
						}
					}
				}				
			}
		}.runTask(this);
	}
	
	private void registerListeners(){
		Bukkit.getPluginManager().registerEvents(new CoreListener(), this);
		Bukkit.getPluginManager().registerEvents(new WorldListener(), this);
		Bukkit.getPluginManager().registerEvents(new KDListener(), this);
		Bukkit.getPluginManager().registerEvents(new GUIListener(), this);
		Bukkit.getPluginManager().registerEvents(new DamageFixListener(), this);
		Bukkit.getPluginManager().registerEvents(new DuelListener(), this);
	}
	
	private void registerCommands(){
		this.getCommand("stats").setExecutor(new StatsCommand());
		this.getCommand("color").setExecutor(new ColorCommand());
		this.getCommand("spawn").setExecutor(new SpawnCommand());
		this.getCommand("hat").setExecutor(new HatCommand());
		this.getCommand("arena").setExecutor(new ArenaExecutor(this));
		this.getCommand("arena").setPermission("admin");
	}
	 
	 private void setInstances(){
		Bukkit.getWorld("FFASoup").getSpawnLocation().add(0.0, 0.1, 0.0);
		 this.userManager = new UserManager(this);
		 LamaHub.instance = this;
		 this.scoreboardHandler = new ScoreboardHandler(this);
		 this.inventoryHandler = new InventoryHandler(this);
		 new MainGui();
		 new KitGui();
		 new ColorGui();
		 new HatGui();
		 new HatGuiPage2();
		 new SonsGui();
		 new ParametreGui();
		 this.timerManager = new TimerManager(this);
		 this.arenaManager = new FlatFileArenaManager(this);
	 }
	 
	 public ArenaManager getArenaManager(){
		 return this.arenaManager;
	 }
	 
	 public TimerManager getTimerManager(){
		 return this.timerManager;
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
	 
	 static {
	        MINUTE = TimeUnit.MINUTES.toMillis(1L);
	        HOUR = TimeUnit.HOURS.toMillis(1L);
	    }
}
