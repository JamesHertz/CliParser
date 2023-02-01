package jh.parser;

import jh.parser.exeptions.BadArgumentException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParserFormat implements Format {

   private record CmdArgument(String name, String description, DataType type) implements Argument {
       @Override
        public Object parse(String value) throws BadArgumentException {
           try{
               return type.parse(value);
           } catch (BadArgumentException ex) {
               ex.setArgument(this);
               throw ex;
           }
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

    public void addArgument(String name, String description, DataType type){
         format.add(new CmdArgument(name, description, type));
    }

    @Override
    public String toString() {
        // usage: name name name
        // Error wrong parameter type <name>: 'value'
        return super.toString();
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
