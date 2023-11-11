package es.uco.pw.business.common.exceptions;

public class BusinessException {
    public static void handleException(Exception e) throws Exception {
        throw new Exception(e.getMessage());
    }
}
