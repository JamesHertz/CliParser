package jh.parser;



import jh.parser.exeptions.WrongNumberOfArgsException;

import java.lang.invoke.WrongMethodTypeException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParserFormat implements Format {

   private final List<Argument> format;

    public ParserFormat(int number){
        this.format = new ArrayList<>(number);
    }

    public void addArgument(String name, DataType type){
        format.add(new CmdArgument(name, type));
    }
    @Override
    public int numOfArgs() {
        return format.size();
    }

    @Override
    public Argument getType(int idx) {
        return format.get(idx);
    }

    @Override
    public Iterator<Argument> getFormat() {
        return format.iterator();
    }

    @Override
    public Object[] parseArgs(String[] args) {
        if(args.length != format.size())
            throw new WrongNumberOfArgsException(format.size(), args.length);

        Object[] result = new Object[args.length];
        Iterator<Argument> pars = format.iterator();
        for(int i  = 0; i < args.length; i++){
            result[i] = pars.next().parse(args[i]);
        }

        return result;
    }

}
