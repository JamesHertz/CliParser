package jh.parser;

import jh.parser.exeptions.BadArgumentException;

public interface Argument {

    String name();
    DataType type();
    Object parse(String value) throws BadArgumentException; // throws exception
}
