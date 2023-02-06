package jh.projects.cliparser.cliApp.api.form;

import jh.projects.cliparser.parser.Argument;
import jh.projects.cliparser.parser.DataType;

public interface CliForm {
    CliForm addField(String name, DataType type);
    CliForm addField(String name, DataType type, String description);
    // Object[] getValues()
    CliFormValue[] run();
    Argument getErrorCause();

    /*
        CliForm addField(String name, DataType type);
        CliForm addField(String name, DataType type, String description);
        CliFormValue {
            toLong();
            toInt();
            toString();
            toFloat();
            toDouble();
        }

     */
}
