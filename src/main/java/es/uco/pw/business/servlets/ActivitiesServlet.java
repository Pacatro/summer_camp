package es.uco.pw.business.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.business.common.level.Level;
import es.uco.pw.business.common.schedule.*;
import es.uco.pw.display.javabeans.CustomerBean;
import es.uco.pw.business.common.userType.*;

import java.io.IOException;
import java.util.Random;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

@WebServlet(name = "ActivitiesServlet", urlPatterns = "/activities")
public class ActivitiesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        
        HttpSession session= req.getSession();
        CustomerBean customerBean= (CustomerBean) session.getAttribute("customerBean");
        if(customerBean==null || customerBean.getType()==UserType.ASSISTANT){
            res.sendError(401, "Unauthorized: The user is not an admin");
            return;
        }

        if (req.getParameter("name") == null ||
            req.getParameter("level") == null ||
            req.getParameter("schedule") == null ||
            req.getParameter("max_participants") == null || 
            req.getParameter("num_monitors") == null) {
            res.sendError(400, "Bad Request: Missing parameters");
            return;
        }
        
        String name = req.getParameter("name");
        Level level = Level.valueOf(req.getParameter("level"));
        Schedule schedule = Schedule.valueOf(req.getParameter("schedule"));
        int maxParticipants = Integer.parseInt(req.getParameter("max_participants"));
        int numMonitors = Integer.parseInt(req.getParameter("num_monitors"));
    
        Properties sqlProperties = new Properties();
        Properties configProperties = new Properties();
        sqlProperties.load(new FileInputStream("src/main/webapp/WEB-INF/sql.properties"));
        configProperties.load(new FileInputStream("src/main/webapp/WEB-INF/config.properties"));

        CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);

        try {
            campamentsManager.createActivity(name, level, schedule, maxParticipants, numMonitors);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            res.sendError(500, "Server Error: " + e.getMessage());
        }

        res.setStatus(201);
    }
}
