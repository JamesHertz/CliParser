package jh.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParserFormat implements Format {

   private record CmdArgument(String name, DataType type) implements Argument {
       @Override
        public Object parse(String value) {
            return type.parse(value);
        }
   };
   private final List<Argument> format;

    public ParserFormat(int size){
        this.format = new ArrayList<>(size);
    }

    @Override
    public Iterator<Argument> getFormat() {
        return format.iterator();
    }

    @Override
    public int size(){
        return format.size();
    }

    public void addArgument(String name, DataType type){
         format.add(new CmdArgument(name, type));
    }
    /*
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

     */
}
