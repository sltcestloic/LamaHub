package fr.taeron.lamahub.timer.argument;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.taeron.core.util.JavaUtils;
import fr.taeron.core.util.command.CommandArgument;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.timer.PlayerTimer;
import fr.taeron.lamahub.timer.Timer;
import net.minecraft.util.com.google.common.base.Function;
import net.minecraft.util.com.google.common.base.Predicate;
import net.minecraft.util.com.google.common.collect.FluentIterable;
import net.minecraft.util.org.apache.commons.lang3.time.DurationFormatUtils;

public class TimerSetArgument extends CommandArgument{
	
    private static final Pattern WHITESPACE_TRIMMER;
    private final LamaHub plugin;
    
    public TimerSetArgument(final LamaHub plugin) {
        super("set", "Definir le temps restant d'un timer");
        this.plugin = plugin;
    }
    
    public String getUsage(final String label) {
        return '/' + label + ' ' + this.getName() + " <timer> <all|joueur> <temps>";
    }
    
    @SuppressWarnings("deprecation")
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length < 4) {
            sender.sendMessage(ChatColor.RED + "Utilisation: " + this.getUsage(label));
            return true;
        }
        final long duration = JavaUtils.parse(args[3]);
        if (duration == -1L) {
            sender.sendMessage(ChatColor.RED + "Duree invalide. Exemple: 10m 1s");
            return true;
        }
        PlayerTimer playerTimer = null;
        for (final Timer timer : this.plugin.getTimerManager().getTimers()) {
            if (timer instanceof PlayerTimer && TimerSetArgument.WHITESPACE_TRIMMER.matcher(timer.getName()).replaceAll("").equalsIgnoreCase(args[1])) {
                playerTimer = (PlayerTimer)timer;
                break;
            }
        }
        if (playerTimer == null) {
            sender.sendMessage(ChatColor.RED + "Le timer '" + args[1] + "' n'existe pas.");
            return true;
        }
        if (args[2].equalsIgnoreCase("all")) {
            for (final Player player : Bukkit.getOnlinePlayers()) {
                playerTimer.setCooldown(player, player.getUniqueId(), duration, true);
            }
            sender.sendMessage(ChatColor.GREEN + "Tu as defini le timer " + playerTimer.getName() + " pour tout le monde en " + DurationFormatUtils.formatDurationWords(duration, true, true) + '.');
        }
        else {
            final OfflinePlayer target = Bukkit.getOfflinePlayer(args[2]);
            Player targetPlayer = null;
            if (target == null || (sender instanceof Player && (targetPlayer = target.getPlayer()) != null && !((Player)sender).canSee(targetPlayer))) {
                sender.sendMessage(ChatColor.GOLD + "Le joueur '" + ChatColor.WHITE + args[1] + ChatColor.GOLD + "' n'est pas connecté.");
                return true;
            }
            playerTimer.setCooldown(targetPlayer, target.getUniqueId(), duration, true);
            sender.sendMessage(ChatColor.GREEN + "Tu as defini la durée du timer " + playerTimer.getName() + " a " + DurationFormatUtils.formatDurationWords(duration, true, true) + " pour " + target.getName() + '.');
        }
        return true;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public List<String> onTabComplete(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length == 2) {
            return (List<String>)FluentIterable.from((Iterable)this.plugin.getTimerManager().getTimers()).filter(new Predicate<Timer>() {
                public boolean apply(final Timer timer) {
                    return timer instanceof PlayerTimer;
                }
            }).transform((Function)new Function<Timer, String>() {
                @Nullable
                public String apply(final Timer timer) {
                    return TimerSetArgument.WHITESPACE_TRIMMER.matcher(timer.getName()).replaceAll("");
                }
            }).toList();
        }
        if (args.length == 3) {
            final List<String> list = new ArrayList<String>();
            list.add("ALL");
            final Player player = (Player)sender;
            for (final Player target : Bukkit.getOnlinePlayers()) {
                if (player == null || player.canSee(target)) {
                    list.add(target.getName());
                }
            }
            return list;
        }
        return Collections.emptyList();
    }
    
    static {
        WHITESPACE_TRIMMER = Pattern.compile("\\s");
    }
}
