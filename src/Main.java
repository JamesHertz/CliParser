import jh.cliApp.CliApp;
import jh.cliApp.SimpleCliApp;
import jh.cliApp.annotations.Command;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    static class Auto{

        List<Integer> values;
        public Auto(){
           values = new ArrayList<>();
        }

        // exception: NumberFormatException
        @Command(name="add") // if name non specified, generate it :)
        public void add(byte value){
           values.add((int) value);
        }

        @Command(name="list") // if name non specified, generate it :)
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
        //@Command(name="another command")
        //public void hi_there(int age){
        //    System.out.printf("in: hi_there -> I got %d\n", age);
        //}
        //@Command(name="mycommand")
        //public static int getValue(int name, String other){
        //    return 0;
        //}
        /*
            TODO:
                -> generate name from the method name
                -> add special annotation to class
                -> add default methods
                -> errors when methods are overridden, etc
         */
        public static void spoilSomething(){}
        //@Command(name="say")
        public static void saySomething(){}

        public boolean isOne(){ return false;}
    }

    public static void main(String[] args) throws IOException {
        //for(Class<?> key : messages.keySet()){
        //    System.out.printf("type: %s\nmsg: %s\n", key, messages.get(key));
        //    System.out.println("-------------------------------------");
        //}
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
