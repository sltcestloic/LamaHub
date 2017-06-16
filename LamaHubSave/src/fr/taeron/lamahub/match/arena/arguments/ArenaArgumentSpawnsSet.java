package fr.taeron.lamahub.match.arena.arguments;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

import fr.taeron.core.util.JavaUtils;
import fr.taeron.core.util.command.CommandArgument;
import fr.taeron.lamahub.LamaHub;

public class ArenaArgumentSpawnsSet extends CommandArgument
{
    private final LamaHub plugin;
   
    public ArenaArgumentSpawnsSet(LamaHub plugin) {
        super("set", "Définir les spawns d'une arène");
        this.isPlayerOnly = true;
        this.plugin = plugin;
    }
    
    public String getUsage(final String label) {
        return '/' + label + ' ' + " spawns " + this.getName() + " <arène> <1|2>";
    }
    
    public boolean onCommand(final CommandSender cs, final Command command, final String s, final String[] args) {
        if (!(cs instanceof Player)) {
            cs.sendMessage(ChatColor.RED + "La console ne met pas de spawns d'arènes.");
            return true;
        }
        final Player p = (Player)cs;
        if (args.length < 4) {
            p.sendMessage(this.getUsage(s));
            return true;
        }
        if (this.plugin.getArenaManager().getArena(args[2]) == null) {
            p.sendMessage(net.md_5.bungee.api.ChatColor.RED + "Cette arène n'existe pas.");
            return true;
        }
        final Integer amount = JavaUtils.tryParseInt(args[3]);
        if (amount == null || (amount != 1 && amount != 2)) {
            p.sendMessage(ChatColor.RED + "Ce nombre n'est pas valide. " + this.getUsage(s));
            return true;
        }
        this.plugin.getArenaManager().getArena(args[2]).addPoint(amount, p.getLocation());
        p.sendMessage(ChatColor.GREEN + "Tu as défini le spawn " + ChatColor.AQUA + amount + ChatColor.GREEN + " pour l'arène " + ChatColor.AQUA + args[2]);
        return false;
    }
}
