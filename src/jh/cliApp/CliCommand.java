package jh.cliApp;

import jh.parser.Format;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public interface CliCommand {
    String commandName();

    String description();
    Format argsFormat();
    Method commandMethod();

    <T> void run(CommandContext<T> t, String[] args);
}
