package jh.parser;



import java.util.Iterator;
import java.util.List;

public class ParserFormat implements Format {

   private final List<DataType> format;

    public ParserFormat(DataType ...types){
        this.format = List.of(types);
    }

    @Override
    public int numOfArgs() {
        return format.size();
    }

    @Override
    public DataType getType(int idx) {
        return format.get(idx);
    }

    @Override
    public Iterator<DataType> getFormat() {
        return format.iterator();
    }

}
