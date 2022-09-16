package jh.parser.exeptions;

public class UnmatchedQuoteException extends RuntimeException{
    public UnmatchedQuoteException(String message){
        super(message);
    }
}
