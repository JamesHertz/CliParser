package jh.projects.cliparser.cliApp.api;

public interface CliTable {

    int rows();
    int cols();
    void add(Object ...values);
    void set(int col, int row, String value);
    void setDelimiter(int idx, String del);
    void setDelimiter(String del);
    void print();
    // todo: think about alignment :)
}
