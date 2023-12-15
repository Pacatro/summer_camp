package es.uco.pw.business.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.common.schedule.Schedule;
import es.uco.pw.business.managers.AssistantManager;
import es.uco.pw.business.managers.CampamentsManager;
import es.uco.pw.business.managers.InscriptionsManager;
import es.uco.pw.display.javabeans.CustomerBean;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * Servlet implementation for handling complete camp inscriptions cancellation.
 * This servlet is mapped to the URL pattern "/completeInscription".
 *
 * The expected parameters for the POST request are "camp-id", "assis-id" and "schedule".
 * These parameters are used to identify the camp, assistant and schedule for create the complete inscription.
 *
 * The expected parameters for the DELETE request are "camp-id" and "assis-id".
 * These parameters are used to identify the camp and assistant for which the complete inscription should be canceled.
 * 
 * The authentication is performed using the "customerBean" attribute stored in the session,
 * ensuring that the user is authorized to perform the cancellation operation.
 *
 */
@WebServlet(name = "completeInscriptionsServlet", urlPatterns = "/completeInscription")
public class CompleteInscriptionsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

        if(customerBean == null) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: The user is not an admin");
            return;
        }

        if(req.getParameter("assis-id") == null || 
           req.getParameter("camp-id") == null || 
           req.getParameter("schedule") == null) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request: Missing parameters");
            return;
        }
        
        // Estos parametros se pasaraian de la siguiente forma -> /summer_camp/completeInscription?camp-id=1&assis-id=2
        int campId = Integer.parseInt(req.getParameter("camp-id"));
        int assisId = Integer.parseInt(req.getParameter("assis-id"));
        Schedule schedule = Schedule.valueOf(req.getParameter("schedule"));

        try {
            // TODO: MOSTRAR AVISOS
            Properties configProperties = new Properties();
            Properties sqlProperties = new Properties();
            sqlProperties.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            configProperties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));

            CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);
            AssistantManager assistantManager = new AssistantManager(sqlProperties, configProperties);
            InscriptionsManager inscriptionsManager = new InscriptionsManager(sqlProperties, configProperties);

            CampamentDTO campamentDTO = campamentsManager.getById(campId);
            AssistantDTO assistantDTO = assistantManager.getById(assisId);

            inscriptionsManager.enrollComplete(campamentDTO, assistantDTO, schedule);
            
            res.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.getStackTrace();
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request: " + e.getMessage());
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        HttpSession session = req.getSession();

        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

        if(customerBean == null) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: The user is not an admin");
            return;
        }
        
        if(req.getParameter("assis-id") == null || req.getParameter("camp-id") == null) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request: Missing parameters");
            return;
        }
        
        // Estos parametros se pasaraian de la siguiente forma -> /summer_camp/completeInscription?camp-id=1&assis-id=2
        int campId = Integer.parseInt(req.getParameter("camp-id"));
        int assisId = Integer.parseInt(req.getParameter("assis-id"));

        try {
            Properties configProperties = new Properties();
            Properties sqlProperties = new Properties();
            sqlProperties.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            configProperties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));

            CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);
            AssistantManager assistantManager = new AssistantManager(sqlProperties, configProperties);
            InscriptionsManager inscriptionsManager = new InscriptionsManager(sqlProperties, configProperties);

            CampamentDTO campamentDTO = campamentsManager.getById(campId);
            AssistantDTO assistantDTO = assistantManager.getById(assisId);

            inscriptionsManager.cancelComplete(campamentDTO, assistantDTO);
            
            res.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request: " + e.getMessage());
        }
    }
}
