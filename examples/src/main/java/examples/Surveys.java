package examples;

import jh.projects.cliparser.cliApp.annotations.CliAppCommand;
import jh.projects.cliparser.cliApp.api.CliAPI;
import jh.projects.cliparser.cliApp.api.form.CliForm;
import jh.projects.cliparser.cliApp.api.form.CliFormValue;
import jh.projects.cliparser.cliApp.api.table.CliTable;
import static jh.projects.cliparser.parser.DataType.*;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class Surveys {
    private record Survey(int id, String answer, String country, int age){};

    private final SortedMap<Integer, Survey> answers;
    private int curr_id;
    public Surveys(){
        answers = new TreeMap<>();
        curr_id = 0;
    }


    @CliAppCommand
    public void register(CliAPI api){
        CliForm form = api.createForm();
        CliFormValue[] values = form
                .addField("Age", INTEGER)
                .addField("Country", STRING)
                .addField("Answer", STRING)
                .run();
        if(values == null)
            System.out.printf("Invalid value for '%s'.\n", form.getErrorCause().name());
        else {
            int sv_id = curr_id++;
            answers.put(sv_id, new Survey(
                    sv_id, values[2].toString(),
                    values[1].toString(), values[0].toInt()
            ));
        }

    }

    @CliAppCommand
    public void unregister(int id){
        Survey sv = answers.remove(id);
        if(sv == null)
            System.out.printf("No survey with id: %d\n", id);
        else
            System.out.printf("Survey %d removed\n", id);
    }

    @CliAppCommand
    public void list(CliAPI api){
        Iterator<Survey> it = answers.values().iterator();
        if(!it.hasNext())
            System.out.println("No survey on store");
        else{
            CliTable table = api.createTable(new String[]{"ID", "ANSWER", "AGE", "COUNTRY"});
            while(it.hasNext()){
                Survey sv = it.next();
                table.add(sv.id(), sv.answer(), sv.age(), sv.country());
            }
            table.print();
        }

    }

}
