package jh.projects.cliparser.cliApp.api;

import jh.projects.cliparser.cliApp.CommandInfo;
import jh.projects.cliparser.cliApp.api.form.CliForm;
import jh.projects.cliparser.cliApp.api.table.CliTable;

import java.util.Iterator;

public interface CliAPI {
    // FUTURE-PLAN: integrate this https://github.com/awegmann/consoleui in here.

    Object getCmdStore(); // should I deprecate it??
    Iterator<CommandInfo> getAllCommands();

    // TODO: a method Builder getInputBuilder() or something like this
    CliTable createTable(String[] headers);
    CliTable createTable(int cols);
    CliForm createForm();
    void exit();
    /*
        CliTable createTable(int row, int col);

     */
}
