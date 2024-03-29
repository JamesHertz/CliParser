package jh.projects.cliparser.cliApp;

import jh.projects.cliparser.cliApp.annotations.CliAppArg;
import jh.projects.cliparser.cliApp.annotations.CliAppCommand;
import jh.projects.cliparser.cliApp.api.CliAPI;
import jh.projects.cliparser.cliApp.api.CliTable;
import jh.projects.cliparser.cliApp.api.Table;
import jh.projects.cliparser.cliApp.exception.*;
import jh.projects.cliparser.cliApp.listeners.*;
import jh.projects.cliparser.parser.Argument;
import jh.projects.cliparser.parser.DataType;

import jh.projects.cliparser.parser.Format;
import jh.projects.cliparser.parser.ParserFormat;
import jh.projects.cliparser.parser.exeptions.BadArgumentException;

import static jh.projects.cliparser.parser.LineParser.parseLine;

import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.*;

public class SimpleCliApp implements CliAPI, CliApp{

    private final Map<String, CommandInfo> commands;
    private final Object cmdStore;
    private boolean running;

    /*
    class Api implements CliAPI{

        @Override
        public Object getCmdStore() {
            return null;
        }

        @Override
        public Iterator<CommandInfo> getAllCommands() {
            return SimpleCliApp.this.getAllCommands();
        }

        @Override
        public CliTable createTable(String[] headers) {
            return new Table(headers);
        }

        @Override
        public void exit() {
            SimpleCliApp.this.exit();
        }
    }
     */

    /*
        TODO:
               X add a default command for exit
               - make a mini library with the colors
               - add documentation for the methods
               - build a set of useful libraries
    */

    public SimpleCliApp(Object cmdStore){
        this.cmdStore = cmdStore;
        this.running = false;
        this.commands = new TreeMap<>();

        this.extractCommands(DefaultCommands.class);  // add the default commands :)
        this.extractCommands(cmdStore.getClass());
    }

    @Override
    public void run() {
        final String prompt = ">> ";

        this.running = true;
        if(this.cmdStore instanceof CliRunListener){
            ((CliRunListener) cmdStore).onRun(this);
        }

        while(this.running){ // exit in ctrl+d ??
            System.out.print(prompt);
            try {
                run_commands(System.in);
            }catch (BadArgumentException | WrongNumberOfArgsException e){
                String usage = e.getRelatedCommand().getUsage();
                if(!usage.isEmpty()) System.out.printf("usage: %s\n", usage);
                System.out.printf("Error: %s\n", e.getMessage());
            } catch (CliAppException e){
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

    @Override
    public void exit() {
        this.running = false;
        if(this.cmdStore instanceof CliExitListener)
            ((CliExitListener) cmdStore).onExit(this);
    }


    @Override
    public CliTable createTable(String[] headers) {
        return new Table(headers);
    }

    private void extractCommands(Class<?> container){
        for(Method m: container.getMethods()){
            CliAppCommand aux = m.getAnnotation(CliAppCommand.class);
            if(aux != null) addCommand(aux, m);
        }
    }
    private void addCommand(CliAppCommand aux, Method m){
        Parameter[] parameters = m.getParameters();
        String cmd_name = transform_name(aux.key().isBlank() ? m.getName() : aux.key());

        {
           CliCommand cmd = getCommand(cmd_name);
           if(cmd != null && cmd.getMethod().getDeclaringClass() != DefaultCommands.class)
               throw new DuplicatedCommandException(cmd_name, cmd.getMethod(), m);
        }

        int idx = 0;
        if(parameters.length > 0 && parameters[0].getType() == CliAPI.class) ++idx;

        Format format = new ParserFormat(parameters.length - idx);
        for(; idx < parameters.length; ++idx){
            Parameter p = parameters[idx];
            DataType type = DataType.getType(p.getType());

            if(type == null)
                throw new InvalidParameterTypeException(m.getName(), p.getType());

            String argName = p.getName();
            String argDesc = "";

            CliAppArg argInfo = p.getAnnotation(CliAppArg.class);
            if(argInfo != null){
                argName = argInfo.key();
                argDesc = argInfo.desc();
            }

            format.addArgument(transform_name(argName), argDesc, type);
        }

        m.setAccessible(true); // help from stackoverflow
        Object cmd_store = (Modifier.isStatic(m.getModifiers())) ? null : this.cmdStore;
        commands.put(cmd_name, new Command(cmd_name, aux.desc(), format, m, cmd_store));
    }

    private void run_commands(InputStream stream) throws Exception {
        String[] args = parseLine(stream);
        if(args.length > 0){
            String commandName = args[0];
            CliCommand cmd = getCommand(commandName);
            // IDEAS: have a class named messages :)
            if(cmd == null) throw new UnknownCmdException(commandName);

            Object[] cmd_args = parse_args(cmd, args);
            cmd.run(cmd_args);
        }

    }

    private Object[] parse_args(CliCommand cmd, String[] args) throws CliAppException {
        Format fmt = cmd.getArgsFormat();
        try{
             if(fmt.size() != (args.length - 1)){ // because first arg is the command name
                throw new WrongNumberOfArgsException(fmt.size(), args.length - 1).setCommand(cmd);
            }

            int cmd_idx = 0, arg_idx = 1; // arg_idx is 1 because the first arg is the cmd name
            Object[] cmd_args = new Object[cmd.getParsSize()];
            if(cmd.receivesCliApi()) cmd_args[cmd_idx++] = this;

            for (Iterator<Argument> it = fmt.getArguments(); it.hasNext();) {
                Argument arg = it.next();
                cmd_args[cmd_idx++] = arg.parse(args[arg_idx++]);
            }

            return cmd_args;
        }catch (CliAppException e){
            throw e.setCommand(cmd);
        }
    }

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
