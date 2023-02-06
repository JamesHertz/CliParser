package jh.projects.cliparser.parser;

import java.util.Iterator;
import java.util.List;

public class ParserFormat implements Format {
    // TO-Think: should I move the getUsage method to here ?
   private final List<FmtArgument> format;

    public ParserFormat(FmtArgument...args) {
        format = List.of(args);
    }

    @Override
    public Iterator<FmtArgument> getArguments() {
        return format.iterator();
    }

    @Override
    public int size(){
        return format.size();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
