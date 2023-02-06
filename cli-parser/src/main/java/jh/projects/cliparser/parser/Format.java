package jh.projects.cliparser.parser;


import java.util.Iterator;

public interface Format {

    Iterator<Argument> getArguments();
    int size();
}
