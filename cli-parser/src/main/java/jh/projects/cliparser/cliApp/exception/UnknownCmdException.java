package jh.projects.cliparser.cliApp.exception;

public class UnknownCmdException extends CliAppException{
    private static final String MSG = "Unknown command: %s";
    private final String commandName;
    public UnknownCmdException(String commandName){
        super(String.format(MSG, commandName));
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
