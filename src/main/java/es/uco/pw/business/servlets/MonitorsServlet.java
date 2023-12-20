package es.uco.pw.business.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.display.javabeans.CustomerBean;
import es.uco.pw.business.common.userType.*;

import java.io.IOException;
import java.util.Random; 

import java.io.FileNotFoundException;
import java.util.Properties;

@WebServlet(name = "MonitorsServlet", urlPatterns = "/monitors")
public class MonitorsServlet extends HttpServlet {
    @Override    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        
        HttpSession session= req.getSession();
        CustomerBean customerBean= (CustomerBean) session.getAttribute("customerBean");
        if(customerBean==null || customerBean.getType()==UserType.ASSISTANT){
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: The user is not an admin");
            return;
        }

        if (req.getParameter("name") == null ||
            req.getParameter("surname") == null ||
            req.getParameter("isEspecial") == null) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request: Missing parameters");
            return;
        }
        
        int monitorId = new Random().nextInt(Integer.MAX_VALUE - 1);
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        boolean isEspecial = Boolean.parseBoolean(req.getParameter("isEspecial"));

        try {
            Properties sqlProperties = new Properties();
            Properties configProperties = new Properties();
            sqlProperties.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            configProperties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));

            CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);
            campamentsManager.createMonitor(monitorId, name, surname, isEspecial);

            res.setStatus(HttpServletResponse.SC_CREATED);
            res.sendRedirect("/summer_camp/mvc/view/messages/monitorCreated.jsp");
       
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request: " + e.getMessage());
        }
    }
}
