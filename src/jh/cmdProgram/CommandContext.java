package jh.cmdProgram;

import jh.parser.Argument;

import java.util.Iterator;

public interface CommandContext<T> {
    T getContext();
    Iterator<Argument> arguments();
}
