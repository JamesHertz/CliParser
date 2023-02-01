package jh.cliApp;

import java.util.Iterator;

public interface CliApp {
    void run();
    Iterator< ? extends CommandInfo> getAllCommands();
}
