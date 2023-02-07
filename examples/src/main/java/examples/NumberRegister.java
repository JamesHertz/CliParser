package examples;

import jh.projects.cliparser.cliApp.annotations.CliAppCommand;
import jh.projects.cliparser.cliApp.api.CliAPI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NumberRegister {

        private List<Integer> values;
        public NumberRegister(){
           values = new ArrayList<>();
        }

        // exception: NumberFormatException
        @CliAppCommand(desc = "registers a value")
        public void add_name( byte value){
           values.add((int) value);
        }

        @CliAppCommand(desc = "exits the app")
        public void quit(CliAPI api){
            System.out.println("quitting");
            api.exit();
        }

        @CliAppCommand( desc = "list all the registered values")
        public void list(){
            Iterator<Integer> it = values.iterator();
            if(!it.hasNext())
                System.out.println("No values in stored :(");
            else{
                System.out.println("the added values are:");
                while(it.hasNext())
                    System.out.println("-> " + it.next());
            }
        }
        // @Command(name="another command")
        // public void hi_there(int age){
        //     System.out.printf("in: hi_there -> I got %d\n", age);
        // }
        // @Command(name="mycommand")
        // public static int getValue(int name, String other){
        //     return 0;
        // }
        /*
            TODO:
                X generate name from the method name
                -> add special annotation to class
                -> add default methods
                -> errors when methods are overridden, etc
         */
        public static void spoilSomething(){}
        public static void saySomething(){}

        public boolean isOne(){ return false;}
}

