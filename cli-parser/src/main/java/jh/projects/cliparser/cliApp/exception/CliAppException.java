package jh.projects.cliparser.cliApp.exception;

import jh.projects.cliparser.cliApp.CommandInfo;

public /*abstract*/ class CliAppException extends  Exception{
    private CommandInfo cmd;
    public CliAppException(){}

    public CliAppException(String message){
        super(message);
    }

    public CliAppException setCommand(CommandInfo cmd){
        this.cmd = cmd;
        return this;
    }

    public CommandInfo getRelatedCommand(){
        return cmd;
    }
}
