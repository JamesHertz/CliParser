package jh.projects.cliparser.cliApp;

import jh.projects.cliparser.cliApp.annotations.CliAppArg;
import jh.projects.cliparser.cliApp.annotations.CliAppCommand;
import jh.projects.cliparser.parser.Argument;

import java.util.Iterator;

class DefaultCommands {

    @CliAppCommand(desc = "prints the a given command information")
    public static void info(
            CliAPI api,
            @CliAppArg(
                    value="command-name",
                    desc="the command name"
            )
            String command){
        System.out.println("TODO!!!");
    }

    @CliAppCommand(desc = "lists all commands")
    public static void help(CliAPI api){
        // use a table kakaka
        /*
            CliTable table = api.createTable(2) // number of colors
            table.setHeaders("Commands", "Description");
            table.setSeparator("-");
            Iterator it = api.getAllCommands();
            while(it.hasNext()){
                CommandInfo info = it.next();
                table.add(info.name(), info.description())
            }
            table.print()
         */

        Iterator<CommandInfo> info = api.getAllCommands();

        System.out.println("Commands  Description");
        while(info.hasNext()){
            CommandInfo cmd = info.next();
            String desc = cmd.description();
            System.out.printf("%s - %s\n", cmd.commandName(), desc.isBlank() ? "(node)" : desc);
        }

    }

    @CliAppCommand(desc = "exits the program")
    public static void exit(CliAPI api){
        api.exit();
    }

}
