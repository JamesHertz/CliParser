package jh.projects.cliparser.cliApp.api;

import jh.projects.cliparser.cliApp.CommandInfo;
import jh.projects.cliparser.cliApp.api.form.CliForm;
import jh.projects.cliparser.cliApp.api.table.CliTable;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public interface CliAPI {
    // FUTURE-PLAN: integrate this https://github.com/awegmann/consoleui in here.

    /*
        InputStream getInput();
        PrintWriter getOutput();
    */

    Scanner getInputScanner();
    Object getCmdStore(); // should I deprecate it??
    Iterator<CommandInfo> getAllCommands();

    // todo: think of a better way to create this controllers
    CliTable createTable(String[] headers);
    CliTable createTable(int cols);
    CliForm createForm();
    String prompt(String msg);
    String prompt();

    void exit();

    /*
        IDEAS:
             CliFormValue => PromptValue
             PromptValue[] prompt(...Datatype);
     */
}
