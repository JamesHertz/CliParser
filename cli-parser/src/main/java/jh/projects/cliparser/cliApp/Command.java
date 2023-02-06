package jh.projects.cliparser.cliApp;

import jh.projects.cliparser.parser.FmtArgument;
import jh.projects.cliparser.parser.Format;
import java.lang.reflect.Method;
import java.util.Iterator;

// think about name...
class Command implements CliCommand {

    private final String cmdName, description;
    private final Format format;
    private final Method method;
    private final Object store;

    private final String usage;

    public Command(String name, String description, Format parFormat, Method method, Object store){
        this.format = parFormat;
        this.cmdName= name;
        this.description = description;
        this.method = method;
        this.store = store;
        this.usage = generateUsage();
    }

    // should I use the chain of set???
    @Override
    public String getName() {
        return cmdName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Format getArgsFormat() {
        return format;
    }

    @Override
    public int getParsSize() {
        return method.getParameters().length;
    }

    @Override
    public boolean receivesCliApi() {
        return this.getParsSize() != format.size();
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public void run(Object[] args) throws Exception{
        method.invoke(store, args);
    }

    @Override
    public String getUsage() {
        return usage;
    }

    private String generateUsage(){
        StringBuilder builder = new StringBuilder();
        Iterator<FmtArgument> args = format.getArguments();
        while(args.hasNext()){
            FmtArgument arg = args.next();
            builder.append("<")
                    .append(arg.name())
                    .append("> ");
        }
        return builder.toString();
    }
}
