package jh.projects.cliparser.cliApp;

import jh.projects.cliparser.parser.Argument;

import java.util.Iterator;

public class Utils {
    public static String getUsageMessage(CommandInfo cmd){
        StringBuilder builder = new StringBuilder("usage: ");
        Iterator<Argument> args = cmd.argsFormat().getArguments();
        if(!args.hasNext())
            builder.append("(none)");
        else{
            while(args.hasNext()){
                Argument arg = args.next();
                builder.append("<")
                        .append(arg.name())
                        .append("> ");
            }
            // TODO: print the info meessage :)
        }
        return builder.toString();
    }
}
