package jh.projects.cliparser.cliApp;

import jh.projects.cliparser.parser.Format;

public interface CommandInfo {
    String getName();
    String getDescription();

    /**
     * @return the command usage. If the command doesn't receive any argument it returns
     * an empty string
     */
    String getUsage();

    // TODO: solve format problem, by sending all the formats to the constructor
}
