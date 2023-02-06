package jh.projects.cliparser.cliApp.exception;

import jh.projects.cliparser.cliApp.CommandInfo;

public class WrongNumberOfArgsException extends CliAppException{

    private static final String MSG = "Expected %d args but provided %s args.";
    private final int expected, provided;
    public WrongNumberOfArgsException(int expected, int provided){
        super(String.format(MSG, expected, provided));
        this.expected = expected;
        this.provided = provided;
    }

    int expectedArgs(){
        return expected;
    }
    int provided(){
        return provided;
    }

}
