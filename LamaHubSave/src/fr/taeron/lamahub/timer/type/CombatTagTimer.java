package fr.taeron.lamahub.timer.type;

import java.util.concurrent.*;
import fr.taeron.core.kits.events.*;
import org.bukkit.event.*;
import java.util.*;

import org.bukkit.*;
import net.minecraft.util.org.apache.commons.lang3.time.DurationFormatUtils;
import fr.taeron.core.util.*;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.listeners.KDListener;
import fr.taeron.lamahub.timer.PlayerTimer;
import fr.taeron.lamahub.timer.event.TimerClearEvent;
import fr.taeron.lamahub.timer.event.TimerStartEvent;

import org.bukkit.event.entity.*;
import org.bukkit.enchantments.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.event.player.*;

public class CombatTagTimer extends PlayerTimer implements Listener{
	
    
    public CombatTagTimer(final LamaHub plugin) {
        super("Combat", TimeUnit.SECONDS.toMillis(30L));
    }
    
    public String getScoreboardPrefix() {
        return ChatColor.GREEN.toString();
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onKitApply(final KitApplyEvent event) {
        final Player player = event.getPlayer();
        final long remaining;
        if (!event.isForce() && (remaining = this.getRemaining(player)) > 0L) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Tu ne peux pas prendre de kit tant que ton " + this.getDisplayName() + ChatColor.RED + " timer est actif [" + ChatColor.BOLD + LamaHub.getRemaining(remaining, true, false) + ChatColor.RED + " restant]");
        }
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onTimerStop(final TimerClearEvent event) {
        if (event.getTimer().equals(this)) {
            final com.google.common.base.Optional<UUID> optionalUserUUID = event.getUserUUID();
            if (optionalUserUUID.isPresent()) {
                this.onExpire((UUID)optionalUserUUID.get());
            }
        }
    }
    
	@EventHandler
    public void onQuit(PlayerQuitEvent e){
    	if(this.getRemaining(e.getPlayer()) > 0){
    		this.clearCooldown(e.getPlayer());
    		KDListener.update(new PlayerDeathEvent(e.getPlayer(), Arrays.asList(e.getPlayer().getInventory().getContents()), 0, "quit"));
    	}
    }

    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        final Player attacker = BukkitUtils.getFinalAttacker((EntityDamageEvent)event, true);
        final Entity entity;
        if (attacker != null && (entity = event.getEntity()) instanceof Player) {
            final Player attacked = (Player)entity;
            boolean weapon = event.getDamager() instanceof Arrow;
            if (!weapon) {
                final ItemStack stack = attacker.getItemInHand();
                weapon = (stack != null && EnchantmentTarget.WEAPON.includes(stack));
            }
            final long duration = weapon ? this.defaultCooldown : 5000L;
            this.setCooldown(attacked, attacked.getUniqueId(), Math.max(this.getRemaining(attacked), duration), true);
            this.setCooldown(attacker, attacker.getUniqueId(), Math.max(this.getRemaining(attacker), duration), true);
        }
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onTimerStart(final TimerStartEvent event) {
        if (event.getTimer().equals(this)) {
            final com.google.common.base.Optional<Player> optional = event.getPlayer();
            if (optional.isPresent()) {
                final Player player = (Player)optional.get();
                player.sendMessage(ChatColor.YELLOW + "Tu es désormais en " + "combat" + ChatColor.YELLOW + " pendant " + ChatColor.RED + DurationFormatUtils.formatDurationWords(event.getDuration(), true, true) + ChatColor.YELLOW + '.');
            }
        }
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerRespawn(final PlayerRespawnEvent event) {
        this.clearCooldown(event.getPlayer().getUniqueId());
    }
}
