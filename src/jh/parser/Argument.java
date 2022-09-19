package jh.parser;

public interface Argument {

    String name();
    DataType type();
    Object parse(String value); // throws exception
}
