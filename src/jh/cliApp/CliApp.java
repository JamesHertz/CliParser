package jh.cliApp;

import jh.cliApp.annotations.Command;

import java.util.Iterator;

public interface CliApp {
    void run();
    Iterator< ? extends CommandInfo> getAllCommands();
}
