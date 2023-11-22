<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import ="java.util.*" %>
<%@ page import = "es.uco.pw.business.campament.CampamentDTO"%>
<%@ page import = "es.uco.pw.data.dao.campament.CampamentDAO"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel de Administrador</title>
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
    %>

    <h2>Lista de Campamentos</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID del Campamento</th>
                <th>Número de Personas Inscritas</th>
            </tr>
        </thead>
        <tbody>
            <%
            CampamentDAO campamentDAO = new CampamentDAO(sqlprop, configprop);
            ArrayList<CampamentDTO> campaments = campamentDAO.getAll();
            //customerBean.setCampaments(campaments);
            %>

            <%for(int i = 0; i < campaments.size(); i++){%>
                <tr>
                    <td><%=campaments.get(i).getId()%></td>
                </tr>
            <%}%>
            
            <!-- Itera sobre la lista de campamentos y muestra la información -->
            <%-- <c:forEach var="campaments" items="${campaments}">
                <tr>
                    <td>${campaments.id}</td>
                </tr>
            </c:forEach> --%>
        </tbody>
    </table>

    <%-- <c:choose>
        <c:when test="${not empty customerBean and customerBean.type2 == 'ADMIN'}">
            <!-- Contenido para el administrador -->
            <h2>Lista de Campamentos</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID del Campamento</th>
                        <th>Número de Personas Inscritas</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    CampamentDAO campamentDAO = new CampamentDAO();
                    ArrayList<CampamentDTO> campaments = campamentDAO.getAll();
			        //customerBean.setCampaments(campaments);
                    %>

                    <%for(int i = 0; i < campaments.size(); i++){%>
                        <tr>
                            <td><%=campaments.get(i).getId()%></td>
                        </tr>
                    <%}%>
                    
                    <!-- Itera sobre la lista de campamentos y muestra la información -->
                    <%-- <c:forEach var="campaments" items="${campaments}">
                        <tr>
                            <td>${campaments.id}</td>
                        </tr>
                    </c:forEach> --%>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <!-- Si no es administrador, redirige o muestra un mensaje de error -->
            <c:redirect url="/pagina-de-error.jsp" />
        </c:otherwise>
    </c:choose> --%>

</body>
</html>