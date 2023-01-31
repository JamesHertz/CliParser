package jh.cliApp;

import jh.parser.Format;

public interface CommandInfo {
    String commandName();
    String description();
    Format argsFormat();
}
