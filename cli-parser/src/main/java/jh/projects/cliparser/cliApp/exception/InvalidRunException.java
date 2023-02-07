package jh.projects.cliparser.cliApp.exception;

import jh.projects.cliparser.cliApp.CliAppState;

import static jh.projects.cliparser.cliApp.CliAppState.RUNNING;
public class InvalidRunException extends InvalidOperationException{
    public InvalidRunException(CliAppState appState) {
        super(appState);
    }
}
