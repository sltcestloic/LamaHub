package fr.taeron.lamahub.inventory;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;

import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.scoreboard.PlayerBoard;
import fr.taeron.lamahub.scoreboard.SidebarProvider;

import org.bukkit.event.player.*;
import org.bukkit.inventory.*;

public abstract class InventorySnapshot
{
    public final SidebarProvider sidebarProvider;
    private final LamaHub plugin;
    private final Location location;
    
    public InventorySnapshot(final LamaHub plugin, final SidebarProvider sidebarProvider, final Location location) {
        this.plugin = plugin;
        this.sidebarProvider = sidebarProvider;
        this.location = location;
    }
    
    public void applyTo(final Player player, final boolean teleport, final boolean setItems) {
        if (this.sidebarProvider != null) {
            final PlayerBoard playerBoard = this.plugin.getScoreboardHandler().getPlayerBoard(player.getUniqueId());
            if (playerBoard != null) {
                playerBoard.setDefaultSidebar(this.sidebarProvider, 2L);
            }
        }
        for (final PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
        if (setItems) {
            final PlayerInventory inventory = player.getInventory();
            inventory.clear();
            inventory.setArmorContents(new ItemStack[inventory.getArmorContents().length]);
        }
        if (teleport && this.location != null && !player.isDead()) {
        	player.closeInventory();
            player.teleport(this.location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
    }
}
