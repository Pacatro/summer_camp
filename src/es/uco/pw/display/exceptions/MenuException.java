package es.uco.pw.display.exceptions;

public class MenuException {
    public static void handleException(Exception e){
        System.err.println("Error: " + e.getMessage());
    }
}
