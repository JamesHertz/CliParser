package jh.cliApp;

import jh.cliApp.annotations.Command;
import jh.parser.Argument;
import jh.parser.DataType;
import jh.parser.Format;
import jh.parser.exeptions.WrongNumberOfArgsException;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.*;

public class SimpleCliApp<T> implements CliApp<T> {
    /*
        IDEA: use a map for managing this thing
     */

    private final Class<?> container;
    private final Map<String, CliCommand<T>> commands;

    public SimpleCliApp(Class<?> container){
        this.container = container;
        this.commands = new TreeMap<>();
        this.getCommands();
        // get commands
    }

    private void getCommands(){
        for(Method m: container.getMethods()){
            Command aux = m.getAnnotation(Command.class);
            if(Modifier.isStatic(m.getModifiers()) && aux != null){
                // and the list of it's args types and name...
                for(Parameter p: m.getParameters()){
                    if(p.getType() == int.class){
                        System.out.println("I got an integer");
                    }
                    else if(p.getType() == boolean.class){
                        System.out.println("I got an boolean");
                    }else System.out.println(p.getType());

                }
                // make a data structure to save the command name
                // check if format is suitable
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
            //parsedArgs.add(new Arg(it.next().parse(arg)));
        }
        return parsedArgs;
    }
}
