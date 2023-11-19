package es.uco.pw.data.common;

import java.sql.SQLException;

public class DataException extends SQLException {
    public DataException(String message){
        super(message);
    }
}
