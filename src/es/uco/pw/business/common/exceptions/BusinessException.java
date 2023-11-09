package es.uco.pw.business.common.exceptions;

public class BusinessException extends Exception {
    public static void handleException(Exception e){
        System.err.println("Error: " + e.getMessage());
    }
}
