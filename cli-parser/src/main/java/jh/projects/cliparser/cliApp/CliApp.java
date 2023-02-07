package jh.projects.cliparser.cliApp;

import jh.projects.cliparser.cliApp.api.CliAPI;

public interface CliApp {
    void run();
    CliAppState getAppState();
    CliAPI getAppAPI();
}
