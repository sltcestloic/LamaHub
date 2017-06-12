package fr.taeron.lamahub.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.taeron.lamahub.LamaHub;

public class SpawnCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){
			return false;
		}
		Player p = (Player) sender;
		if(LamaHub.getInstance().getTimerManager().spawnTagTimer.getRemaining(p) > 0){
			sender.sendMessage("§cTu ne peut pas utiliser cette commande en combat.");
			return false;
		}
		LamaHub.getInstance().getInventoryHandler().spawnInventory.applyTo(p, true, true);
		return false;
	}
}
