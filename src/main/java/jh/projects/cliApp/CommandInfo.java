package jh.projects.cliApp;

import jh.projects.parser.Format;

public interface CommandInfo {
    String commandName();
    String description();
    Format argsFormat();
}
