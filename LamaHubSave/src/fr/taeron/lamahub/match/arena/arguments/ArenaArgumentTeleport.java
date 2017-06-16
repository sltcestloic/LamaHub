package fr.taeron.lamahub.match.arena.arguments;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import net.md_5.bungee.api.*;

import fr.taeron.core.util.JavaUtils;
import fr.taeron.core.util.command.CommandArgument;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.match.arena.Arena;


public class ArenaArgumentTeleport extends CommandArgument
{
    private final LamaHub plugin;
    
    public ArenaArgumentTeleport(LamaHub plugin) {
        super("teleport", "Se téléporter à une arène");
        this.plugin = plugin;
        this.isPlayerOnly = true;
        this.aliases = new String[] { "tp" };
    }
    
    public String getUsage(final String label) {
        return '/' + label + ' ' + this.getName() + " <arène> <1|2>";
    }
    
    public boolean onCommand(final CommandSender cs, final Command command, final String s, final String[] args) {
        final Player p = (Player)cs;
        if (args.length != 3) {
            p.sendMessage(ChatColor.RED + "Utilisation: " + this.getUsage(s));
            return true;
        }
        if (this.plugin.getArenaManager().getArena(args[1]) == null) {
            p.sendMessage(ChatColor.RED + "Cette arène n'existe pas.");
            return true;
        }
        final Arena arena = this.plugin.getArenaManager().getArena(args[1]);
        final Integer point = JavaUtils.tryParseInt(args[2]);
        if (point == null) {
            p.sendMessage(org.bukkit.ChatColor.RED + "Nombre invalide.");
            return true;
        }
        if (point <= 1) {
            p.teleport(arena.getPoint1());
        }
        else {
            p.teleport(arena.getPoint2());
        }
        p.sendMessage(ChatColor.GREEN + "Tu as été téléporté au spawn " + ChatColor.AQUA + point + ChatColor.GREEN + " de l'arène " + ChatColor.AQUA + args[1]);
        return false;
    }
}
