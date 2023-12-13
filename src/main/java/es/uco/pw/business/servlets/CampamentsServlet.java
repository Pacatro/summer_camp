package es.uco.pw.business.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Random;
import java.time.LocalDate;


public class CampamentsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        LocalDate startDate = new LocalDate(0, 0, 0);
        startDate.parse(request.getParameter("start-date"));
        int campId = new Random().nextInt();

    }
}
