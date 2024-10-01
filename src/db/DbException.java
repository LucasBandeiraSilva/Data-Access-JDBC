package db;

public class DbException extends  RuntimeException{
    private  String msg;


    public DbException( String message ) {
        super(message);
    }
}
