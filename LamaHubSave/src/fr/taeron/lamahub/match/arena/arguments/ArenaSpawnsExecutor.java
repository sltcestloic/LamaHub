package fr.taeron.lamahub.match.arena.arguments;

import com.google.common.collect.*;
import fr.taeron.core.util.command.CommandArgument;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.utils.CommandHelper;

import org.bukkit.command.*;
import org.bukkit.*;
import java.util.*;

public class ArenaSpawnsExecutor extends CommandArgument
{
    private final List<CommandArgument> arguments;
    
    public ArenaSpawnsExecutor(final LamaHub plugin) {
        super("spawns", "Gérer les spawns des arènes");
        (this.arguments = Lists.newArrayList()).add(new ArenaArgumentSpawnsSet(plugin));
    }
    
    public String getUsage(final String label) {
        return '/' + label + ' ' + this.getName();
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length < 2) {
            for (final CommandArgument argument : this.arguments) {
                sender.sendMessage(ChatColor.YELLOW + argument.getUsage(label) + " > " + argument.getDescription());
            }
            return true;
        }
        final CommandArgument argument2 = CommandHelper.getArgument(this.arguments, args[1]);
        if (argument2 == null) {
            sender.sendMessage(ChatColor.RED + "L'argument '" + args[1] + "' n'existe pas.");
            return true;
        }
        return argument2.onCommand(sender, command, label, args);
    }
    
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length < 3) {
            final List<String> results = Lists.newArrayListWithExpectedSize(this.arguments.size());
            for (final CommandArgument argument : this.arguments) {
                results.add(argument.getName());
            }
            return results;
        }
        final CommandArgument argument2 = CommandHelper.getArgument(this.arguments, args[1]);
        if (argument2 != null) {
            return (List<String>)argument2.onTabComplete(sender, command, label, args);
        }
        return Collections.emptyList();
    }
}
