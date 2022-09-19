package jh.parser;


public record CmdArgument(String name, DataType type) implements Argument {

    @Override
    public Object parse(String value) {
        return type.parse(value);
    }
}
