package jh.projects.cliparser.cliApp.exception;

import java.lang.reflect.Method;

public class DuplicatedCommandException extends CliAppCompilingException{
    private static final String MSG = "Commanded '%s' defined twice by method '%s' and '%s'.";
    private final String cmd_name;
    private final Method method1, method2;

    public DuplicatedCommandException(String cmd_name, Method method1, Method method2) {
        super(String.format(MSG, cmd_name, method1.getName(), method2.getName()));
        this.cmd_name = cmd_name;
        this.method1 = method1;
        this.method2 = method2;
    }

    public String getCommandName() {
        return cmd_name;
    }

    public Method getMethod1() {
        return method1;
    }

    public Method getMethod2() {
        return method2;
    }
}
