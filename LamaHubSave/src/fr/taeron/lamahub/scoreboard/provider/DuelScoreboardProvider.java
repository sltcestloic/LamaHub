package fr.taeron.lamahub.scoreboard.provider;

import java.util.List;

import org.bukkit.entity.Player;

import fr.taeron.lamahub.scoreboard.SidebarEntry;
import fr.taeron.lamahub.scoreboard.SidebarProvider;

public class DuelScoreboardProvider extends SidebarProvider{

	@Override
	public String getTitle(Player p) {
		return SidebarProvider.SCOREBOARD_TITLE + " §7| §b1v1";
	}

	@Override
	public List<SidebarEntry> getLines(Player p) {
		// TODO Auto-generated method stub
		return null;
	}

}
