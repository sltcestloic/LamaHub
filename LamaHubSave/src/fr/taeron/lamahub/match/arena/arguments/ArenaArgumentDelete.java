package fr.taeron.lamahub.match.arena.arguments;

import org.bukkit.command.*;
import org.bukkit.entity.*;

import fr.taeron.core.util.command.CommandArgument;
import fr.taeron.lamahub.LamaHub;
import net.md_5.bungee.api.*;

public class ArenaArgumentDelete extends CommandArgument
{
    private final LamaHub plugin;
    
    public ArenaArgumentDelete(final LamaHub plugin) {
        super("delete", "Supprimer une arène");
        this.plugin = plugin;
        this.aliases = new String[] { "remove" };
        this.isPlayerOnly = true;
    }
    
    public String getUsage(final String label) {
        return '/' + label + ' ' + this.getName() + " <arène>";
    }
    
    public boolean onCommand(final CommandSender cs, final Command command, final String s, final String[] args) {
        if (!(cs instanceof Player)) {
            cs.sendMessage(ChatColor.RED + "La console ne supprimer pas d'arène.");
            return true;
        }
        final Player p = (Player)cs;
        if (args.length != 2) {
            p.sendMessage(ChatColor.RED + "Utilisation: " + this.getUsage(s));
            return true;
        }
        if (this.plugin.getArenaManager().getArena(args[1]) == null) {
            p.sendMessage(ChatColor.RED + "Cette arène n'existe pas");
            return true;
        }
        this.plugin.getArenaManager().removeArena(this.plugin.getArenaManager().getArena(args[1]));
        p.sendMessage(ChatColor.GREEN + "Tu as supprimé l'arène " + ChatColor.AQUA + args[1]);
        return false;
    }
}
