package jh.projects.cliparser.cliApp;

import jh.projects.cliparser.cliApp.annotations.CliAppArg;
import jh.projects.cliparser.cliApp.annotations.CliAppCommand;

import java.util.Iterator;

class DefaultCommands {

    @CliAppCommand(desc = "prints the a given command information")
    public static void info(
            CliAPI api,
            @CliAppArg(
                    key ="command-name",
                    desc="the command name"
            )
            String command){
        System.out.println("TODO!!!");
    }

    @CliAppCommand(desc = "lists all commands")
    public static void help(CliAPI api){
        /*
            CliTable table = api.createTable(3) // number of colors
            table.setHeaders("Commands", "Description");
            // table.setSeparator("-");
            Iterator it = api.getAllCommands();
            while(it.hasNext()){
                CommandInfo info = it.next();
                table.add(info.name(), "-", info.description())
            }
            table.print()
         */

        Iterator<CommandInfo> info = api.getAllCommands();

        System.out.println("Commands  Description");
        while(info.hasNext()){
            CommandInfo cmd = info.next();
            String desc = cmd.getDescription();
            System.out.printf("%s - %s\n", cmd.getName(), desc.isBlank() ? "(none)" : desc);
        }

    }

    @CliAppCommand(desc = "exits the program")
    public static void exit(CliAPI api){
        api.exit();
    }

}
