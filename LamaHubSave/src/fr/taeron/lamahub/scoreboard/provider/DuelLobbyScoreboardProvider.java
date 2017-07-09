package fr.taeron.lamahub.scoreboard.provider;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.scoreboard.SidebarEntry;
import fr.taeron.lamahub.scoreboard.SidebarProvider;
import fr.taeron.lamahub.user.LamaUser;

public class DuelLobbyScoreboardProvider extends SidebarProvider{

	@Override
	public String getTitle(Player p) {
		return SidebarProvider.SCOREBOARD_TITLE + " §7| §b1v1";
	}

	@Override
	public List<SidebarEntry> getLines(Player p) {
        final List<SidebarEntry> lines = new ArrayList<SidebarEntry>();
        lines.add(new SidebarEntry(ChatColor.GRAY, ChatColor.GRAY + DuelLobbyScoreboardProvider.STRAIGHT_LINE, DuelLobbyScoreboardProvider.STRAIGHT_LINE));
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "Ping: ", ChatColor.WHITE.toString() + ((CraftPlayer)p).getHandle().ping));
        LamaUser user = LamaHub.getInstance().getUserManager().getUser(p.getUniqueId());
        int earlyElo = user.getElo("EarlyHG");
        int ironElo = user.getElo("Iron");
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "EarlyHG Elo: ", ChatColor.WHITE.toString() + earlyElo));
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "Iron Elo: ", ChatColor.WHITE.toString() + ironElo));
        lines.add(new SidebarEntry(ChatColor.GRAY, ChatColor.STRIKETHROUGH + DuelLobbyScoreboardProvider.STRAIGHT_LINE + ChatColor.GRAY, DuelLobbyScoreboardProvider.STRAIGHT_LINE));
        return lines;
	}
}
