package jh.parser;


import java.util.Iterator;

public interface Format {

    void addArgument(String name, DataType type);
    Iterator<Argument> getFormat();
    int size();
}
