package jh.cliApp;

import jh.parser.Format;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

interface CliCommand extends CommandInfo{

    Method commandMethod();
    boolean receivesCliApi();
    int parametersSize();
    void run(Object cmdStore, Object args[]);
}
