package es.uco.pw.business.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
import es.uco.pw.business.common.level.Level;
import es.uco.pw.business.common.userType.*;
import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.display.javabeans.CustomerBean;

import java.io.IOException;
import java.util.Random;
import java.time.LocalDate;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * Servlet implementation for handling the creation of campaments.
 * This servlet is mapped to the URL pattern "/campaments".
 *
 * The expected parameters for the POST request are "start-date", "end-date", "level", and "max-assistants".
 * These parameters are used to create a new campament with the specified details.
 *
 * The authentication is performed using the "customerBean" attribute stored in the session,
 * ensuring that the user is authorized to perform the cancellation operation.
 */
@WebServlet(name = "campamentsServlet", urlPatterns = "/campaments")
public class CampamentsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        HttpSession session = req.getSession();

        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

        if(customerBean == null || customerBean.getType() == UserType.ASSISTANT) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("/summer_camp/index.jsp");
            return;
        }
        
        if (req.getParameter("start-date").equals("") ||
            req.getParameter("end-date").equals("") ||
            req.getParameter("level").equals("") ||
            req.getParameter("max-assistants").equals("")) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.sendRedirect("/summer_camp/mvc/view/forms/parcialInscriptionView.jsp");
            return;
        }
        
        int campId = new Random().nextInt(Integer.MAX_VALUE - 1);
        LocalDate starDate = LocalDate.parse(req.getParameter("start-date"));
        LocalDate endDate = LocalDate.parse(req.getParameter("end-date"));
        Level level = Level.valueOf(req.getParameter("level"));
        int maxAssistants = Integer.parseInt(req.getParameter("max-assistants"));

        try {
            Properties configProperties = new Properties();
            Properties sqlProperties = new Properties();
            sqlProperties.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            configProperties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));
    
            CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);
            campamentsManager.createCampaments(campId, starDate, endDate, level, maxAssistants);
            
            res.setStatus(HttpServletResponse.SC_CREATED);
            res.sendRedirect("/summer_camp/mvc/view/messages/campamentsCreated.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.getSession().setAttribute("message", e.getMessage());
            res.sendRedirect("/summer_camp/mvc/view/errors/error.jsp");
        }
    }
}
