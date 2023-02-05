package jh.projects.cliparser.cliApp.api;

import jh.projects.cliparser.cliApp.CommandInfo;

import java.util.Iterator;

public interface CliAPI {

    Object getCmdStore(); // should I deprecate it??
    Iterator<CommandInfo> getAllCommands();
    CliTable createTable(String[] headers);
    void exit();
    /*
        CliTable createTable(int row, int col);

     */
}
