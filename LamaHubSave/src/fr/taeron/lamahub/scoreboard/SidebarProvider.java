package fr.taeron.lamahub.scoreboard;

import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;
import fr.taeron.core.util.BukkitUtils;

public abstract class SidebarProvider
{
    protected static final String SCOREBOARD_TITLE;
    protected static final String STRAIGHT_LINE;
    
    public abstract String getTitle(final Player p0);
    
    public abstract List<SidebarEntry> getLines(final Player p0);
    
    public SidebarEntry getLine(final ChatColor lineColor, final List<SidebarEntry> lines) {
        final List<ChatColor> colors = new ArrayList<ChatColor>();
        for (final ChatColor color : ChatColor.values()) {
            if (!colors.contains(color)) {
                colors.add(color);
            }
        }
        final Integer random = new Random().nextInt(colors.size());
        String name = colors.get(random).toString();
        if (lines == null) {
            return null;
        }
        for (final SidebarEntry line : lines) {
            if (line == null) {
                return null;
            }
            if (!line.name.equalsIgnoreCase(name)) {
                return new SidebarEntry(lineColor + SidebarProvider.STRAIGHT_LINE, name + lineColor, SidebarProvider.STRAIGHT_LINE);
            }
            name += colors.get(new Random().nextInt(colors.size()));
        }
        return new SidebarEntry(lineColor + SidebarProvider.STRAIGHT_LINE, name + lineColor, SidebarProvider.STRAIGHT_LINE);
    }
    
    static {
        SCOREBOARD_TITLE = "§f§lLama§2§lHub";
        STRAIGHT_LINE = BukkitUtils.STRAIGHT_LINE_DEFAULT.substring(0, 11);
    }
}
