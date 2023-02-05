package jh.projects.cliparser.cliApp.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// TODO: this thing is broken just fix it :)
public class Table implements CliTable{

    private List<String> cells;
    private String[] delimiters;
    private int[] max_sizes;
    private int counter;
    private final int cols;

    public Table(String[] headers){
        this.cols = headers.length;
        this.cells = new ArrayList<>();
        this.delimiters = new String[cols];
        this.max_sizes = new int[cols];

        this.add((Object[]) headers);
    }

    @Override
    public int rows() {
        return (int) Math.ceil((double) cells.size() / cols);
    }

    @Override
    public int cols() {
        return cols;
    }

    @Override
    public void add(Object ...values) {
        for(Object value : values){
            String str_value = value.toString();
            int col = counter % cols;
            max_sizes[col] = Math.max(max_sizes[col], str_value.length());
            // problems :)
            while(counter >= cells.size()) cells.add("");
            cells.set(counter++, str_value); // by now :)
        }
    }

    @Override
    public void set(int col, int row, String value) {
        counter = row + col * cols;
        this.add(value);
    }

    @Override
    public void setDelimiter(int idx, String del ) {
        if(idx < 0 || idx >= delimiters.length) throw new IndexOutOfBoundsException();
        delimiters[idx] = del;
    }

    @Override
    public void setDelimiter(String del) {
        for(int i = 0; i < cols; ++i) setDelimiter(i, del);
    }

    @Override
    public void print() {

        Iterator<String> it = cells.iterator();
        for(int r = 0; r < this.rows(); ++r){
            for(int c = 0; c < this.cols() && it.hasNext(); ++c){
                String value = it.next();
                 if(delimiters[c] != null) {
                     String del = delimiters[c];
                     if (r == 0) // I am printing the headers
                         del = String.format("%" + del.length() + "s", " ");
                     System.out.print(del);
                 }
                System.out.printf("%-" + max_sizes[c] + "s", value);
            }
            if(r == 0) System.out.println(); // :)
            System.out.println();
        }
    }

}
