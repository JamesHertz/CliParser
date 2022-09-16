package jh.parser.exeptions;

public class BadArgumentException extends RuntimeException{
    private static final String MSG = "Got: %s but expected a %s.\nError: ...%s";
    private final String arg, expected;
    public BadArgumentException(String arg, String expected, String msg){
        super(String.format(MSG, expected, arg, msg));
        this.arg = arg;
        this.expected = expected;
    }

    public String expectedDataType(){
        return expected;
    }

    public String providedArg(){
        return arg;
    }
}
