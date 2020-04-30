package nepcflan.nvb;

import java.util.Arrays;
import java.util.Set;
import nepcflan.nvb.Commands.CleanCommand;
import nepcflan.nvb.Commands.NekoCommand;
import nepcflan.nvb.Commands.ShutdownCommand;

public class CommandRegistry {
    private static final Set<Command> commands = New HashSet<>();

    CommandRegistry(){
        register(
                new NekoCommand(),
                new CleanCommand(),
                new ShutdownCommand());
    }

    private void register(Command... cmds) {
        commands.addAll(Arrays.asList(cmds));
    }

    Set<Command> getCommands() {
        return commands;
    }
}
