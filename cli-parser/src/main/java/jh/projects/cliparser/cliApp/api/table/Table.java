package jh.projects.cliparser.cliApp.api.table;

import java.util.ArrayList;
import java.util.List;
import static java.lang.String.format;

// TODO: this thing is broken just fix it :)
public class Table implements CliTable {

    private static final String DEFAULT_DELIMITER = " ";
    private final List<String> cells;
    private final int cols;
    private final String[] delimiters;
    private final int[] max_sizes;
    private int counter;
    private boolean includesHeader;

    public Table(String[] headers){
        this(headers.length);
        // todo: remove the flag and make our life easier
        this.includesHeader = true;
        this.add((Object[]) headers);
    }

    public Table(int cols){
        this.cols = cols;
        this.cells = new ArrayList<>();
        this.delimiters = new String[cols]; // TODO: allow before and after each row
        this.max_sizes = new int[cols];
        this.includesHeader = false;
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
            String str_value = "" + value;
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
        for(int r = 0, idx = 0; r < this.rows(); ++r){
            printRow(r);

            if(this.isHeader(r)) System.out.println(); // :)
            System.out.println();
        }
    }

    private String getDelimiter(int row, int col){
         String del = delimiters[col] == null ? DEFAULT_DELIMITER : delimiters[col];
         if (this.isHeader(row)) return del;  // it's not a header :)
         return String.format("%" + del.length() + "s", " ");
    }

    @Override
    public void printRow(int row) {
        StringBuilder builder = new StringBuilder();
        for(int col = 0; col < this.cols(); ++col){
            builder.append(getDelimiter(row, col));
            String value = getCell(row, col);
            builder.append(format("%-" + max_sizes[col] + "s", value));
        }
        System.out.print(builder.toString());
    }

    private String getCell(int row, int col){
        try{
            return cells.get(row * cols + col);
        }catch (IndexOutOfBoundsException e){
            return " ";
        }
    }
    private boolean isHeader(int row){
        return row == 0 && includesHeader;
    }

}
