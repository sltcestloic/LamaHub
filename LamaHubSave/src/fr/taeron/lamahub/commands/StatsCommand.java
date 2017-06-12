package fr.taeron.lamahub.commands;

import fr.taeron.core.util.command.ArgumentExecutor;
import fr.taeron.lamahub.commands.arguments.StatsResetArgument;

public class StatsCommand extends ArgumentExecutor{

	public StatsCommand() {
		super("stats");
		this.arguments.add(new StatsResetArgument());
	}
}
