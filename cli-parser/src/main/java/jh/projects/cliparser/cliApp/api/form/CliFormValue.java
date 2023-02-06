package jh.projects.cliparser.cliApp.api.form;

public class CliFormValue {
    private final Object value;

    CliFormValue(Object value){
        this.value = value;
    }

    public int toInt(){
        return (int) value;
    }

    public long toLong(){
        return (long) value;
    }

    public float toFloat(){
        return (float) value;
    }

    public double toDouble(){
        return (double) value;
    }

    @Override
    public String toString(){
        return (String) value;
    }
}
