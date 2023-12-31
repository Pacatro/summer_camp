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
        <link href="/summer_camp/styles/loginView.css" rel="stylesheet" /> 
        <title>Pagina Asistente</title>
    </head>
    <body>
        <div class="container">
            
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
                        String nextPage = "Adminview.jsp";
                        messageNextPage = "Bienvenido/a" + customerBean.getName();
                %>
                    <jsp:forward page="<%=nextPage%>">
                        <jsp:param value="<%=messageNextPage%>" name="message"/>
                    </jsp:forward>
                <%
                    }
                %>

                <div class="assis-buttons">
                    <button>
                        <a href="/summer_camp/mvc/controller/logoutController.jsp">Desconexion</a>
                    </button>

                    <button>
                        <a href="/summer_camp/mvc/view/forms/changeView.jsp">Modificar datos</a>
                    </button>
                </div>

                <div class="wellcome-message">
                    <% if(messageNextPage == null) { %>
                        <h2>Bienvenido/a <%= customerBean.getName() %></h2>
                    <% } else { %> 
                        <h2><%= messageNextPage %></h2>
                    <% } %>
                    <h3>Fecha actual: <%= new java.util.Date() %></h3>
                </div>

                <h2>Lista de Campamentos</h2>
                <% 
                    AssistantManager assis=new AssistantManager(sqlprop, configprop);
                    ArrayList<CampamentDTO> campaments=assis.getCampaments(customerBean.getEmailUser());
                %>

                <table>
                    <thead>
                        <tr>
                            <th>Campamentos</th>
                            <th>Fecha de inicio</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(CampamentDTO campament : campaments) { %>
                            <tr>
                                <td><%=campament.getId()%></td>
                                <td><%=campament.getInitDate()%></td>
                            </tr> 
                        <% } %>
                    </tbody>
                </table>

                <div class="assis-buttons">
                    
                    <button class="button-assis">
                        <a href="/summer_camp/mvc/view/forms/campSearchDate.jsp">Filtrar por fecha</a>
                    </button class="button-assis">

                    <button class="button-assis">
                        <a href="/summer_camp/mvc/view/forms/campSearchLevel.jsp">Filtrar por nivel educativo</a>
                    </button class="button-assis">

                    <button class="button-assis">
                        <a href="/summer_camp/mvc/view/forms/campSearchPlaces.jsp">Filtrar por plazas m&iacute;nimas</a>
                    </button class="button-assis">

                    <button class="button-assis">
                        <a href="/summer_camp/mvc/view/forms/completeInscriptionsView.jsp">Inscripcion completa</a>
                    </button class="button-assis">

                    <button class="button-assis">
                        <a href="/summer_camp/mvc/view/forms/parcialInscriptionsView.jsp">Inscripcion parcial</a>
                    </button class="button-assis">

                    <button class="button-assis">
                        <a href="/summer_camp/mvc/view/forms/cancelCompleteInscriptions.jsp">Cancelar inscripcion completa</a>
                    </button class="button-assis">
                    
                    <button class="button-assis">
                        <a href="/summer_camp/mvc/view/forms/cancelParcialInscriptions.jsp">Cancelar inscripcion parcial</a>
                    </button>
                </div>
            </main>
        </div>
    </body>
</html>