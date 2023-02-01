package jh.cliApp;

import jh.cliApp.annotations.CliAppArg;
import jh.cliApp.annotations.CliAppCommand;
import jh.cliApp.exception.*;
import jh.parser.Argument;
import jh.parser.DataType;

import jh.parser.Format;
import jh.parser.ParserFormat;
import jh.parser.exeptions.BadArgumentException;

import static jh.parser.LineParser.parseLine;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class SimpleCliApp implements CliAPI{
    /*
        IDEA: use a map for managing this thing
     */

    private final Map<String, jh.cliApp.CliCommand> commands;
    private final Object cmdStore;
    private boolean running;

    /*
        TODO:
               - make a mini library with the colors
               - add a default command for exit
               - add documentation for the methods
               - build a set of useful libraries
    */

    public SimpleCliApp(Object cmdStore){
        this.cmdStore = cmdStore;
        this.running = false;
        this.commands = new TreeMap<>();
        this.getCommands(cmdStore.getClass());
    }


    private void generateCommand(CliAppCommand aux, Method m){
        Parameter[] parameters = m.getParameters();

        String cmd_name = transform_name(aux.value().isBlank() ? m.getName() : aux.value());
        // for debug
        String desc = aux.desc();
        System.out.printf("adding command: %s; desc: %s\n", cmd_name, (desc.length() > 0) ? desc : "None");

        int i = 0;
        if(parameters.length > 0 && parameters[0].getType() == CliAPI.class) ++i;
        Format format = new ParserFormat(parameters.length - i);

        for(; i < parameters.length; ++i){
            Parameter p = parameters[i];
            DataType type = DataType.getType(p.getType());

            if(type == null)
                throw new InvalidParameterTypeException(m.getName(), p.getType());

            String argName = transform_name(p.getName());
            String argDesc = "";

            CliAppArg argInfo = p.getAnnotation(CliAppArg.class);
            if(argInfo != null){
                argName = argInfo.value();
                argDesc = argInfo.desc();
            }

            format.addArgument(argName, argDesc, type);
            System.out.println("\t - adding parameter: " + argName);
        }

        m.setAccessible(true); // help from stackoverflow

        // TODO: exception if command duplicated
        commands.put(cmd_name, new AppCmd(cmd_name, aux.desc(), format, m));
        System.out.println(
                getUsageMessage(commands.get(cmd_name)) // :)
        );
    }

    private void getCommands(Class<?> container){
        for(Method m: container.getMethods()){
            CliAppCommand aux = m.getAnnotation(CliAppCommand.class);
            if(aux != null) generateCommand(aux, m);
        }
    }

    private Object[] parse_args(CliCommand cmd, String[] args) throws CliAppException {
        Format fmt = cmd.argsFormat();
        if(fmt.size() != (args.length - 1)){ // because first arg is the command name
            throw new WrongNumberOfArgsException(fmt.size(), args.length - 1);
        }

        int i = 0;
        Object[] cmd_args = new Object[cmd.parametersSize()];
        if(cmd.receivesCliApi()) cmd_args[i++] = this;

        for (Iterator<Argument> it = fmt.getArguments(); it.hasNext(); ++i) {
            Argument arg = it.next();
            cmd_args[i] = arg.parse(args[i + 1]); // +1 because the first arg is the cmd name
        }

        return cmd_args;
    }

    private void run_commands(InputStream stream) throws Exception {
        String[] args = parseLine(stream);
        if(args.length > 0){
            String commandName = args[0];
            jh.cliApp.CliCommand cmd = commands.get(commandName);
            // IDEAS: have a class named messages :)
            if(cmd == null) throw new UnknownCmdException(commandName);

            Object[] cmd_args = parse_args(cmd, args);
            cmd.run(this.cmdStore, cmd_args);
        }

    }
    @Override
    public void run() {
        final String prompt = ">> ";
        this.running = true;

        while(this.running){
            System.out.print(prompt);
            try {
                run_commands(System.in);
            //}catch (BadArgumentException e){
            //    System.out.println(e.getMessage());
            }catch (CliAppException e){
                if(e instanceof BadArgumentException){
                    System.out.println("usage: TODO!!");
                }
                System.out.printf("Error: %s\n", e.getMessage());
            } catch (Exception e){
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    @Override
    public Iterator<? extends CommandInfo> getAllCommands() {
        return commands.values().iterator();
    }

    @Override
    public Object getCmdStore() {
        return cmdStore;
    }

    @Override
    public void exit() {
        this.running = false;
    }

    // jamesHertz
    public static String transform_name(String name){
        return name.replaceAll("([a-z])([A-Z])", "$1 $2")
                    .replaceAll("_", " ")
                    .trim()
                    .replaceAll("\\s+", "-")
                    .toLowerCase();
    }

    public static String getUsageMessage(CliCommand cmd){
        StringBuilder builder = new StringBuilder("usage: ");
        Iterator<Argument> args = cmd.argsFormat().getArguments();
        if(!args.hasNext())
            builder.append("(none)");
        else{
            while(args.hasNext()){
                Argument arg = args.next();
                builder.append("<")
                        .append(arg.name())
                        .append("> ");
            }
            // TODO: print the info meessage :)
        }
        return builder.toString();
    }
}
