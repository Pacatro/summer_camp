package es.uco.pw.business.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.common.level.Level;
import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.display.javabeans.CustomerBean;


@WebServlet(name = "availableCampsLevelServlet", urlPatterns = "/availableCampsLevel")
public class AvaibleCampsLevelServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        HttpSession session = req.getSession();

        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

        if(customerBean == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("/summer_camp/index.jsp");
            return;
        }

        if (req.getParameter("level").equals("")){
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.sendRedirect("/summer_camp/mvc/view/forms/campSearchLevel.jsp");
            return;
        }

        Level level = Level.valueOf(req.getParameter("level"));

        try {
            Properties configProperties = new Properties();
            Properties sqlProperties = new Properties();
            sqlProperties.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            configProperties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));
    
            CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);
            ArrayList<CampamentDTO> campaments = campamentsManager.getCampsByLevel(level);

            res.setStatus(HttpServletResponse.SC_OK);
            req.getSession().setAttribute("campaments", campaments);
            res.sendRedirect("/summer_camp/mvc/view/messages/searchCampResultLevel.jsp");
            
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.getSession().setAttribute("message", e.getMessage());
            res.sendRedirect("/summer_camp/mvc/view/errors/error.jsp");
        }
        
    }

}
