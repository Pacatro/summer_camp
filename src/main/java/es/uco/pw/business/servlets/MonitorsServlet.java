package es.uco.pw.business.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.display.javabeans.CustomerBean;
import es.uco.pw.business.common.userType.*;

import java.io.IOException;
import java.util.Random;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

@WebServlet(name = "MonitorsServlet", urlPatterns = "/monitors")
public class MonitorsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        
        HttpSession session= req.getSession();
        CustomerBean customerBean= (CustomerBean) session.getAttribute("customerBean");
        if(customerBean==null || customerBean.getType()==UserType.ASSISTANT){
            res.sendError(401, "Unauthorized: The user is not an admin");
            return;
        }

        if (req.getParameter("name") == null ||
            req.getParameter("surname") == null ||
            req.getParameter("isEspecial") == null) {
            res.sendError(400, "Bad Request: Missing parameters");
            return;
        }
        
        int monitorId = new Random().nextInt();
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        boolean isEspecial = Boolean.parseBoolean(req.getParameter("isEspecial"));


        Properties sqlProperties = new Properties();
        Properties configProperties = new Properties();
        sqlProperties.load(new FileInputStream("src/main/webapp/WEB-INF/sql.properties"));
        configProperties.load(new FileInputStream("src/main/webapp/WEB-INF/config.properties"));

        CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);

        try {
            campamentsManager.createMonitor(monitorId, name, surname, isEspecial);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            res.sendError(500, "Server Error: " + e.getMessage());
        }

        res.setStatus(201);
    }
}
