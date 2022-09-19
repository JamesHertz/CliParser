package jh.cliApp;

import jh.cliApp.annotations.Command;
import jh.cliApp.exception.BadFormatException;
import jh.parser.Argument;
import static jh.parser.DataType.*;
import jh.parser.Format;
import jh.parser.ParserFormat;
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
    private final Map<String, CliCommand> commands;

    public SimpleCliApp(Class<?> container){
        this.container = container;
        this.commands = new TreeMap<>();
        this.getCommands();
        // get commands
    }

    private void generateCommand(Command aux, Method m){
        // TODO: what to do when the function is empty?
        Parameter[] parameters = m.getParameters();
        if(parameters[0].getType() != CommandContext.class)
            throw new BadFormatException(m.getName(), parameters[0].getType());
        Format format = new ParserFormat(parameters.length - 1); // what about no parameter functions
        for(int i = 1; i < parameters.length; i++){
            Parameter p = parameters[i];
            // TODO:
            //  - find a way to get the real type based on p.getType() and have a exception when it's something else than
            //  a "native" datatype or a String
            format.addArgument(p.getName(), DECIMAL);
        }

        // TODO: exception if command duplicated
        commands.put(aux.name(), new CliCmd(aux.name(), aux.desc(), format, m));
    }
    private void getCommands(){
        for(Method m: container.getMethods()){
            Command aux = m.getAnnotation(Command.class);
            if(Modifier.isStatic(m.getModifiers()) && aux != null)
                generateCommand(aux, m);

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

}
