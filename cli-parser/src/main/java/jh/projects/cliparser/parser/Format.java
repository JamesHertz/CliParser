package jh.projects.cliparser.parser;


import java.util.Iterator;

public interface Format {

    Iterator<FmtArgument> getArguments();
    int size();
}
