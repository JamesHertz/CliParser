package jh.projects.cliparser.cliApp;

import jh.projects.cliparser.parser.Format;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

// think about name...
class AppCmd implements CliCommand {

    private final String cmdName, description;
    private final Format format;
    private final Method method;

    private final Object store;

    public AppCmd(String name, String description, Format parFormat, Method method, Object store){
        this.format = parFormat;
        this.cmdName= name;
        this.description = description;
        this.method = method;
        this.store = store;
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
    public void run(Object[] args) throws Exception{
//         Object self = (Modifier.isStatic(method.getModifiers())) ?  null : store;
        // if store is null means it's an static method :)
        method.invoke(store, args);
    }
}
