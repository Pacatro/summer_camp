package es.uco.pw.business.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.managers.AssistantManager;
import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.business.managers.InscriptionsManager;
import es.uco.pw.display.javabeans.CustomerBean;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;

@WebServlet(name = "deleteCompleteInscriptionsServlet", urlPatterns = "/deleteCompleteInscription")
public class DeleteCompleteInscriptionsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        HttpSession session = req.getSession();

        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

        if(customerBean == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("/summer_camp/index.jsp");
            return;
        }

        if(customerBean.getEmailUser().equals("") || req.getParameter("camp-id").equals("")) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.sendRedirect("/summer_camp/mvc/view/forms/completeInscriptionsView.jsp");
            return;
        }
        
        // Estos parametros se pasaraian de la siguiente forma -> /summer_camp/completeInscription?camp-id=1&assis-id=2
        int campId = Integer.parseInt(req.getParameter("camp-id"));
        String email = customerBean.getEmailUser();

        try {
            Properties configProperties = new Properties();
            Properties sqlProperties = new Properties();
            sqlProperties.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            configProperties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));

            CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);
            AssistantManager assistantManager = new AssistantManager(sqlProperties, configProperties);
            InscriptionsManager inscriptionsManager = new InscriptionsManager(sqlProperties, configProperties);

            CampamentDTO campamentDTO = campamentsManager.getById(campId);
            AssistantDTO assistantDTO = assistantManager.getByEmail(email);

            inscriptionsManager.cancelComplete(campamentDTO, assistantDTO);
            
            res.setStatus(HttpServletResponse.SC_OK);
            res.sendRedirect("/summer_camp/mvc/view/messages/inscriptionsDeleted.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.getSession().setAttribute("message", e.getMessage());
            res.sendRedirect("/summer_camp/mvc/view/errors/error.jsp");
        }
    }
}
