package jh.parser;

import java.util.LinkedList;
import java.util.List;

class LineParser {
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

    public static String[] parseLine(String line) {
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
            throw new UnsupportedOperationException("unmatched quote: " + line.substring(0, track+1));
        else if(inWord) args.add(line.substring(track));
        // raise exception here
        return args.toArray(new String[args.size()]);

    }
}
