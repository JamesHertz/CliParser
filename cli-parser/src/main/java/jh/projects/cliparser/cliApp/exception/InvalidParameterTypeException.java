package jh.projects.cliparser.cliApp.exception;

import jh.projects.cliparser.parser.DataType;

import java.util.Arrays;

public class InvalidParameterTypeException extends CliAppCompilingException{
    private static final String MSG ="In method: %s: %s is not a primitive type nor a String.\nAllowed types: " + Arrays.toString(DataType.values());;
    public InvalidParameterTypeException(String method, Class<?> type){
        super(String.format(MSG, method, type));
    }
}
