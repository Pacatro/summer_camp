<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="es.uco.pw.business.campament.CampamentDTO"%>
<%@ page import="es.uco.pw.business.managers.AssistantManager"%>
<%@ page import = "es.uco.pw.business.common.userType.UserType"%>
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="/summer_camp/styles/index.css" rel="stylesheet" />
        <title>Pagina Asistente</title>
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

        <main>

            <%
                if(customerBean == null || customerBean.getEmailUser().equals("")){
                    String nextPage = "/index.jsp";
            %>
                <jsp:forward page="<%=nextPage%>">
                    <jsp:param value="<%=messageNextPage%>" name="message"/>
                </jsp:forward>
            <%
                }else if(customerBean.getType() == UserType.ADMIN){
                    String nextPage = "/index.jsp";
            %>
                <jsp:forward page="<%=nextPage%>">
                    <jsp:param value="<%=messageNextPage%>" name="message"/>
                </jsp:forward>
            <%
                }
            %>

            <button>
                <a href="/summer_camp/mvc/controller/logoutController.jsp">Desconexion</a>
            </button>

            <button>
                <a>Modificar datos</a>
            </button>

            <div class="welcome-message">
                <h2> <%= messageNextPage %> </h2>
                <p>Fecha actual: <%= new java.util.Date() %></p>
            </div>

            <h2>Lista de Campamentos</h2>
            <% 
                AssistantManager assis=new AssistantManager(sqlprop, configprop);
                ArrayList<CampamentDTO> campaments=assis.getCampaments(customerBean.getEmailUser());
            %>

            <ul>
                <% for(int i=0;i<campaments.size();i++){%>
                    <li> <%=campaments.get(i).getId()%> </li>
                <%}%>
            </ul>

        </main>

    </body>
</html>