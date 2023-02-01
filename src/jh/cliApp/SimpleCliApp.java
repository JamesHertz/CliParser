package jh.cliApp;

import jh.cliApp.annotations.Command;
import jh.cliApp.exception.CliAppException;
import jh.cliApp.exception.UnknownCmdException;
import jh.cliApp.exception.WrongNumberOfArgsException;
import jh.parser.Argument;
import jh.parser.DataType;

import jh.parser.Format;
import jh.parser.ParserFormat;
import jh.parser.exeptions.BadArgumentException;

import static jh.parser.LineParser.parseLine;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class SimpleCliApp implements CliAPI{
    /*
        IDEA: use a map for managing this thing
     */

    private final Map<String, CliCommand> commands;
    private final Object cmdStore;

    /*
        TODO:
               - add a default command for exit
               - add documentation for the methods
               - build a set of useful libraries
    */

    public SimpleCliApp(Object cmdStore){
        this.cmdStore = cmdStore;
        commands = new TreeMap<>();
        this.getCommands(cmdStore.getClass());
    }


    private void generateCommand(Command aux, Method m){
        Parameter[] parameters = m.getParameters();

        // for debug
        String desc = aux.desc();
        System.out.printf("adding command: %s; desc: %s\n", aux.name(), (desc.length() > 0) ? desc : "None");

        int i = 0;
        if(parameters.length > 0 && parameters[0].getType() == CliAPI.class) ++i;
        Format format = new ParserFormat(parameters.length - i);

        for(; i < parameters.length; ++i){
            Parameter p = parameters[i];
            DataType type = DataType.getType(p.getType());

            // TODO: make a more specific exception
            if(type == null) throw new RuntimeException("not primitive type provided");

            format.addArgument(p.getName(), type);

            // TODO: find a way to identify the type of parameters
            System.out.println("\t - adding parameter: " + p.getName());
        }

        m.setAccessible(true); // help from stackoverflow

        // TODO: exception if command duplicated
        commands.put(aux.name(), new AppCmd(aux.name(), aux.desc(), format, m));
    }

    private void getCommands(Class<?> container){
        for(Method m: container.getMethods()){
            Command aux = m.getAnnotation(Command.class);
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

        for (Iterator<Argument> it = fmt.getFormat(); it.hasNext(); ++i) {
            Argument arg = it.next();
            cmd_args[i] = arg.parse(args[i + 1]); // +1 because the first arg is the cmd name
        }

        return cmd_args;
    }

    private void run_commands(InputStream stream) throws IOException, CliAppException {
        String[] args = parseLine(stream);
        if(args.length > 0){
            String commandName = args[0];
            CliCommand cmd = commands.get(commandName);
            // IDEAS: have a class named messages :)
            if(cmd == null) throw new UnknownCmdException(commandName);

            Object[] cmd_args = parse_args(cmd, args);
            cmd.run(this.cmdStore, cmd_args);
        }

    }
    @Override
    public void run() {
        final String prompt = ">> ";
//        Scanner in = new Scanner(System.in);

        while(true){
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
        // do something :)
    }
}
