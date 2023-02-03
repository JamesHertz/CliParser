package jh.projects.cliparser.cliApp;

import java.util.Iterator;

public interface CliApp {
    // void start();
    // void stop();
    // void exit();
    void run();
    Iterator< ? extends CommandInfo> getAllCommands();
}
