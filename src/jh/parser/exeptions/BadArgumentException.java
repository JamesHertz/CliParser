package jh.parser.exeptions;

import jh.cliApp.exception.CliAppException;
import jh.parser.Argument;

public class BadArgumentException extends CliAppException {

    private static final String MSG = "Error: bad argument for %s\nExpected %s\nBut got: '%s'\n";
    private final String provided;
    private Argument argument;

    public BadArgumentException(String provided){
        this.provided = provided;
    }
    public void setArgument(Argument other){
        this.argument = other;
    }
    public String getProvided() {
        return provided;
    }
    public Argument getArgument() {
        return argument;
    }
    @Override
    public String getMessage() {
        if(argument == null) return super.getMessage();
        return null;
    }
}
