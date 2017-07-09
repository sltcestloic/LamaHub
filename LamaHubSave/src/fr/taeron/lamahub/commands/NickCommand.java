package fr.taeron.lamahub.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.taeron.lamahub.utils.NicksCache;

public class NickCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){
			return false;
		}
		if(!sender.hasPermission("nick")){
			return false;
		}
		if(args.length != 1){
			sender.sendMessage("§cUtilisation: /nick <pseudo>");
			return false;
		}
		Player p = (Player) sender;
		if(args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("remove")){
			NicksCache.unNick(p);
			sender.sendMessage("§aNick supprimé.");
			return false;
		}
		NicksCache.setNick(p, args[0]);
		sender.sendMessage("§aNick: " + args[0]);
		return false;
	}

}
