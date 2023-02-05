package jh.projects.cliparser.cliApp;

import jh.projects.cliparser.cliApp.annotations.CliAppArg;
import jh.projects.cliparser.cliApp.annotations.CliAppCommand;
import jh.projects.cliparser.cliApp.exception.*;
import jh.projects.cliparser.cliApp.listeners.*;
import jh.projects.cliparser.parser.Argument;
import jh.projects.cliparser.parser.DataType;

import jh.projects.cliparser.parser.Format;
import jh.projects.cliparser.parser.ParserFormat;
import jh.projects.cliparser.parser.exeptions.BadArgumentException;

import static jh.projects.cliparser.parser.LineParser.parseLine;
import static jh.projects.cliparser.cliApp.Utils.*;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.*;

public class SimpleCliApp implements CliAPI, CliApp{

    private final Map<String, CommandInfo> commands;
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
        this.getCommands(DefaultCommands.class); // by now :)
    }

    private void generateCommand(CliAppCommand aux, Method m){
        Parameter[] parameters = m.getParameters();

        String cmd_name = transform_name(aux.value().isBlank() ? m.getName() : aux.value());
        {
           CliCommand cmd = getCommand(cmd_name);
           if(cmd != null){
               if(m.getDeclaringClass() == DefaultCommands.class) return;
               else throw new DuplicatedCommandException(cmd_name, cmd.commandMethod(), m);
           }
        }
        // TODO: add exception here :)

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
        //    System.out.println("\t - adding parameter: " + argName);
        }

        m.setAccessible(true); // help from stackoverflow

        // TODO: exception if command duplicated
        Object cmd_store = (Modifier.isStatic(m.getModifiers())) ? null : this.cmdStore;
        commands.put(cmd_name, new AppCmd(cmd_name, aux.desc(), format, m, cmd_store));
        //System.out.println(
        //        getUsageMessage(
        //            getCommand(cmd_name)
        //            //commands.get(cmd_name)
        //        )
        //);
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

        int cmd_idx = 0, arg_idx = 1; // arg_idx is 1 because the first arg is the cmd name
        Object[] cmd_args = new Object[cmd.parametersSize()];
        if(cmd.receivesCliApi()) cmd_args[cmd_idx++] = this;

        for (Iterator<Argument> it = fmt.getArguments(); it.hasNext();) {
            Argument arg = it.next();
            cmd_args[cmd_idx++] = arg.parse(args[arg_idx++]);
        }

        return cmd_args;
    }

    // TODO: give a method in the api allowing executing other commands
    private void run_commands(InputStream stream) throws Exception {
        String[] args = parseLine(stream);
        if(args.length > 0){
            String commandName = args[0];
            CliCommand cmd = getCommand(commandName);// commands.get(commandName);
            // IDEAS: have a class named messages :)
            if(cmd == null) throw new UnknownCmdException(commandName);

            Object[] cmd_args = parse_args(cmd, args);
            cmd.run(cmd_args);
        }

    }
    @Override
    public void run() {
        final String prompt = ">> ";

        this.running = true;
        if(this.cmdStore instanceof CliRunListener){
            ((CliRunListener) cmdStore).onRun(this);
        }

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
    public Iterator<CommandInfo> getAllCommands() {
        return commands.values().iterator();
    }

    @Override
    public Object getCmdStore() {
        return cmdStore;
    }

    public void exit() {
        this.running = false;
        if(this.cmdStore instanceof CliExitListener)
            ((CliExitListener) cmdStore).onExit(this);
    }

    // jamesHertz
    private String transform_name(String name){
        return name.replaceAll("([a-z])([A-Z])", "$1 $2")
                    .replaceAll("_", " ")
                    .trim()
                    .replaceAll("\\s+", "-")
                    .toLowerCase();
    }

    private CliCommand getCommand(String commandName){
        return (CliCommand) commands.get(commandName);
    }
}
