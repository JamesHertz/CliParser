package jh.projects.cliparser.cliApp.api.table;

public interface CliTable {

    int rows();
    int cols();
    void add(Object ...values);
    void set(int col, int row, String value);
    void setDelimiter(int idx, String del);
    void setDelimiter(String del);
    void print();
    void printRow(int row);
    // void printCol()

    // void print(int row, int col);
    // todo: think about alignment :)
    // todo: add methods that allow to print specific cell
    // todo: add ways to take out the headers
}
