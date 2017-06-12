package fr.taeron.lamahub.scoreboard;

import org.bukkit.scheduler.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.scoreboard.*;

import fr.taeron.lamahub.LamaHub;

import java.util.*;
import org.bukkit.*;

public class PlayerBoard{
	
    private boolean sidebarVisible;
    private boolean removed;
    private SidebarProvider defaultProvider;
    private SidebarProvider temporaryProvider;
    private BukkitRunnable runnable;
    private final Scoreboard scoreboard;
    public final BufferedObjective bufferedObjective;
    private final Player player;
    private final LamaHub plugin;
    
    public PlayerBoard(final LamaHub plugin, final Player player) {
        this.sidebarVisible = false;
        this.removed = false;
        this.plugin = plugin;
        this.player = player;
        this.scoreboard = plugin.getServer().getScoreboardManager().getNewScoreboard();
        this.bufferedObjective = new BufferedObjective(this.scoreboard);
        new BukkitRunnable() {
            public void run() {
                player.setScoreboard(PlayerBoard.this.scoreboard);
            }
        }.runTaskAsynchronously((Plugin)plugin);
    }
    
    public static boolean isSupportedByServer() {
        return Bukkit.getScoreboardManager() != null;
    }
    
    public void remove() {
        this.removed = true;
        if (this.scoreboard != null) {
            for (final Team team : this.scoreboard.getTeams()) {
                team.unregister();
            }
            for (final Objective objective : this.scoreboard.getObjectives()) {
                objective.unregister();
            }
        }
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }
    
    public boolean isSidebarVisible() {
        return this.sidebarVisible;
    }
    
    public void setSidebarVisible(final boolean visible) {
        if (!isSupportedByServer()) {
            return;
        }
        this.sidebarVisible = visible;
        this.bufferedObjective.setDisplaySlot(visible ? DisplaySlot.SIDEBAR : null);
    }
    
    public void setDefaultSidebar(final SidebarProvider provider, final long updateInterval) {
        if (!isSupportedByServer()) {
            return;
        }
        if (provider != null && provider.equals(this.defaultProvider)) {
            return;
        }
        this.defaultProvider = provider;
        if (this.runnable != null) {
            this.runnable.cancel();
        }
        if (provider == null) {
            this.scoreboard.clearSlot(DisplaySlot.SIDEBAR);
            return;
        }
        (this.runnable = new BukkitRunnable() {
            public void run() {
                if (PlayerBoard.this.removed) {
                    this.cancel();
                    return;
                }
                if (provider.equals(PlayerBoard.this.defaultProvider)) {
                    PlayerBoard.this.updateObjective();
                }
            }
        }).runTaskTimerAsynchronously((Plugin)this.plugin, updateInterval, updateInterval);
    }
    
    public void setTemporarySidebar(final SidebarProvider provider, final long expiration) {
        this.temporaryProvider = provider;
        this.updateObjective();
        new BukkitRunnable() {
            public void run() {
                if (PlayerBoard.this.removed) {
                    this.cancel();
                    return;
                }
                if (PlayerBoard.this.temporaryProvider == provider) {
                    PlayerBoard.this.temporaryProvider = null;
                    PlayerBoard.this.updateObjective();
                }
            }
        }.runTaskLaterAsynchronously((Plugin)this.plugin, expiration);
    }
    
    private void updateObjective() {
        try {
            final SidebarProvider provider = (this.temporaryProvider != null) ? this.temporaryProvider : this.defaultProvider;
            if (provider == null) {
                this.bufferedObjective.setVisible(false);
            }
            else {
                this.bufferedObjective.setTitle(provider.getTitle(this.player));
                this.bufferedObjective.setAllLines(provider.getLines(this.player));
                this.bufferedObjective.flip();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addUpdate(final Player target) {
        this.addUpdates(Collections.singleton(target));
    }
    
    public void addUpdates(final Collection<? extends Player> updates) {
        new BukkitRunnable() {
            public void run() { 
                //qqch
            }
        }.runTaskAsynchronously(this.plugin);
    }
}

