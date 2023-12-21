package es.uco.pw.business.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.display.javabeans.CustomerBean;

import java.io.IOException;
import java.time.LocalDate;
import java.io.FileNotFoundException;
import java.util.Properties;

@WebServlet(name = "availableCampsServlet", urlPatterns = "/availableCamps")
public class AvailableCampsDateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        HttpSession session = req.getSession();

        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

        if(customerBean == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("/summer_camp/");
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
        
        LocalDate starDate = LocalDate.parse(req.getParameter("start-date"));
        LocalDate endDate = LocalDate.parse(req.getParameter("end-date"));

        try {
            Properties configProperties = new Properties();
            Properties sqlProperties = new Properties();
            sqlProperties.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            configProperties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));
    
            CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);
            campamentsManager.getCampsByDateInterval(starDate, endDate);
            
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.getSession().setAttribute("message", e.getMessage());
            res.sendRedirect("/summer_camp/mvc/view/errors/error.jsp");
        }
    }
}