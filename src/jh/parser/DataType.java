package jh.parser;

import jh.parser.exeptions.BadArgumentException;

public enum DataType {

    INTEGER(Integer::parseInt, int.class),
    LONG(Long::parseLong, long.class),
    BYTE(Byte::parseByte, byte.class),
    FLOAT(Float::parseFloat, float.class),
    DOUBLE(Double::parseDouble, double.class),
    STRING(value -> value, String.class),
    BOOLEAN(value -> {
        if(value.equals("true")) return true;
        else if (value.equals("false")) return false;
        else throw new RuntimeException();
    }, boolean.class),
    CHAR( value -> { // still think about this....
        if(value.length() > 1) throw new RuntimeException();
        return value.charAt(0);
    }, char.class);


    private final String typeDesc;
    private final ParseFunc argParser;
    private final Class<?> mType;

    DataType(ParseFunc argParser, Class<?> mType){
        this.typeDesc = this.name().toLowerCase();
        this.argParser = argParser;
        this.mType = mType;
    }

    public Object parse(String arg){
//        try{
        //}catch (Exception e){ // TODO: work over here
        //    throw new BadArgumentException(this.typeDesc, arg, e.getMessage());
        //}
        // by now :)
        return argParser.parse(arg);
    }

    public static DataType getType(Class<?> type){
        for(DataType d : values()){
            if(d.mType == type) return d;
        }
        return null;
    }

    @FunctionalInterface
    interface ParseFunc{
        Object parse(String value);
    }

}
