package es.uco.pw.business.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.uco.pw.business.common.userType.*;
import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.display.javabeans.CustomerBean;

import java.io.IOException; 
import java.io.FileNotFoundException;
import java.util.Properties;

@WebServlet(name = "activitiesCampamentsServlet", urlPatterns = "/activityCampament")
public class ActivitiesCampamentsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        HttpSession session = req.getSession();

        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

        if(customerBean == null || customerBean.getType() == UserType.ASSISTANT) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: The user is not an admin");
            return;
        }
        
        if(req.getParameter("act-id") == null || req.getParameter("camp-id") == null) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request: Missing parameters");
            return;
        }
        
        String actId = req.getParameter("act-id");
        int campId = Integer.parseInt(req.getParameter("camp-id"));
        
        try {
            Properties configProperties = new Properties();
            Properties sqlProperties = new Properties();
            sqlProperties.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            configProperties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));
    
            CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);
            campamentsManager.associateActivitiesToCampaments(campId, actId);
            
            res.setStatus(HttpServletResponse.SC_OK);
            res.sendRedirect("/summer_camp/mvc/view/messages/actsCampsAssociated.jsp");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Server Error: " + e.getMessage());
        }
    }
}
