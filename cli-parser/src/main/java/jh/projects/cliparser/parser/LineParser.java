package jh.projects.cliparser.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LineParser {
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
     */
    private static boolean isQuote(char c) {
        return c == '\'' || c == '"';
    }

    public static String[] parseLine(InputStream stream) throws IOException {
        List<String> args = new LinkedList<>();
        Scanner in = new Scanner(stream);
        StringBuilder word = new StringBuilder();
        char ch, quote = 0;
        int next;

        while((next = stream.read()) != -1 && (next != '\n' || quote != 0)){
            ch = (char) next;

            // TODO: if you ever care about escapes just touch here :)
            boolean white_sp = Character.isWhitespace(ch);
            if(quote == 0) {
                if(isQuote(ch)) quote = ch;
                else if(!white_sp) word.append(ch);

                if(!word.isEmpty() && ( white_sp || quote != 0) ){
                    args.add(word.toString());
                    word.setLength(0);
                }
            }else {
                // by now :)
                if(ch != quote) word.append( white_sp ? ' ' : ch);
                else {
                    args.add(word.toString());
                    word.setLength(0);
                    quote = 0;
                }
            }
        }

        if(!word.isEmpty()) args.add(word.toString());
        return args.toArray(new String[0]);
    }
}
