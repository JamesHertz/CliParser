package jh.projects.cliparser.cliApp;

import java.util.Iterator;

public interface CliAPI {

    Object getCmdStore();
    Iterator< ? extends CommandInfo> getAllCommands();
    void exit();
}
