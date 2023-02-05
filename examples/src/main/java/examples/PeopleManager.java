package examples;

import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import jh.projects.cliparser.cliApp.CliAPI;
import jh.projects.cliparser.cliApp.annotations.CliAppCommand;

public class PeopleManager {
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

    @CliAppCommand(key = "change")
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
