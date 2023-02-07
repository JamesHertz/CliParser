import examples.NumberRegister;
import examples.Surveys;
import jh.projects.cliparser.cliApp.CliApp;
import jh.projects.cliparser.cliApp.SimpleCliApp;

import jh.projects.cliparser.cliApp.api.CliAPI;
import jh.projects.cliparser.cliApp.api.InputController;
import jh.projects.cliparser.cliApp.api.table.CliTable;

import java.io.IOException;
import java.util.*;

import examples.PeopleManager;
import jh.projects.cliparser.cliApp.listeners.CliRunListener;

import static java.lang.String.format;

public class Main implements CliRunListener{

    private static String getId(String name){
       return name.replaceAll("([a-z])([A-Z])", "$1 $2")
                   .replaceAll("\s", "-")
                   .toLowerCase();
    }

    private final List<Object> examples = List.of(new NumberRegister(), new Surveys(), new PeopleManager());
    @Override
    public void onRun(CliAPI api) {
        System.out.println("Welcome to cli-examples\n");
        System.out.println("Available examples:");

        CliTable table = api.createTable(2);
        int count = 1;
        for(Iterator<Object> it = examples.iterator(); it.hasNext();)
            System.out.printf("%2d. %s\n", count++, getId(it.next().getClass().getSimpleName()));

        table.print();

        String res = api.prompt(format("\nChoose an example or 0 to exit [0..%d]: ", examples.size()));
        
        try{
            int option = res.isEmpty() ? 0 : Integer.parseInt(res);
            if(option != 0){
                Object obj = examples.get(option - 1);
                System.out.println("\nRunning: " + getId(obj.getClass().getSimpleName()));
                CliApp myApp = new SimpleCliApp(obj);
                myApp.run();
            }
        }catch (Exception ex){
             System.out.println("Invalid example: " + res);
        }
        api.exit();
    }

    public static void main(String[] args) throws IOException {
        //CliApp app = new SimpleCliApp(new Auto());
        // CliApp app = new SimpleCliApp(new PeopleManager());
        // CliApp app = new SimpleCliApp(new Surveys());
        CliApp app = new SimpleCliApp(new Main());
        app.run();
        //System.out.println("Hello, world!");
    }
}
