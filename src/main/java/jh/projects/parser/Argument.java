package jh.projects.parser;

import jh.projects.parser.exeptions.BadArgumentException;

public interface Argument {

    String name();
    DataType type();
    String description();
    Object parse(String value) throws BadArgumentException; // throws exception
}
