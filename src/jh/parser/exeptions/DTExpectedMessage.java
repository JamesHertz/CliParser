package jh.parser.exeptions;

import java.util.HashMap;
import java.util.Map;

public class DTExpectedMessage {
    private static final String DEFAULT_INTEGER_FMT = "an integer number in the range [%,d ; %,d]";
    private static final String DEFAULT_DECIMAL_FMT = "a decimal number in the range [%e ; %e]";
    private static final Map<Class<?>, String> messages = new HashMap<>(){{
        put(int.class, String.format(DEFAULT_INTEGER_FMT, Integer.MIN_VALUE, Integer.MAX_VALUE));
        put(short.class, String.format(DEFAULT_INTEGER_FMT, Short.MIN_VALUE, Short.MAX_VALUE));
        put(long.class, String.format(DEFAULT_INTEGER_FMT, Long.MIN_VALUE, Long.MAX_VALUE));
        put(byte.class, String.format(DEFAULT_INTEGER_FMT, Byte.MIN_VALUE, Byte.MAX_VALUE));
        put(float.class, String.format(DEFAULT_DECIMAL_FMT, Float.MIN_VALUE, Float.MAX_VALUE));
        put(double.class, String.format(DEFAULT_DECIMAL_FMT, Double.MIN_VALUE, Double.MAX_VALUE));
        put(char.class, "Expected a single character.");
    }};

    public static String getExpectedMessage(Class<?> type){
        return messages.get(type);
    }
}
