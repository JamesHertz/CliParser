import examples.Surveys;
import jh.projects.cliparser.cliApp.CliApp;
import jh.projects.cliparser.cliApp.SimpleCliApp;

import jh.projects.cliparser.cliApp.api.CliAPI;
import jh.projects.cliparser.cliApp.listeners.CliExitListener;

import java.io.IOException;
import examples.PeopleManager;
import jh.projects.cliparser.cliApp.listeners.CliRunListener;


// TODO: make the main an app to multiplex between the commands :)
public class Main{
    // you can start digging here :) https://docs.gradle.org/current/userguide/writing_build_scripts.html#sec:the_gradle_build_language
    public static void main(String[] args) throws IOException {
        //CliApp app = new SimpleCliApp(new Auto());
        // CliApp app = new SimpleCliApp(new PeopleManager());
        CliApp app = new SimpleCliApp(new Surveys());
        app.run();
        //System.out.println("Hello, world!");
    }

}
