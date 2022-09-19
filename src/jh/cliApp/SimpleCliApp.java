package jh.cliApp;

import jh.parser.Argument;
import jh.parser.DataType;
import jh.parser.Format;
import jh.parser.exeptions.WrongNumberOfArgsException;

import static jh.parser.LineParser.parseLine;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.*;

public class SimpleCliApp<T> implements CliApp<T> {
    /*
        IDEA: use a map for managing this thing
     */

    private final Class<?> container;
    private final Map<String, Command<T>> commands;

    public SimpleCliApp(Class<?> container){
        this.container = container;
        this.commands = new TreeMap<>();
        this.getCommands();
        // get commands
    }

    private void getCommands(){
        for(Method m: container.getMethods()){
            if(Modifier.isStatic(m.getModifiers())){
                // check if format is suitable
                // make a data structure to save the command name
                // and the list of it's args types and name...
            }
        }
    }
    @Override
    public void run() {
        /*
        List<String> args;
        Command<T> cmd;
        String target;
        //boolean done = false;
        while(true){
            // but now
            System.out.print(">>> ");
            args = parseLine(input.nextLine());
            if(args.size() > 0){
                target = args.get(0);
                cmd = commands.get(target);
                if(cmd == null)
                    System.out.printf("Unknown command '%s'", target);
                else {
                    args = args.subList(1, args.size()); // look at this later
                    defCtx.setArgs(checkArgs(cmd.argsFormat(), args));
                    cmd.runCommand(defCtx);
                }
            }

            // TODO:
            //  - find the command
            //  - than parse the args with the cmd Format
            //  - think about prompt
            // use starts with or??
        }
         */
    }

    private List<Argument> checkArgs(Format fmt, List<String> args){
        if(args.size() != fmt.numOfArgs())
            throw new WrongNumberOfArgsException(fmt.numOfArgs(), args.size());

        List<Argument> parsedArgs = new ArrayList<>(fmt.numOfArgs());
        Iterator<DataType> it = fmt.getFormat();
        for(String arg: args){
            parsedArgs.add(new Arg(it.next().parse(arg)));
        }
        return parsedArgs;
    }

    private static class Arg implements Argument {
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
