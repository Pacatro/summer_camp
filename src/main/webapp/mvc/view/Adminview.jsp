<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import ="java.util.*" %>
<%@ page import = "es.uco.pw.business.campament.CampamentDTO"%>
<%@ page import = "es.uco.pw.business.managers.CampamentsManager"%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pagina de Administrador</title>
    </head>
    <body>

        <%
            String file = application.getInitParameter("sqlproperties");
            String file1 = application.getInitParameter("configproperties");
            java.io.InputStream myIO = application.getResourceAsStream(file);
            java.io.InputStream myIO1 = application.getResourceAsStream(file1);
            java.util.Properties sqlprop = new java.util.Properties();
            java.util.Properties configprop = new java.util.Properties();
            sqlprop.load(myIO);
            configprop.load(myIO1);

            String messageNextPage = request.getParameter("message");
        %>

        <div class="Wellcome-message">
            <h2><%= messageNextPage %></h2>
        </div>
        <h2>Lista campamentos </h2>
        <table>
            <thead>
                <th> ID Campamento </th>
                <th> Asistentes </th>
            </thead>
            <tbody>
                <%
                CampamentsManager camp = new CampamentsManager(sqlprop, configprop);
                ArrayList<CampamentDTO> campaments = camp.getAllCampaments();
                %>

                <% for(int i = 0; i < campaments.size(); i++){
                    CampamentDTO campamento = campaments.get(i);
                    int inscripcionesCompletas = camp.getNumInscriptionsC(campamento.getId());
                    int inscripcionesParciales = camp.getNumInscriptionsP(campamento.getId());
                %>

                    <tr>
                        <td><%=campaments.get(i).getId()%></td>
                        <td>Inscripciones Completas: <%= inscripcionesCompletas %></td>
                        <td>Inscripciones Parciales: <%= inscripcionesParciales %></td>
                    </tr>
                <%}%>
                    
            </tbody>
        </table>
    </body>
</html>