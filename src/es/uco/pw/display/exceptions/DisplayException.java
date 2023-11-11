package es.uco.pw.display.exceptions;

public class DisplayException {
    public static void handleException(Exception e){
        System.err.println("Error: " + e.getMessage());
    }
}
