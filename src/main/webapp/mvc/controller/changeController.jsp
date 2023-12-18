<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "es.uco.pw.business.user.UserDTO" %>
<%@ page import = "es.uco.pw.business.managers.UserManager" %>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>
<%@ page import = "java.time.*"%>
<%@ page import = "es.uco.pw.business.assistant.AssistantDTO"%>
<%@ page import = "es.uco.pw.business.managers.AssistantManager"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>

<%
    String nextPage = "";
    String messageNextPage = "";

    String file = application.getInitParameter("sqlproperties");
    String file1 = application.getInitParameter("configproperties");
    java.io.InputStream myIO = application.getResourceAsStream(file);
    java.io.InputStream myIO1 = application.getResourceAsStream(file1);
    java.util.Properties sqlprop = new java.util.Properties();
    java.util.Properties configprop = new java.util.Properties();
    sqlprop.load(myIO);
    configprop.load(myIO1);
    
    UserManager manager = new UserManager(sqlprop, configprop);

    String name = request.getParameter("name");
    String password = request.getParameter("password");
    String email = customerBean.getEmailUser();
    UserType type = customerBean.getType();

    UserDTO user = new UserDTO(email, name, password, type);

    if(manager.update(user)){
        if(type == UserType.ASSISTANT){

            AssistantManager assist_manager = new AssistantManager(sqlprop, configprop);
            String surname = request.getParameter("surname");
            java.time.LocalDate date = java.time.LocalDate.parse(request.getParameter("birthdate"));
            String atentionString = request.getParameter("atention");

            boolean atention;
            if(atentionString.equals("yes")){
                atention = true;
            }else{
                atention = false;
            }

            AssistantDTO assist = assist_manager.getByEmail(email);

            assist_manager.modify(assist.getId(), name, surname, date, atention, email);

            nextPage = "../view/AssistantView.jsp";
            messageNextPage = "Bienvenido/a " + name;
        }else{
            nextPage = "../view/Adminview.jsp";
            messageNextPage = "Bienvenido/a " + name;
        }

    }else{
        nextPage = "../view/errors/error.jsp";
        messageNextPage = "No se ha podido realizar la modificacion";
    }

%>

<jsp:forward page="<%=nextPage%>">
    <jsp:param value="<%=messageNextPage%>" name="message"/>
</jsp:forward>