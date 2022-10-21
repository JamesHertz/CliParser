package jh.cliApp;

import jh.cliApp.annotations.Command;
import jh.cliApp.exception.BadFormatException;
import jh.parser.Argument;
import static jh.parser.DataType.*;
import jh.parser.Format;
import jh.parser.ParserFormat;
import jh.parser.exeptions.WrongNumberOfArgsException;

import static jh.parser.LineParser.parseLine;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.*;

public class SimpleCliApp<T> implements CliApp<T> {
    /*
        IDEA: use a map for managing this thing
     */

    private final Map<String, CliCommand> commands;
    private final CommandContext<T> cmdCtx;

    public SimpleCliApp(Class<?> container){
        this.commands = new TreeMap<>();
        this.getCommands(container);
        cmdCtx = new CommandCtx<T>(null);
    }

    private void generateCommand(Command aux, Method m){
        // TODO: what to do when the function is empty?
        Parameter[] parameters = m.getParameters();

        /*
        TODO: uncomment later and handle the generic problem
        Class<?> firstPar = parameters[0].getType();
        if(firstPar != CommandContext.class)
            throw new BadFormatException(m.getName(), firstPar);
         */
        String desc = aux.desc();
        System.out.printf("adding command: %s; desc: %s\n", aux.name(), (desc.length() > 0) ? desc : "None");


        Format format = new ParserFormat(parameters.length); // what about no parameter functions
        for(int i = 1; i < parameters.length; i++){
            Parameter p = parameters[i];
            // TODO:
            //  - find a way to get the real type based on p.getType() and have a exception when it's something else than
            //  a "native" datatype or a String
            format.addArgument(p.getName(), DECIMAL);

            // TODO: find a way to identify the type of parameters
            System.out.println("\t - adding parameter: " + p.getName());
        }

        m.setAccessible(true); // help from stackoverflow
        // TODO: exception if command duplicated
        commands.put(aux.name(), new CliCmd(aux.name(), aux.desc(), format, m));
    }
    private void getCommands(Class<?> container){
        for(Method m: container.getMethods()){
            Command aux = m.getAnnotation(Command.class);
            if(Modifier.isStatic(m.getModifiers()) && aux != null)
                generateCommand(aux, m);
        }
    }
    @Override
    public void run() {
        final String prompt = ">> ";
        Scanner in = new Scanner(System.in);

        while(true){
            System.out.print(prompt);
            String[] args = parseLine(in.nextLine()); // a catch block later

            // parse line
            if(args.length  == 0) continue;

            // select the command
            CliCommand cmd = commands.get(args[0]);

            if(cmd == null){
                System.out.println("Unknown command: " + args[0]);
            }else{
                cmd.run(cmdCtx, args);
                System.out.println();
            }

        }

    }

}
