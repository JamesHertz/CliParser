package jh.projects.cliparser.parser;

import jh.projects.cliparser.parser.exeptions.BadArgumentException;

public interface Argument {

    String name();
    DataType type();
    String description();
    Object parse(String value) throws BadArgumentException; // throws exception
}
