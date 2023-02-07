package jh.projects.cliparser.cliApp.exception;

import jh.projects.cliparser.cliApp.CliAppState;

public class InvalidOperationException extends RuntimeException{

    private final CliAppState appState;
    public InvalidOperationException(CliAppState appState) {
        super("App is: " + appState.toString().toLowerCase());
        this.appState = appState;
    }

    public CliAppState getAppState() {
        return appState;
    }
}
