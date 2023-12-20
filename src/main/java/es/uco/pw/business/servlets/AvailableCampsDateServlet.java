package es.uco.pw.business.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.display.javabeans.CustomerBean;

import java.io.IOException;
import java.time.LocalDate;
import java.io.FileNotFoundException;
import java.util.Properties;

@WebServlet(name = "availableCampsServlet", urlPatterns = "/availableCamps")
public class AvailableCampsDateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        HttpSession session = req.getSession();

        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

        if(customerBean == null) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: The user is not register or is not an admin");
            return;
        }
        
        if (req.getParameter("start-date") == null ||
            req.getParameter("end-date") == null ||
            req.getParameter("level") == null ||
            req.getParameter("max-assistants") == null) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request: Missing parameters");
            return;
        }
        
        LocalDate starDate = LocalDate.parse(req.getParameter("start-date"));
        LocalDate endDate = LocalDate.parse(req.getParameter("end-date"));

        try {
            Properties configProperties = new Properties();
            Properties sqlProperties = new Properties();
            sqlProperties.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            configProperties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));
    
            CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);
            campamentsManager.getCampsByDateInterval(starDate, endDate);
            
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request: " + e.getMessage());
        }
    }
}