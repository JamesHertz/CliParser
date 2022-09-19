package jh.cliApp;

import jh.parser.Format;

import java.lang.reflect.Method;

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



}
