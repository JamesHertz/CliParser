package jh.projects.cliparser.cliApp.exception;

import jh.projects.cliparser.cliApp.CliAppState;

public class InvalidExitException extends InvalidOperationException{

    public InvalidExitException(CliAppState appState) {
        super(appState);
    }
}
