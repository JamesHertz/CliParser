package jh.projects.cliparser.cliApp;

import jh.projects.cliparser.cliApp.annotations.CliAppArg;
import jh.projects.cliparser.cliApp.annotations.CliAppCommand;
import jh.projects.cliparser.cliApp.api.CliAPI;
import jh.projects.cliparser.cliApp.api.CliTable;

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
        CliTable table = api.createTable(new String[] {"Commands", "Description"});
        table.setDelimiter(1, " - ");
        Iterator<CommandInfo> it = api.getAllCommands();
        while(it.hasNext()){
            CommandInfo info = it.next();
            String desc = info.getDescription();
            table.add(info.getName(), desc.isEmpty() ? "----" : desc);
        }
        table.print();
    }

    @CliAppCommand(desc = "exits the program")
    public static void exit(CliAPI api){
        api.exit();
    }

}
