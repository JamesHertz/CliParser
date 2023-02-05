package jh.projects.cliparser.cliApp;

import java.lang.reflect.Method;

interface CliCommand extends CommandInfo{

    Method commandMethod();
    boolean receivesCliApi();
    int parametersSize();
    void run(Object[] args) throws Exception;
}
