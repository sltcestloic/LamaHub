package fr.taeron.lamahub.match.arena.arguments;


import org.bukkit.command.*;
import org.bukkit.entity.*;

import fr.taeron.core.util.command.CommandArgument;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.match.arena.Arena;
import net.md_5.bungee.api.*;

public class ArenaArgumentList extends CommandArgument
{
    public final LamaHub plugin;
    
    public ArenaArgumentList(final LamaHub plugin) {
        super("list", "Voir la liste des arènes");
        this.plugin = plugin;
        this.isPlayerOnly = true;
        this.aliases = new String[] { "show" };
    }
    
    public String getUsage(final String label) {
        return '/' + label + ' ' + this.getName() + "";
    }
    
    public boolean onCommand(final CommandSender cs, final Command command, final String s, final String[] args) {
        final Player p = (Player)cs;
        if (args.length != 1) {
            p.sendMessage(ChatColor.RED + "Usage: " + this.getUsage(s));
            return true;
        }
        p.sendMessage("§bListe des arènes:");
        String ss = "";
        for(Arena a : this.plugin.getArenaManager().getArenas()){    	
        	ss = ss + "§a" + a.getName() + "§7, ";
        }
        cs.sendMessage(ss);
        return false;
    }
}
