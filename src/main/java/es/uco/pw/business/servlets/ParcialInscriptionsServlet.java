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

/**
 * Servlet implementation for handling partial camp inscriptions cancellation.
 * This servlet is mapped to the URL pattern "/parcialInscription".
 *
 * The expected parameters for the GET request are "camp-id" and "assis-id".
 * These parameters are used to identify the camp and assistant for create the parcial inscription.
 * 
 * The expected parameters for the POST request are "camp-id" and "assis-id".
 * These parameters are used to identify the camp and assistant for create the parcial inscription.
 * 
 * The authentication is performed using the "customerBean" attribute stored in the session,
 * ensuring that the user is authorized to perform the cancellation operation.
 *
 */
@WebServlet(name = "parcialInscriptionsServlet", urlPatterns = "/parcialInscription")
public class ParcialInscriptionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

        if(customerBean == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("/summer_camp/");
            return;
        }

        if(req.getParameter("camp-id").equals("")) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.sendRedirect("/summer_camp/mvc/view/forms/parcialInscriptionsView.jsp");
            return;
        }

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

            double price = inscriptionsManager.calcPrice(campamentDTO, assistantDTO.getAtention());

            res.setStatus(HttpServletResponse.SC_OK);
            res.setHeader("price", String.valueOf(price));
        } catch(Exception e) {
            e.printStackTrace();
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.getSession().setAttribute("message", e.getMessage());
            res.sendRedirect("/summer_camp/mvc/view/errors/error.jsp");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

        if(customerBean == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("/summer_camp/");
            return;
        }

        if(req.getParameter("camp-id").equals("")) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.sendRedirect("/summer_camp/mvc/view/forms/parcialInscriptionsView.jsp");
            return;
        }
        
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

            inscriptionsManager.enrollParcial(campamentDTO, assistantDTO);
            
            res.setStatus(HttpServletResponse.SC_CREATED);
            res.sendRedirect("/summer_camp/mvc/view/messages/inscriptionsCreated.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.getSession().setAttribute("message", e.getMessage());
            res.sendRedirect("/summer_camp/mvc/view/errors/error.jsp");
        }
    }
}
