import jh.cliApp.CliApp;
import jh.cliApp.SimpleCliApp;
import jh.cliApp.annotations.Command;
import jh.parser.LineParser;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//import static jh.parser.LineParser.parseLine;

public class Main {

    static class Auto{

        List<String> names;
        public Auto(){
           names = new ArrayList<>();
        }

        @Command(name="add") // if name non specified, generate it :)
        public void add(String name){
           names.add(name);
        }

        @Command(name="list") // if name non specified, generate it :)
        public void list(){
            Iterator<String> it = names.iterator();
            if(!it.hasNext())
                System.out.println("No names in stored :(");
            else{
                System.out.println("the added names are:");
                while(it.hasNext())
                    System.out.println("-> " + it.next());
            }
        }
        //@Command(name="another command")
        //public void hi_there(int age){
        //    System.out.printf("in: hi_there -> I got %d\n", age);
        //}
        //@Command(name="mycommand")
        //public static int getValue(int name, String other){
        //    return 0;
        //}
        public static void spoilSomething(){}
        //@Command(name="say")
        public static void saySomething(){}

        public boolean isOne(){ return false;}
    }

    public static void main(String[] args) throws IOException {
        CliApp app = new SimpleCliApp(new Auto());
        app.run();

        //for(;;){
        //    System.out.print(">> ");
        //    System.out.println(
        //            Arrays.toString(LineParser.parseLine(System.in))
        //    );
        //}
    }

}
