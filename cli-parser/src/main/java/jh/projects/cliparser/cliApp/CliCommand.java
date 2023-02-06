package jh.projects.cliparser.cliApp;

import jh.projects.cliparser.parser.Format;

import java.lang.reflect.Method;

interface CliCommand extends CommandInfo{

    Method getMethod();
    boolean receivesCliApi();
    int getParsSize();
    void run(Object[] args) throws Exception;
}
