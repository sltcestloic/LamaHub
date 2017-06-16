package fr.taeron.lamahub.match.arena.arguments;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.taeron.core.util.command.CommandArgument;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.match.arena.Arena;

public class ArenaArgumentCreate extends CommandArgument{
	
    private final LamaHub plugin;
    
    public ArenaArgumentCreate(LamaHub plugin) {
        super("create", "Créer une arène");
        this.isPlayerOnly = true;
        this.plugin = plugin;
    }
    
    public String getUsage(final String label) {
        return '/' + label + ' ' + this.getName() + " <nom>";
    }
    
    public boolean onCommand(final CommandSender cs, final Command command, final String s, final String[] args) {
        final Player p = (Player)cs;
        if (args.length != 2) {
            p.sendMessage(ChatColor.RED + "Utilisation: " + this.getUsage(s));
            return true;
        }
        if (this.plugin.getArenaManager().getArena(args[1]) != null) {
            p.sendMessage(ChatColor.RED + "Cette arène existe déjà");
            return true;
        }
        if (this.plugin.getArenaManager().createArena(new Arena(args[1]))) {
            p.sendMessage(ChatColor.GREEN + "Tu as créé l'arène " + ChatColor.AQUA + args[1]);
        }
        return false;
    }
}
