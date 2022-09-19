package jh.cliApp;

import jh.parser.Format;

public interface Command<T> {
    String commandName();
    Format argsFormat();
    void runCommand(CommandContext<T> context);
}
