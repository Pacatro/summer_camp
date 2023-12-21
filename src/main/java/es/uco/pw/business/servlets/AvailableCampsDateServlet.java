package es.uco.pw.business.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.display.javabeans.CustomerBean;

import java.io.IOException;
import java.time.LocalDate;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Properties;

@WebServlet(name = "availableCampsDateServlet", urlPatterns = "/availableCampsDate")
public class AvailableCampsDateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        HttpSession session = req.getSession();

        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

        if(customerBean == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("/summer_camp/index.jsp");
            return;
        }
        
        if (req.getParameter("start-date") == null ||
            req.getParameter("end-date") == null) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.sendRedirect("/summer_camp/mvc/view/forms/campSearchDate.jsp");
            return;
        }
        
        LocalDate startDate = LocalDate.parse(req.getParameter("start-date"));
        LocalDate endDate = LocalDate.parse(req.getParameter("end-date"));

        try {
            Properties configProperties = new Properties();
            Properties sqlProperties = new Properties();
            sqlProperties.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            configProperties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));
    
            CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);
            ArrayList<CampamentDTO> campaments = campamentsManager.getCampsByDateInterval(startDate, endDate);

            res.setStatus(HttpServletResponse.SC_OK);
            req.getSession().setAttribute("campaments", campaments);
            res.sendRedirect("/summer_camp/mvc/view/messages/searchCampResult.jsp");
            
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.getSession().setAttribute("message", e.getMessage());
            res.sendRedirect("/summer_camp/mvc/view/errors/error.jsp");
        }
    }
}