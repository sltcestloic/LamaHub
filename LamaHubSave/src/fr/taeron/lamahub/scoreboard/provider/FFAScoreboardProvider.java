package fr.taeron.lamahub.scoreboard.provider;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.scoreboard.SidebarEntry;
import fr.taeron.lamahub.scoreboard.SidebarProvider;
import fr.taeron.lamahub.user.LamaUser;

public class FFAScoreboardProvider extends SidebarProvider{

	@Override
	public String getTitle(Player p) {
		return SidebarProvider.SCOREBOARD_TITLE + " §7| §bFFA";
	}

	@Override
	public List<SidebarEntry> getLines(Player p) {
		final List<SidebarEntry> lines = new ArrayList<SidebarEntry>();
		LamaUser user = LamaHub.getInstance().getUserManager().getUser(p.getUniqueId());
		lines.add(new SidebarEntry(ChatColor.GRAY, ChatColor.GRAY + SpawnScoreboardProvider.STRAIGHT_LINE, SpawnScoreboardProvider.STRAIGHT_LINE));	
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "Kills: ", ChatColor.WHITE.toString() + user.getKills()));
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "Morts: ", ChatColor.WHITE.toString() + user.getDeaths()));
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "Ratio: ", ChatColor.WHITE.toString() + user.getKDR()));
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "KS: ", ChatColor.WHITE.toString() + user.getKS()));
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "Meilleur KS: ", ChatColor.WHITE.toString() + user.getBestKS()));
		lines.add(new SidebarEntry(ChatColor.GRAY, ChatColor.STRIKETHROUGH + SpawnScoreboardProvider.STRAIGHT_LINE + ChatColor.GRAY, SpawnScoreboardProvider.STRAIGHT_LINE));
        return lines;
	}
}
