package es.uco.pw.data.common;

public class DataException {
    public static void handleException(String message) throws Exception {
        throw new Exception(message);
    }
}
