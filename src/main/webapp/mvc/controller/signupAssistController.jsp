<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "es.uco.pw.business.assistant.AssistantDTO" %>
<%@ page import = "es.uco.pw.business.managers.AssistantManager" %>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>

<%
    String nextPage = "../view/index.jsp";
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
    int id = request.getParameter("dni");
    String surname = request.getParameter("surname");
    LocalDate date = request.getParameter("birthdate");
    String atentionString = request.getParameter("atention");

    boolean atention;
    if(atentionString.equals("yes")){
        atention = true;
    }else{
        atention = false;
    }

    AssistantDTO assist = new AssistantDTO(id, customerBean.get)
%>