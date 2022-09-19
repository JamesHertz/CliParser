package jh.cliApp;

import jh.parser.Format;

import java.lang.reflect.Method;

public interface CliCommand {
    String commandName();

    String description();
    Format argsFormat();
    Method commandMethod();
}
