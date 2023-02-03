import jh.projects.cliparser.cliApp.CliApp;
import jh.projects.cliparser.cliApp.SimpleCliApp;

import java.io.IOException;
import examples.PeopleManager;


public class Main {
    // TODO: find a way to place the examples aside
    // you can start digging here :) https://docs.gradle.org/current/userguide/writing_build_scripts.html#sec:the_gradle_build_language
    public static void main(String[] args) throws IOException {
        //CliApp app = new SimpleCliApp(new Auto());
        CliApp app = new SimpleCliApp(new PeopleManager());
        app.run();
        //System.out.println("Hello, world!");
    }

}
