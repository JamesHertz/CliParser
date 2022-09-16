package jh.parser;


import java.util.Iterator;

public interface Parser {
    int argsLength();
    Iterator<DataType> formatTypes();
    Iterator<Argument> parse(String line);
}
