package jh.parser;


import java.util.Iterator;

public interface Format {
    int numOfArgs();

    DataType getType(int idx);
    Iterator<DataType> getFormat();
}
