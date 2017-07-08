package fr.taeron.lamahub.scoreboard.provider;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.match.PlayerDuel;
import fr.taeron.lamahub.scoreboard.SidebarEntry;
import fr.taeron.lamahub.scoreboard.SidebarProvider;
import fr.taeron.lamahub.user.LamaUser;

public class DuelScoreboardProvider extends SidebarProvider{

	@Override
	public String getTitle(Player p) {
		return SidebarProvider.SCOREBOARD_TITLE + " §7| §b1v1";
	}

	@Override
	public List<SidebarEntry> getLines(Player p) {
		final List<SidebarEntry> lines = new ArrayList<SidebarEntry>();
        lines.add(new SidebarEntry(ChatColor.GRAY, ChatColor.GRAY + DuelLobbyScoreboardProvider.STRAIGHT_LINE, DuelLobbyScoreboardProvider.STRAIGHT_LINE));
		LamaUser user = LamaHub.getInstance().getUserManager().getUser(p.getUniqueId());
		if(user.getCurrentDuel() != null){
			PlayerDuel duel = user.getCurrentDuel();
			lines.add(new SidebarEntry(ChatColor.GREEN.toString(), "Duel: ", ChatColor.WHITE + duel.getOpponent(p).getName()));
			if(duel.hasStarted()){
				lines.add(new SidebarEntry(ChatColor.GREEN.toString(),  "Durée: ", ChatColor.WHITE + LamaHub.getRemaining(duel.getElapsedTime(), false, true)));
			}
			if(duel.isRanked()){
				lines.add(new SidebarEntry(ChatColor.GREEN.toString(), "Elo: ", ChatColor.WHITE.toString() + LamaHub.getInstance().getUserManager().getUser(duel.getOpponent(p).getUniqueId()).getElo(duel.getKit().getName())));
			}
			lines.add(new SidebarEntry(ChatColor.GREEN.toString(), "Arène: " + ChatColor.WHITE, duel.getArena().getName()));
		}
        lines.add(new SidebarEntry(ChatColor.GRAY, ChatColor.STRIKETHROUGH + DuelLobbyScoreboardProvider.STRAIGHT_LINE + ChatColor.GRAY, DuelLobbyScoreboardProvider.STRAIGHT_LINE));
		return lines;
	}

}
