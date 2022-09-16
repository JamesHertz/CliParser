package jh.cmdProgram;

import jh.parser.Argument;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class CommandCtx<T> implements EditCmdContext<T>{
    private static final List<Argument> EMPTY = new LinkedList<>();
    private final T ctx;
    private List<Argument> args;

    public CommandCtx(T ctx){
        this(ctx, EMPTY);
    }
    public CommandCtx(T ctx, List<Argument> args){
        this.args = args;
        this.ctx = ctx;
    }

    public void setArgs(List<Argument> args){
        this.args = args;
    }
    // set Args
    @Override
    public Iterator<Argument> arguments() {
        return args.iterator();
    }

    @Override
    public T getContext() {
        return ctx;
    }
}
