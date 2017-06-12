package fr.taeron.lamahub.timer.argument;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.cheesesoftware.PowerfulPerms.common.UUIDFetcher;

import fr.taeron.core.util.command.CommandArgument;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.timer.PlayerTimer;
import fr.taeron.lamahub.timer.Timer;
import net.minecraft.util.org.apache.commons.lang3.time.DurationFormatUtils;

public class TimerCheckArgument extends CommandArgument{
	
    private final LamaHub plugin;
    
    public TimerCheckArgument(final LamaHub plugin) {
        super("check", "Voir le temps restant d'un timer");
        this.plugin = plugin;
    }
    
    public String getUsage(final String label) {
        return '/' + label + ' ' + this.getName() + " <timer> <joueur>";
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "Utilisation: " + this.getUsage(label));
            return true;
        }
        PlayerTimer temporaryTimer = null;
        for (final Timer timer : this.plugin.getTimerManager().getTimers()) {
            if (timer instanceof PlayerTimer && timer.getName().equalsIgnoreCase(args[1])) {
                temporaryTimer = (PlayerTimer)timer;
                break;
            }
        } 
        if (temporaryTimer == null) {
            sender.sendMessage(ChatColor.RED + "Le timer '" + args[1] + "' est introuvable.");
            return true;
        }
        final PlayerTimer playerTimer = temporaryTimer;
        new BukkitRunnable() {
            public void run() {
                UUID uuid;
                try {
                    uuid = UUIDFetcher.getUUIDOf(args[2]);
                }
                catch (Exception ex) {
                    sender.sendMessage(ChatColor.GOLD + "Le joueur '" + ChatColor.WHITE + args[2] + ChatColor.GOLD + "' est introuvable.");
                    return;
                }
                final long remaining = playerTimer.getRemaining(uuid);
                sender.sendMessage(ChatColor.YELLOW + args[2] + " a le timer " + playerTimer.getName() + " pendant " + DurationFormatUtils.formatDurationWords(remaining, true, true));
            }
        }.runTaskAsynchronously(this.plugin);
        return true;
    }
    
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String label, final String[] args) {
        return (args.length == 2) ? null : Collections.emptyList();
    }
}
