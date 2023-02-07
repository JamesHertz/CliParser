package jh.projects.cliparser.cliApp.api;

import jh.projects.cliparser.cliApp.CliAppState;
import jh.projects.cliparser.cliApp.api.form.CliForm;
import jh.projects.cliparser.cliApp.api.form.Form;
import jh.projects.cliparser.cliApp.api.table.CliTable;
import jh.projects.cliparser.cliApp.api.table.Table;

import java.util.Scanner;

public abstract class BaseAPI implements CliAPI{
    // should I have Input and OutputStream ... stored??
    /*
    private final InputStream input;
    private final PrintWriter output;
     */
    private final Scanner scanner;

    public BaseAPI(){
        this.scanner = new Scanner(System.in);
        /* this.input = input; this.output= output;*/
    }

    @Override
    public Scanner getInputScanner() {
        return scanner;
    }

    @Override
    public String prompt() {
        return prompt(null);
    }

    @Override
    public String prompt(String msg) {
        if(msg != null) System.out.print(msg);
        return scanner.nextLine().trim();
    }

    @Override
    public CliTable createTable(String[] headers) {
        return new Table(headers);
    }

    @Override
    public CliTable createTable(int cols) {
        return new Table(cols);
    }

    @Override
    public CliForm createForm() {
        return new Form(scanner);
    }
}
