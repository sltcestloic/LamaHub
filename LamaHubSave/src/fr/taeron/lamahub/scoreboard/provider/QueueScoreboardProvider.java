package fr.taeron.lamahub.scoreboard.provider;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.match.Queue;
import fr.taeron.lamahub.scoreboard.SidebarEntry;
import fr.taeron.lamahub.scoreboard.SidebarProvider;

public class QueueScoreboardProvider extends SidebarProvider{

	@Override
	public String getTitle(Player p) {
		return SidebarProvider.SCOREBOARD_TITLE + " §7| §b1v1";
	}

	@Override
	public List<SidebarEntry> getLines(Player p) {
		Queue queue = LamaHub.getInstance().getUserManager().getUser(p.getUniqueId()).getQueue();
		final List<SidebarEntry> lines = new ArrayList<SidebarEntry>();
		lines.add(new SidebarEntry(ChatColor.GRAY, ChatColor.GRAY + SpawnScoreboardProvider.STRAIGHT_LINE, SpawnScoreboardProvider.STRAIGHT_LINE));	
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "Queue: ", ChatColor.WHITE.toString() + queue.getKit().getName()));
        String ranked = queue.isRanked() ? "Oui" : "Non";
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "Ranked: ", ChatColor.WHITE.toString() + ranked));
        lines.add(new SidebarEntry(ChatColor.GRAY, ChatColor.STRIKETHROUGH + SpawnScoreboardProvider.STRAIGHT_LINE + ChatColor.GRAY, SpawnScoreboardProvider.STRAIGHT_LINE));
		return lines;
	}

}
