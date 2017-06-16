package fr.taeron.lamahub.match.arena;

import fr.taeron.core.util.command.ArgumentExecutor;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.match.arena.arguments.ArenaArgumentCreate;
import fr.taeron.lamahub.match.arena.arguments.ArenaArgumentDelete;
import fr.taeron.lamahub.match.arena.arguments.ArenaArgumentList;
import fr.taeron.lamahub.match.arena.arguments.ArenaArgumentTeleport;
import fr.taeron.lamahub.match.arena.arguments.ArenaSpawnsExecutor;


public class ArenaExecutor extends ArgumentExecutor{
	
    public ArenaExecutor(LamaHub plugin) {
        super("arena");
        this.addArgument(new ArenaSpawnsExecutor(plugin));
        this.addArgument(new ArenaArgumentCreate(plugin));
        this.addArgument(new ArenaArgumentTeleport(plugin));
        this.addArgument(new ArenaArgumentDelete(plugin));
        this.addArgument(new ArenaArgumentList(plugin));
    }
}
