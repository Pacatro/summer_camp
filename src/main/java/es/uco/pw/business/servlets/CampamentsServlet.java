package es.uco.pw.business.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.uco.pw.business.common.level.Level;
import es.uco.pw.business.managers.CampamentsManager;

import java.io.IOException;
import java.util.Random;
import java.time.LocalDate;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

@WebServlet(name = "campamentServlet", urlPatterns = "/campaments")
public class CampamentsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        if (req.getParameter("start-date") == null ||
            req.getParameter("end-date") == null ||
            req.getParameter("level") == null ||
            req.getParameter("max-assistants") == null) {
            res.sendError(400, "Bad Request: Missing parameters");
            return;
        }
        
        int campId = new Random().nextInt();
        LocalDate starDate = LocalDate.parse(req.getParameter("start-date"));
        LocalDate endDate = LocalDate.parse(req.getParameter("end-date"));
        Level level = Level.valueOf(req.getParameter("level"));
        int maxAssistants = Integer.parseInt(req.getParameter("max-assistants"));

        Properties sqlProperties = new Properties();
        Properties configProperties = new Properties();
        sqlProperties.load(new FileInputStream("src/main/webapp/WEB-INF/sql.properties"));
        configProperties.load(new FileInputStream("src/main/webapp/WEB-INF/config.properties"));

        CampamentsManager campamentsManager = new CampamentsManager(sqlProperties, configProperties);

        try {
            campamentsManager.createCampaments(campId, starDate, endDate, level, maxAssistants);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            res.sendError(500, "Server Error: " + e.getMessage());
        }

        res.setStatus(201);
    }
}
