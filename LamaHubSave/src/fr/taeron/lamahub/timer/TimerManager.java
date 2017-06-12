package fr.taeron.lamahub.timer;

import org.bukkit.event.*;

import org.bukkit.plugin.java.*;
import fr.taeron.core.util.*;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.timer.type.CombatTagTimer;

import org.bukkit.plugin.*;
import java.util.*;

public class TimerManager implements Listener
{
    public final CombatTagTimer spawnTagTimer;
    private final Set<Timer> timers;
    private final JavaPlugin plugin;
    private Config config;
    
    public TimerManager(final LamaHub plugin) {
        this.timers = new HashSet<Timer>();
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
        this.registerTimer(this.spawnTagTimer = new CombatTagTimer(plugin));
        this.reloadTimerData();
    }

    
    public Collection<Timer> getTimers() {
        return this.timers;
    }
    
    public void registerTimer(final Timer timer) {
        this.timers.add(timer);
        if (timer instanceof Listener) {
            this.plugin.getServer().getPluginManager().registerEvents((Listener)timer, (Plugin)this.plugin);
        }
    }
    
    public void unregisterTimer(final Timer timer) {
        this.timers.remove(timer);
    }
    
    public void reloadTimerData() {
        this.config = new Config(this.plugin, "timers");
        for (final Timer timer : this.timers) {
            timer.load(this.config);
        }
    }
    
    public void saveTimerData() {
        for (final Timer timer : this.timers) {
            timer.onDisable(this.config);
        }
        this.config.save();
    }
}
