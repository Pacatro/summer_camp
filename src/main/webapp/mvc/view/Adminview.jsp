<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import = "es.uco.pw.business.campament.CampamentDTO"%>
<%@ page import = "es.uco.pw.business.managers.CampamentsManager"%>
<%@ page import = "es.uco.pw.business.common.userType.UserType"%>
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="/summer_camp/styles/loginView.css" rel="stylesheet" />
        <title>Pagina de Administrador</title>
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

            <header>
                <h1>Summer Camp<h1>
            </header>

            <main>

                <%
                    if(customerBean == null || customerBean.getEmailUser().equals("")){
                        String nextPage = "/index.jsp";
                %>
                    <jsp:forward page="<%=nextPage%>">
                        <jsp:param value="<%=messageNextPage%>" name="message"/>
                    </jsp:forward>
                <%
                    }else if(customerBean.getType() == UserType.ASSISTANT){
                        String nextPage = "/AssistantView.jsp";
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
                </div>

                <h2>Lista campamentos: </h2>

                <table>
                    <thead>
                        <th> ID Campamento </th>
                        <th> Inscripciones completas </th>
                        <th> Inscripciones parciales </th>
                    </thead>
                    <tbody>
                        <%
                        CampamentsManager camp = new CampamentsManager(sqlprop, configprop);
                        ArrayList<Integer> campaments = new ArrayList<Integer>();
                        ArrayList<Integer> num_incrip_c = new ArrayList<Integer>();
                        ArrayList<Integer> num_incrip_p = new ArrayList<Integer>();

                        camp.getNumInscriptionsAll(campaments, num_incrip_c, num_incrip_p);

                        for(int i = 0; i < campaments.size(); i++){
                        %>
                            <tr>
                                <td><%=campaments.get(i)%></td>
                                <td><%= num_incrip_c.get(i) %></td>
                                <td><%= num_incrip_p.get(i) %></td>
                            </tr>
                        <% } %>       
                    </tbody>
                </table>

                <div class="assis-buttons">
                    <button class="button-assis">
                        <a href="/summer_camp/mvc/view/forms/createActivitiesView.jsp">Crear una actividad</a>
                    </button>

                    <button class="button-assis">
                        <a href="/summer_camp/mvc/view/forms/createMonitorView.jsp">Crear un monitor</a>
                    </button>

                    <button class="button-assis">
                        <a href="/summer_camp/mvc/view/forms/createCampamentView.jsp">Crear un campamento</a>
                    </button>

                    <button class="button-assis">
                        <a href="/summer_camp/mvc/view/forms/actCampView.jsp">Asociar actividad a campamento</a>
                    </button>

                    <button class="button-assis">
                        <a href="/summer_camp/mvc/view/forms/campMonView.jsp">Asociar monitor a campamento</a>
                    </button>

                    <button class="button-assis">
                        <a href="/summer_camp/mvc/view/forms/monActView.jsp">Asociar monitor a actividad</a>
                    </button>
                </div>
            </main>
        </div>
    </body>
</html>