package jh.projects.cliparser.cliApp;

import java.util.Iterator;

public interface CliAPI {

    Object getCmdStore(); // should I deprecate it??
    Iterator<CommandInfo> getAllCommands();
    void exit();
}
