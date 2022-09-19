import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import static jh.parser.LineParser.parseLine;

public class Main {
/*
    private static String disEscape(String arg) {
        return arg.replace("\\n", "\n")
                .replace("\\b", "\b")
                .replace("\\r", "\r")
                .replace("\\f", "\f")
                .replace("\\\"", "\"")
                .replace("\\'", "'")
                .replace("\\\\", "\\");
    }
    private static boolean isQuote(char c) {
        return c == '\'' || c == '"';
    }

    private static String[] parseLineII(String line) {
        List<String> args = new LinkedList<>();
        boolean inQuote = false;
        boolean inWord = false;
        int track = 0;
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (ch == '/') ++i; // we want to skip 2 characters
            else {
                if (inWord) {

                    if (Character.isWhitespace(ch) || isQuote(ch)) {
                        args.add(line.substring(track, i));
                        inWord = false;
                        --i;
                    }
                    continue;
                }

                if(inQuote){
                    if(ch == line.charAt(track)){
                        inQuote = false;
                        args.add(disEscape(line.substring(track+1, i)));
                    }
                    continue;
                }

                if(!Character.isWhitespace(ch)){
                    track = i;
                    if(isQuote(ch)) inQuote = true;
                    else inWord = true;
                }

            }
        }
        if(inQuote) // an exception
            System.out.println("bad quote: " + line.substring(0, track+1)) ;
        // raise exception here
        return args.toArray(new String[args.size()]);

    }

 */


    static class Auto{
        public static void doSomething(int name, boolean check){
            System.out.printf("name: %s check: %b\n", name, check);
        }
        private static int getValue(int name, String other){return 0;}

        public boolean isOne(){ return false;}
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Class<?> aux = Auto.class;

        for(Method m: aux.getMethods()){
            if(Modifier.isStatic(m.getModifiers())){
                System.out.print(m.getName() + ": ");
                for(Parameter p: m.getParameters()){
                    System.out.print(p.getName() + "; ");
                }
                System.out.println();
                Object[] values = new Object[] {10, false};
                m.invoke(null, values);
            }
        }
    }

}
