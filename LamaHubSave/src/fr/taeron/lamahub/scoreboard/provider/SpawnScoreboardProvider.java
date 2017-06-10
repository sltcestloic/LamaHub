package fr.taeron.lamahub.scoreboard.provider;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.taeron.lamahub.scoreboard.SidebarEntry;
import fr.taeron.lamahub.scoreboard.SidebarProvider;

public class SpawnScoreboardProvider extends SidebarProvider{

	@Override
	public String getTitle(Player p) {
		return SidebarProvider.SCOREBOARD_TITLE + " §7| §bFFA";
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<SidebarEntry> getLines(Player p) {
        final List<SidebarEntry> lines = new ArrayList<SidebarEntry>();
        lines.add(new SidebarEntry(ChatColor.GRAY, ChatColor.GRAY + SpawnScoreboardProvider.STRAIGHT_LINE, SpawnScoreboardProvider.STRAIGHT_LINE));
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.AQUA + "En ligne: ", ChatColor.WHITE.toString() + Bukkit.getOnlinePlayers().length));
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.AQUA + "Ping: ", ((CraftPlayer)p).getHandle().ping));
        lines.add(new SidebarEntry(ChatColor.GRAY, ChatColor.STRIKETHROUGH + SpawnScoreboardProvider.STRAIGHT_LINE + ChatColor.GRAY, SpawnScoreboardProvider.STRAIGHT_LINE));
		return null;
	}
}
