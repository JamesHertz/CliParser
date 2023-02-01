import jh.projects.cliApp.CliAPI;
import jh.projects.cliApp.CliApp;
import jh.projects.cliApp.SimpleCliApp;
import jh.projects.cliApp.annotations.CliAppCommand;
import java.io.IOException;
import java.util.*;

public class Main {

    static class Auto{

        List<Integer> values;
        public Auto(){
           values = new ArrayList<>();
        }

        // exception: NumberFormatException
        @CliAppCommand
        public void add_name( byte value){
           values.add((int) value);
        }

        @CliAppCommand
        public void exit(CliAPI api){
            System.out.println("quitting");
            api.exit();
        }

        @CliAppCommand
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

    static class PeopleManager {
        private record Person(String name, int age){}
        private final Map<String, Person> people;
        public PeopleManager(){
           people = new TreeMap<>();
        }

        @CliAppCommand
        public void addPerson(String name, int age){
            var person = people.get(name);
            if(age <= 0){
                System.out.printf("Error: invalid age: %d\n", age);
            }else if(person != null) {
                System.out.printf("Error: person '%s' already exists\n", name);
            }else{
                people.put(name, new Person(name, age));
                System.out.printf("Person '%s' added with success!!\n", name);
            }
        }

        @CliAppCommand
        public void listPeople(){
            Iterator<Person> it = people.values().iterator();
            if(!it.hasNext())
                System.out.println("No person added!!");
            else {
                while(it.hasNext()){
                    Person p = it.next();
                    System.out.printf("name: %s; age: %d\n", p.name(), p.age());
                }
            }
        }

        @CliAppCommand
        public void deletePerson(String name){
            var err = people.remove(name);
            if(err == null)
                System.out.printf("No such person: '%s'\n", name);
            else
                System.out.printf("Person '%s' removed with success\n", err.name);
        }

        @CliAppCommand("change")
        public void changeAge(String name, int new_age){
            var person = people.get(name);
            if(person == null)
                System.out.printf("No such person: '%s'\n", name);
            else {
                people.put(name, new Person(name, new_age));
                System.out.printf("Person '%s' age changed from %d to %d.\n", name, person.age(), new_age);
            }
        }

        @CliAppCommand
        public void exit(CliAPI api){
            System.out.println("quitting");
            api.exit();
        }
    }

    public static void main(String[] args) throws IOException {
        //CliApp app = new SimpleCliApp(new Auto());
        CliApp app = new SimpleCliApp(new PeopleManager());
        app.run();
    }

}
