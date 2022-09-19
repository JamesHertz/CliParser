package jh.cliApp;

import jh.parser.DataType;
import jh.parser.Format;
import jh.parser.ParserFormat;

import java.util.function.Consumer;

public class CmdCommand<T> implements Command<T> {

    private final Consumer<CommandContext<T>> cmdFunc;
    private final String cmdName;
    private final Format format;

    public CmdCommand(String name, Consumer<CommandContext<T>> cmdFunc, DataType ...args){
        this.cmdName = name;
        this.cmdFunc = cmdFunc;
        this.format = new ParserFormat(args);
    }
    @Override
    public String commandName() {
        return cmdName;
    }

    @Override
    public Format argsFormat() {
        return format;
    }

    @Override
    public void runCommand(CommandContext<T> ctx) {
        cmdFunc.accept(ctx);
    }
}
