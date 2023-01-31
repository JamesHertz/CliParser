package jh.cliApp;

import jh.parser.Format;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

// think about name...
class AppCmd implements CliCommand {

    private final String cmdName, description;
    private final Format format;
    private final Method method;

    public AppCmd(String name, String description, Format parFormat, Method method){
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
    public int parametersSize() {
        return method.getParameters().length;
    }

    @Override
    public boolean receivesCliApi() {
        return this.parametersSize() != format.size();
    }

    @Override
    public Method commandMethod() {
        return method;
    }

    @Override
    public void run(Object cmdStore, Object[] args) {
        try{
            Object self = (Modifier.isStatic(method.getModifiers())) ?  null : cmdStore;
            method.invoke(self, args);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }


    /*
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
     */


}
