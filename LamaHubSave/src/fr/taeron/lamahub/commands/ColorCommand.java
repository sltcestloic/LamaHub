package fr.taeron.lamahub.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.taeron.lamahub.inventory.gui.ColorGui;

public class ColorCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("vip")){
			sender.sendMessage("§2Tu dois être VIP pour utiliser cette commande.");
			sender.sendMessage("§2Notre boutique: §ahttp://lamahub.buycraft.net/");
			return false;
		}
		if(!(sender instanceof Player)){
			Bukkit.getConsoleSender().sendMessage("§cNique ta mère ack on change pas de couleur depuis la console");
			return false;
		}
		Player p = (Player) sender;
		ColorGui.open(p);
		return false;
	}
}
