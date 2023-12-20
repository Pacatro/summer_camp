package es.uco.pw.business.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.business.common.level.Level;
import es.uco.pw.business.common.schedule.*;
import es.uco.pw.display.javabeans.CustomerBean; 
import es.uco.pw.business.common.userType.*;

import java.io.IOException;

import java.io.FileNotFoundException;
import java.util.Properties;

@WebServlet(name = "ActivitiesServlet", urlPatterns = "/activities")
public class ActivitiesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        HttpSession session= req.getSession();
        
        CustomerBean customerBean= (CustomerBean) session.getAttribute("customerBean");
        if(customerBean==null || customerBean.getType()==UserType.ASSISTANT){
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: The user is not an admin");
            return;
        }

        if (req.getParameter("name") == null ||
            req.getParameter("level") == null ||
            req.getParameter("schedule") == null ||
            req.getParameter("max-participants") == null || 
            req.getParameter("num-monitors") == null) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request: Missing parameters");
            return;
        }
        
        String name = req.getParameter("name");
        Level level = Level.valueOf(req.getParameter("level"));
        Schedule schedule = Schedule.valueOf(req.getParameter("schedule"));
        int maxParticipants = Integer.parseInt(req.getParameter("max-participants"));
        int numMonitors = Integer.parseInt(req.getParameter("num-monitors"));
    
        try {
            Properties sqlProperties = new Properties();
            Properties configProperties = new Properties();
            sqlProperties.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            configProperties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));

            CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);
            campamentsManager.createActivity(name, level, schedule, maxParticipants, numMonitors);

            res.setStatus(HttpServletResponse.SC_CREATED);
            res.sendRedirect("/summer_camp/mvc/view/messages/activityCreated.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request: " + e.getMessage());
        }
    }
}
