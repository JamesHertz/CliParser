package examples;

import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;

import jh.projects.cliparser.cliApp.annotations.CliAppCommand;
import jh.projects.cliparser.cliApp.api.CliAPI;
import jh.projects.cliparser.cliApp.api.table.CliTable;

import static java.lang.String.format;
public class PeopleManager {
    private record Person(String name, int age){}
    private final Map<String, Person> people;
    public PeopleManager(){
        people = new TreeMap<>();
    }

    @CliAppCommand( key = "add", desc = "adds a new person")
    public void addPerson(String name, int age){
        var person = people.get(name);
        if(age <= 0)
            System.out.printf("Error: invalid age: %d\n", age);
        else if(person != null)
            System.out.printf("Error: person '%s' already exists\n", name);
        else if(age >= 200)
            System.out.println("Sorry we don't accept person older than 2 centuries ;)");
        else{
            people.put(name, new Person(name, age));
            System.out.printf("Person '%s' added with success!!\n", name);
        }
    }

    @CliAppCommand( desc = "list all the person registered")
    public void listPeople(CliAPI api){
        Iterator<Person> it = people.values().iterator();
        if(!it.hasNext())
            System.out.println("No person added!!");
        else {
            CliTable table = api.createTable(new String[]{"NAME", "AGE"});
            while(it.hasNext()){
                Person p = it.next();
                // using format by now ;)
                table.add(p.name(), format("%3d", p.age()));
            }
            table.print();
        }
    }

    @CliAppCommand(desc = "deletes a person given the name")
    public void deletePerson(String name){
        var err = people.remove(name);
        if(err == null)
            System.out.printf("No such person: '%s'\n", name);
        else
            System.out.printf("Person '%s' removed with success\n", err.name);
    }

    @CliAppCommand(key = "change", desc = "changes a given person age")
    public void changeAge(String name, int new_age){
        var person = people.get(name);
        if(person == null)
            System.out.printf("No such person: '%s'\n", name);
        else {
            people.put(name, new Person(name, new_age));
            System.out.printf("Person '%s' age changed from %d to %d.\n", name, person.age(), new_age);
        }
    }

}
