package fr.taeron.lamahub.utils;

import java.util.Arrays;
import java.util.Collection;

import fr.taeron.core.util.command.CommandArgument;

public class CommandHelper {

	public static CommandArgument getArgument(final Collection<CommandArgument> arguments, final String id) {
        for (final CommandArgument argument : arguments) {
            if (argument.getName().equalsIgnoreCase(id) || Arrays.asList(argument.getAliases()).contains(id)) {
                return argument;
            }
        }
        return null;
    }
}
