package jh.cliApp;

import jh.parser.Format;

public interface CliCommand<T> {
    String commandName();
    Format argsFormat();
    void runCommand(CommandContext<T> context);
}
