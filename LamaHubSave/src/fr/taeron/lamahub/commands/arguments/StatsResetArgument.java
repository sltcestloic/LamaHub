package fr.taeron.lamahub.commands.arguments;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.taeron.core.util.command.CommandArgument;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.user.LamaUser;

public class StatsResetArgument extends CommandArgument{

	public StatsResetArgument() {
		super("reset", "Remettre à 0 les stats d'un joueur");
		this.permission = "staff";
	}

	@Override
	public String getUsage(String label) {
		return ChatColor.YELLOW + "/" + label + " reset <joueur>";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length != 2){
			sender.sendMessage("§cUtilisation: /" + label + " reset <joueur>");
			return false;
		}
		OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
		if(p == null){
			sender.sendMessage("§cCe joueur n'existe pas.");
			return false;
		}
		LamaUser user = LamaHub.getInstance().getUserManager().getUser(p.getUniqueId());
		user.setDeaths(0);
		user.setKills(0);
		user.setKS(0);
		user.setBestKS(0);
		sender.sendMessage("§aTu as remis à 0 les stats de " + p.getName());
		return false;
	}

	
}
