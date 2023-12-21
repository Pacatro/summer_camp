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
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("/summer_camp/");
            return;
        }

        if (req.getParameter("name").equals("") ||
            req.getParameter("level").equals("") ||
            req.getParameter("schedule").equals("") ||
            req.getParameter("max-participants").equals("") || 
            req.getParameter("num-monitors").equals("")) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.sendRedirect("/summer_camp/mvc/view/forms/parcialInscriptionView.jsp");
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
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.getSession().setAttribute("message", e.getMessage());
            res.sendRedirect("/summer_camp/mvc/view/errors/error.jsp");
        }
    }
}
