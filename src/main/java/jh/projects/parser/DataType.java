package jh.projects.parser;


import jh.projects.parser.exeptions.BadArgumentException;

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
        else throw new BadArgumentException(value);
    }, boolean.class),
    CHAR( value -> { // still think about this....
        if(value.length() > 1) throw new BadArgumentException(value); //throw new RuntimeException();
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

    public Class<?> getBaseType(){
        return this.mType;
    }

    public Object parse(String arg) throws BadArgumentException{
        try{
            return argParser.parse(arg);
        }catch (NumberFormatException e){
            throw new BadArgumentException(arg) ;
        }
    }

    public static DataType getType(Class<?> type) {
        for(DataType d : values()){
            if(d.mType == type) return d;
        }
        return null;
    }

    @Override
    public String toString(){
        return mType.toString();
    }

    @FunctionalInterface
    interface ParseFunc{
        Object parse(String value) throws BadArgumentException;
    }



}
