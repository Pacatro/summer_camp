package es.uco.pw.business.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.managers.AssistantManager;
import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.business.managers.InscriptionsManager;
import es.uco.pw.display.javabeans.CustomerBean;

import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "deleteParcialInscriptionsServlet", urlPatterns = "/deleteParcialInscription")
public class DeleteParcialInscriptionsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

        if(customerBean == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("/summer_camp/");
            return;
        }

        if (customerBean.getEmailUser().equals("") || req.getParameter("camp-id").equals("")) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.sendRedirect("/summer_camp/mvc/view/forms/parcialInscriptionView.jsp");
            return;
        }

        int campId = Integer.parseInt(req.getParameter("camp-id"));
        String email = customerBean.getEmailUser();

        try {
            Properties configProperties = new Properties();
            Properties sqlProperties = new Properties();
            sqlProperties.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            configProperties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));

            System.out.println("HOla1");

            CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);
            AssistantManager assistantManager = new AssistantManager(sqlProperties, configProperties);
            InscriptionsManager inscriptionsManager = new InscriptionsManager(sqlProperties, configProperties);

            CampamentDTO campamentDTO = campamentsManager.getById(campId);
            AssistantDTO assistantDTO = assistantManager.getByEmail(email);

            inscriptionsManager.cancelParcial(campamentDTO, assistantDTO);

            res.setStatus(HttpServletResponse.SC_OK);
            res.sendRedirect("/summer_camp/mvc/view/messages/inscriptionsDeleted.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.getSession().setAttribute("message", e.getMessage());
            res.sendRedirect("/summer_camp/mvc/view/errors/error.jsp");
        }
    }
}
