import jh.cliApp.CliApp;
import jh.cliApp.CommandContext;
import jh.cliApp.SimpleCliApp;
import jh.cliApp.annotations.Command;

//import static jh.parser.LineParser.parseLine;

public class Main {

    static class Auto{
        @Command(name="register")
        public static void doSomething(CommandContext<Integer> ctx, float temperature, String name){
            System.out.println("name = " + name);
            System.out.printf("Temperature %.2f registered", temperature);
        }

        @Command(name="unregister")
        public static void doAnotherThing(CommandContext<Integer> ctx, float temperature){
            System.out.printf("Temperature %.2f unregistered", temperature);
        }
        public static void spoilSomething(){}
        //@Command(name="say")
        public static void saySomething(){}
        private static int getValue(int name, String other){return 0;}

        public boolean isOne(){ return false;}
    }

    public static void main(String[] args) {
        CliApp<Integer> app = new SimpleCliApp<>(Auto.class);
        app.run();
    }

}
