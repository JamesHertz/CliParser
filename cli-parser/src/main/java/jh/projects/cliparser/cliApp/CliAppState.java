package jh.projects.cliparser.cliApp;

public enum CliAppState {
    NEW, RUNNING, FINISHED;
    public CliAppState next(){
        return switch (this) {
            case NEW -> RUNNING;
            case RUNNING -> FINISHED;
            default -> null;
        };
    }
}
