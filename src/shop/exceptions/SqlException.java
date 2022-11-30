package shop.exceptions;

import java.sql.SQLException;

public class SqlException extends RuntimeException {
    public SqlException(String message){
        super (message);
    }
}
