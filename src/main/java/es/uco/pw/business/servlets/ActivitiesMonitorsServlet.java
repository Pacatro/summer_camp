package es.uco.pw.business.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.uco.pw.business.common.userType.*;
import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.display.javabeans.CustomerBean;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * Servlet implementation for associating monitors with activities.
 * This servlet is mapped to the URL pattern "/activityMonitor".
 *
 * The expected parameters for the POST request are "act-id" and "mon-id".
 * These parameters are used to identify the activity and monitor that should be associated.
 *
 * The authentication is performed using the "customerBean" attribute stored in the session,
 * ensuring that the user is an admin.
 *
 */
@WebServlet(name = "activitiesMonitorsServlet", urlPatterns = "/activityMonitor")
public class ActivitiesMonitorsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        HttpSession session = req.getSession();

        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

        if(customerBean == null || customerBean.getType() == UserType.ASSISTANT) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: The user is not an admin");
            return;
        }
        
        if(req.getParameter("act-id") == null || req.getParameter("mon-id") == null) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request: Missing parameters");
            return;
        }
        
        String actId = req.getParameter("act-id");
        int monId = Integer.parseInt(req.getParameter("mon-id"));
        
        try {
            Properties configProperties = new Properties();
            Properties sqlProperties = new Properties();
            sqlProperties.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            configProperties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));
    
            CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);
            campamentsManager.associateMonitorsToActivities(monId, actId);
            
            res.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Server Error: " + e.getMessage());
        }
    }
}
