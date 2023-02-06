package jh.projects.cliparser.cliApp;

import jh.projects.cliparser.parser.Format;

// todo: create groups
public interface CommandInfo {
    String getName();
    String getDescription();

    /**
     * @return the command usage. If the command doesn't receive any argument it returns
     * an empty string
     */
    String getUsage();

    Format getArgsFormat();
}
