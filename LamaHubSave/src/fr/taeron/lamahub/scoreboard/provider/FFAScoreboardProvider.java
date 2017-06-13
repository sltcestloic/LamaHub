package fr.taeron.lamahub.scoreboard.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.scoreboard.SidebarEntry;
import fr.taeron.lamahub.scoreboard.SidebarProvider;
import fr.taeron.lamahub.timer.PlayerTimer;
import fr.taeron.lamahub.timer.Timer;
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
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "Kit: ", ChatColor.WHITE.toString() + user.getCurrentKitName()));
		lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "Kills: ", ChatColor.WHITE.toString() + user.getKills()));
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "Morts: ", ChatColor.WHITE.toString() + user.getDeaths()));
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "KS: ", ChatColor.WHITE.toString() + user.getKS()));
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "Meilleur KS: ", ChatColor.WHITE.toString() + user.getBestKS()));
        lines.add(new SidebarEntry(ChatColor.GRAY + "", ChatColor.GREEN + "LamaCoins: ", ChatColor.WHITE.toString() + user.getCoins()));
        final Collection<Timer> timers = LamaHub.getInstance().getTimerManager().getTimers();
        for (final Timer timer : timers) {
            if (timer instanceof PlayerTimer) {
                final PlayerTimer playerTimer = (PlayerTimer)timer;
                final long remaining2 = playerTimer.getRemaining(p);
                if (remaining2 <= 0L) {
                    continue;
                }
                String timerName = playerTimer.getName();
                if (timerName.length() > 14) {
                    timerName = timerName.substring(0, timerName.length());
                }
                lines.add(new SidebarEntry(playerTimer.getScoreboardPrefix(), timerName, ": " + ChatColor.WHITE + LamaHub.getRemaining(remaining2, false)));
            }
        }           lines.add(new SidebarEntry(ChatColor.GRAY, ChatColor.STRIKETHROUGH + SpawnScoreboardProvider.STRAIGHT_LINE + ChatColor.GRAY, SpawnScoreboardProvider.STRAIGHT_LINE));
        return lines;
	}
}
