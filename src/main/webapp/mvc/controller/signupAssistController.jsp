<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.time.*"%>
<%@ page import = "es.uco.pw.business.assistant.AssistantDTO"%>
<%@ page import = "es.uco.pw.business.managers.AssistantManager"%>
<%@ page import = "es.uco.pw.business.user.UserDTO"%>
<%@ page import = "es.uco.pw.business.managers.UserManager"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>

<%
    String messageNextPage = "";

    String file = application.getInitParameter("sqlproperties");
    String file1 = application.getInitParameter("configproperties");
    java.io.InputStream myIO = application.getResourceAsStream(file);
    java.io.InputStream myIO1 = application.getResourceAsStream(file1);
    java.util.Properties sqlprop = new java.util.Properties();
    java.util.Properties configprop = new java.util.Properties();
    sqlprop.load(myIO);
    configprop.load(myIO1);
    
    AssistantManager manager = new AssistantManager(sqlprop, configprop);
    int id = Integer.parseInt(request.getParameter("dni"));
    String surname = request.getParameter("surname");
    java.time.LocalDate date = java.time.LocalDate.parse(request.getParameter("birthdate"));
    String atentionString = request.getParameter("atention");

    boolean atention;
    if(atentionString.equals("yes")){
        atention = true;
    }else{
        atention = false;
    }

    UserManager userManager = new UserManager(sqlprop, configprop);
    UserDTO user = userManager.getById(customerBean.getEmailUser());

    AssistantDTO assist = new AssistantDTO(id, user.getName(), surname, date, atention, user.getEmail());

    manager.register(assist);

    String nextPage = "../view/AssistantView.jsp";
%>

<jsp:forward page="<%=nextPage%>">
    <jsp:param value="<%=messageNextPage%>" name="message"/>
</jsp:forward>