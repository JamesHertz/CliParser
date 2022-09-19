package jh.cliApp;

import jh.parser.Argument;

import java.util.List;

interface EditCmdContext<T> extends CommandContext<T> {
    void setArgs(List<Argument> args);
}
