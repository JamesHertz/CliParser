package jh.cmdProgram;

public interface SimpleCmdProgram<T> {
    void addCommand(Command<T> command);
    void run();
}
