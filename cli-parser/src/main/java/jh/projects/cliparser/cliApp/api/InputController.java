package jh.projects.cliparser.cliApp.api;

import java.util.Scanner;

public abstract class InputController {
    protected final Scanner scanner;
    public InputController(Scanner scanner){
        this.scanner = scanner;
    }
}
