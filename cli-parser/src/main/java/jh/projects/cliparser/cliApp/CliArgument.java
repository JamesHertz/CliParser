package jh.projects.cliparser.cliApp;

import jh.projects.cliparser.parser.Argument;
import jh.projects.cliparser.parser.DataType;
import jh.projects.cliparser.parser.exeptions.BadArgumentException;

public record CliArgument(String name, String description, DataType type) implements Argument {
        @Override
        public Object parse(String value) throws BadArgumentException {
           try{
               return type.parse(value);
           } catch (BadArgumentException ex) {
               ex.setArgument(this);
               throw ex;
           }
        }
}

