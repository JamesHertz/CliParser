import jh.cliApp.CliApp;
import jh.cliApp.SimpleCliApp;
import jh.cliApp.annotations.Command;

//import static jh.parser.LineParser.parseLine;

public class Main {

    static class Auto{
        @Command(name="do")
        public static void doSomething(int name, boolean check){
            System.out.printf("name: %s check: %b\n", name, check);
        }

        public static void spoilSomething(){}
        //@Command(name="say")
        public static void saySomething(){}
        private static int getValue(int name, String other){return 0;}

        public boolean isOne(){ return false;}
    }

    public static void main(String[] args) {
        CliApp<String> app = new SimpleCliApp<>(Auto.class);
    }

}
