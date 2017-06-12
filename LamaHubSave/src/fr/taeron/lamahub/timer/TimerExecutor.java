package fr.taeron.lamahub.timer;

import fr.taeron.core.util.command.ArgumentExecutor;
import fr.taeron.lamahub.LamaHub;
import fr.taeron.lamahub.timer.argument.TimerCheckArgument;
import fr.taeron.lamahub.timer.argument.TimerSetArgument;

public class TimerExecutor extends ArgumentExecutor{
	
    public TimerExecutor(final LamaHub plugin) {
        super("timer");
        this.addArgument(new TimerCheckArgument(plugin));
        this.addArgument(new TimerSetArgument(plugin));
    }
}
