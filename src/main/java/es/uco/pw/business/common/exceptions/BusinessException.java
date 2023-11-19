package es.uco.pw.business.common.exceptions;

public class BusinessException extends Exception {
    public BusinessException(String message){
        super(message);
    }

    public static void handleException(Exception e) throws Exception {
        throw new Exception(e.getMessage());
    }
}
