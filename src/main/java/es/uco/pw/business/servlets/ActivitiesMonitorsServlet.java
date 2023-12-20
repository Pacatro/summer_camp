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
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("/summer_camp/index.jsp");
            return;
        }
        
        if(req.getParameter("act-id").equals("") || req.getParameter("mon-id").equals("")) {
            res.sendRedirect("/summer_camp/mvc/view/forms/parcialInscriptionView.jsp");
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
            res.sendRedirect("/summer_camp/mvc/view/messages/monsActsAssociated.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.getSession().setAttribute("message", e.getMessage());
            res.sendRedirect("/summer_camp/mvc/view/errors/error.jsp");
        }
    }
}
