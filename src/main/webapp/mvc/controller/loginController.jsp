<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.user.UserDTO,es.uco.pw.business.managers.UserManager,es.uco.pw.business.common.userType.UserType" %>
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean" />

<%
	String file = application.getInitParameter("sqlproperties");
	String file1 = application.getInitParameter("configproperties");
	java.io.InputStream myIO = application.getResourceAsStream(file);
	java.io.InputStream myIO1 = application.getResourceAsStream(file1);
	java.util.Properties sqlprop = new java.util.Properties();
	java.util.Properties configprop = new java.util.Properties();
	sqlprop.load(myIO);
	configprop.load(myIO1);

	String nextPage = "";
	String messageNextPage = "";

	// Caso 2
	if (customerBean == null || customerBean.getEmailUser().equals("")) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (email != null && password != null) {
			UserManager userManager = new UserManager(sqlprop, configprop);
			
			if(!userManager.signin(email, password)){
				nextPage = "../view/error/error.html";
				messageNextPage = "Usuario o contraseña incorrectos";
			}

			UserDTO user = userManager.getById(email);

			nextPage = (user.getType() == UserType.ADMIN) ? "../view/Adminview.jsp"
											 			  : "../view/AssistantView.jsp";

			messageNextPage = "Bienvenido " + user.getName();
			%>
			<jsp:setProperty property="emailUser" value="<%=email%>" name="customerBean"/>
			<jsp:setProperty property="type" value="<%=user.getType()%>" name="customerBean"/>
			<jsp:setProperty property="name" value="<%=user.getName()%>" name="customerBean"/>
			<%		
		} else nextPage = "../view/loginView.jsp";
	}
%>

<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=messageNextPage%>" name="message"/>
</jsp:forward>
