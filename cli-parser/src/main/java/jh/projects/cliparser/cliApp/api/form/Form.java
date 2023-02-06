package jh.projects.cliparser.cliApp.api.form;

import jh.projects.cliparser.cliApp.CliArgument;
import jh.projects.cliparser.cliApp.api.table.CliTable;
import jh.projects.cliparser.cliApp.api.table.Table;
import jh.projects.cliparser.parser.Argument;
import jh.projects.cliparser.parser.DataType;
import jh.projects.cliparser.parser.FmtArgument;
import jh.projects.cliparser.parser.exeptions.BadArgumentException;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Form implements CliForm{

    private final List<FmtArgument> fields;
    private BadArgumentException error;
    private CliTable prompts;

    // InputStream stream
    public Form(){
       fields = new LinkedList<>();
       prompts = new Table(2);
       error = null;
    }

    @Override
    public CliForm addField(String name, DataType type) {
        return addField(name, type, null);
    }

    @Override
    public CliForm addField(String name, DataType type, String description) {
        fields.add(new CliArgument(name, description, type));
        prompts.add(name, ": ");
        return this;
    }

    @Override
    public CliFormValue[] run() {
        if(fields.isEmpty()) throw new RuntimeException("Empty form!!"); // todo: an exception
        Scanner in = new Scanner(System.in);// TODO: change this to accept the CliApp input-stream.

        int idx = 0;
        CliFormValue[] values = new CliFormValue[fields.size()];
        String[] args = new String[fields.size()];

        // TODO: use table to do this
        Iterator<FmtArgument> it = fields.iterator();
        int row = 0;
        while(it.hasNext()){
            // TODO: change this to CliApp OutputStream
            FmtArgument arg = it.next();
            // TODO: think about default values
            prompts.printRow(row++);
            args[idx++] = in.nextLine().trim();
        }
        // another loop to parse this time :)
        it = fields.iterator();
        idx = 0;

        while(it.hasNext()){
            try{
                values[idx] = new CliFormValue(
                   it.next().parse(args[idx++])
                );
            }catch (BadArgumentException e){
                error = e; return null;
            }
        }
        return values;
    }

    @Override
    public Argument getErrorCause() {
        return error.getArgument();
    }

    @Override
    public void printError() {
        if(error != null){
            Argument arg = error.getArgument();
            System.out.printf("Error: invalid value for form field '%s': %s\n", arg.name(), error.getProvided());
            System.out.println("Expected " + error.getExpectedMessage());
        }
    }
}
