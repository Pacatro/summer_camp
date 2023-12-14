package es.uco.pw.business.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.uco.pw.business.common.userType.*;
import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.display.javabeans.CustomerBean;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

@WebServlet(name = "activitiesMonitorsServlet", urlPatterns = "/activityMonitor")
public class ActivitiesMonitorsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        HttpSession session = req.getSession();

        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

        if(customerBean == null || customerBean.getType() == UserType.ASSISTANT) {
            res.sendError(401, "Unauthorized: The user is not an admin");
            return;
        }
        
        if(req.getParameter("act-id") == null || req.getParameter("mon-id") == null) {
            res.sendError(400, "Bad Request: Missing parameters");
            return;
        }
        
        String actId = req.getParameter("act-id");
        int monId = Integer.parseInt(req.getParameter("mon-id"));

        Properties sqlProperties = new Properties();
        Properties configProperties = new Properties();
        sqlProperties.load(new FileInputStream("src/main/webapp/WEB-INF/sql.properties"));
        configProperties.load(new FileInputStream("src/main/webapp/WEB-INF/config.properties"));

        CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);

        try {
            campamentsManager.associateMonitorsToActivities(monId, actId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res.sendError(500, "Server Error: " + e.getMessage());
        }

        res.setStatus(200);
    }
}

