package jh.parser;


import java.util.Iterator;

public interface Format {
    int numOfArgs();

    Argument getType(int idx);
    Iterator<Argument> getFormat();

    void addArgument(String name, DataType type);
    // think about this later...
    Object[] parseArgs(String[] args); // throws exception
}
