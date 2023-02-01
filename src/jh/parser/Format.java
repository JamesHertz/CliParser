package jh.parser;


import java.util.Iterator;

public interface Format {

    void addArgument(String name, String description, DataType type);
    Iterator<Argument> getArguments();
    int size();
}
