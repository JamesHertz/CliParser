package jh.projects.cliparser.cliApp;

import jh.projects.cliparser.parser.Format;

public interface CommandInfo {
    String commandName();
    String description();
    Format argsFormat();
}
