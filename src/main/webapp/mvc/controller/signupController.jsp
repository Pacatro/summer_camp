<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "es.uco.pw.business.user.UserDTO" %>
<%@ page import = "es.uco.pw.business.managers.UserManager" %>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>

<%
    String nextPage = "/index.jsp";
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

    String email = request.getParameter("email");
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    String typeString = request.getParameter("type");
    
    UserType type;
    if(typeString.equals("ASSISTANT")){
        type = UserType.ASSISTANT;
    }else{
        type = UserType.ADMIN;
    }

    UserDTO user = new UserDTO(email, name, password, type);

    if(manager.signup(user)){

%>
<jsp:setProperty property="emailUser" value="<%=email%>" name="customerBean"/>
<jsp:setProperty property="type" value="<%=type%>" name="customerBean"/>
<%

        if(type == UserType.ASSISTANT){
            nextPage = "../view/signupAssistView.jsp";
        }else{
            nextPage = "../view/Adminview.jsp";
            messageNextPage = "Bienvenido/a " + name;
        }

    }else{
        //No se ha registrado
        nextPage = "../view/errors/error.jsp";
        messageNextPage = "Ya existe un usuario con ese email";
    }

%>

<jsp:forward page="<%=nextPage%>">
    <jsp:param value="<%=messageNextPage%>" name="message"/>
</jsp:forward>