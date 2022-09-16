package jh.cmdProgram;

import jh.parser.Argument;

import java.util.Iterator;
import java.util.List;

public class CommandCtx<T> implements CommandContext<T>{
    private final T ctx;
    private final List<Argument> args;

    public CommandCtx(T ctx, List<Argument> args){
        this.args = args;
        this.ctx = ctx;
    }

    @Override
    public Iterator<Argument> arguments() {
        return args.iterator();
    }

    @Override
    public T getContext() {
        return ctx;
    }
}
