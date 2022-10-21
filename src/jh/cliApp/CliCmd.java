package jh.cliApp;

import jh.parser.Format;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

// think about name...
public class CliCmd implements CliCommand {

    private final String cmdName, description;
    private final Format format;
    private final Method method;

    public CliCmd(String name, String description, Format parFormat, Method method){
        this.format = parFormat;
        this.cmdName= name;
        this.description = description;
        this.method = method;
    }
    @Override
    public String commandName() {
        return cmdName;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public Format argsFormat() {
        return format;
    }

    @Override
    public Method commandMethod() {
        return method;
    }


    @Override
    public <T> void run(CommandContext<T> ctx, String[] args){
        // should I change it from String[] to List<String> ???
        Object[] parsedArgs = new Object[args.length];

        // look at this later
        Object[] aux = format.parseArgs(Arrays.copyOfRange(args, 1, args.length));
        parsedArgs[0] = ctx;
        System.arraycopy(aux, 0, parsedArgs, 1, parsedArgs.length - 1);

        try{
            // look at this later
            method.invoke(null, parsedArgs);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }



}
