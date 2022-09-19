package jh.cliApp;

public interface CliApp<T> {
    void addCommand(Command<T> command);
    void run();
    // think about void exit()
}
