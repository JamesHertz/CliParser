package jh.parser;


import jh.parser.exeptions.WrongNumberOfArgsException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParserFormat implements Parser {

   private final List<DataType> format;

    public ParserFormat(DataType ...types){
        this.format = List.of(types);
    }

    @Override
    public int argsLength() {
        return format.size();
    }

    @Override
    public Iterator<DataType> formatTypes() {
        return format.iterator();
    }

    @Override
    public Iterator<Argument> parse(String line) {
        String[] args = LineParser.parseLine(line);
        if(args.length != argsLength())
            throw new WrongNumberOfArgsException(argsLength(), args.length);

        List<Argument> parsedArgs = new ArrayList<>(argsLength());
        Iterator<DataType> it = formatTypes();
        for(String arg: args){
            parsedArgs.add(new Arg(it.next().parse(arg)));
        }
        return parsedArgs.iterator();
    }

    private static class Arg implements Argument{
        private Object value;
        private Arg(Object value) {
            this.value = value;
        }

        @Override
        public int toInteger() {
            return (int) value;
        }

        @Override
        public double toDecimal() {
            return (double) value;
        }

        @Override
        public String toString(){
            return (String) value;
        }
    }

}
