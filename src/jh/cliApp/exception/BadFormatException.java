package jh.cliApp.exception;

import jh.cliApp.CommandContext;

public class BadFormatException extends RuntimeException{
    // by now ....
    // idea have types of bad format .... by integer number
    public BadFormatException(String methodName, Class<?> found){
        super(String.format("In method %s first parameter is %s but expected %s.",
                methodName, found, CommandContext.class));
    }
}
