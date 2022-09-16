package jh.parser;

import jh.parser.exeptions.BadArgumentException;

public enum DataType {

    INTEGER(Integer::parseInt),
    DECIMAL(Double::parseDouble),
    STRING(value -> value);


    private final String typeDesc;
    private final ParseFunc argParser;

    DataType(ParseFunc argParser){
        this.typeDesc = this.name().toLowerCase();
        this.argParser = argParser;
    }


    public Object parse(String arg){
        try{
            return argParser.parse(arg);
        }catch (Exception e){
            throw new BadArgumentException(arg, this.typeDesc, e.getMessage());
        }
    }


    @FunctionalInterface
    interface ParseFunc{
        Object parse(String value);
    }

}
