<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>
<%@ page import="es.uco.pw.business.managers.AssistantManager"%>
<%@ page import="es.uco.pw.business.managers.UserManager"%>
<%@ page import = "es.uco.pw.business.assistant.AssistantDTO"%>
<%@ page import = "es.uco.pw.business.user.UserDTO"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/index.css" rel="stylesheet" />
		<title>Modificar datos</title>
	</head>
	<body>
		<header>
			<h1>Summer Camp<h1>
		</header>

		<main>
			<%
				String file = application.getInitParameter("sqlproperties");
				String file1 = application.getInitParameter("configproperties");
				java.io.InputStream myIO = application.getResourceAsStream(file);
				java.io.InputStream myIO1 = application.getResourceAsStream(file1);
				java.util.Properties sqlprop = new java.util.Properties();
				java.util.Properties configprop = new java.util.Properties();
				sqlprop.load(myIO);
				configprop.load(myIO1);

				String nextPage = "../controller/signupController.jsp";
				String messageNextPage = request.getParameter("message");
				if (messageNextPage == null) messageNextPage = "";

				if (customerBean == null || customerBean.getEmailUser().equals("")) {
					nextPage = "/index.jsp";
			%>
				<jsp:forward page="<%=nextPage%>">
					<jsp:param value="<%=messageNextPage%>" name="message"/>
				</jsp:forward>
			<%
				} else {
					UserManager usermanager = new UserManager(sqlprop, configprop);
					UserDTO user = usermanager.getById(customerBean.getEmailUser());
					%>

					<%= messageNextPage %>
					<br/>
					<br/>
					<h1>Modificar datos</h1>
					<form method="post" action="../controller/changeController.jsp">
						<label for="name">Nombre: </label>
							<input type="text" name="name" value="<%=user.getName()%>" placeholder="Nombre">
						<label for="password">Contraseña: </label>
							<input type="text" name="password" value="<%=user.getPassword()%>" placeholder="Contraseña">
                    <%
                        if(customerBean.getType() == UserType.ASSISTANT){
							AssistantManager assistmanager = new AssistantManager(sqlprop, configprop);
							AssistantDTO assist = assistmanager.getByEmail(user.getEmail());
                    %>
                        <label for="surname">Apellidos: </label>
                            <input type="text" name="surname" value="<%=assist.getSurname()%>" placeholder="Apellidos">
                        <label for="birthdate">Fecha de nacimiento: </label>
                            <input type="date" name="birthdate" value="<%=assist.getDate()%>">
                        <label for="atention">¿Necesita atencion especial?: </label>
                            <select name="atention">

							<%
								if(assist.getAtention()){
							%>
                                <option value="yes">Si</option>
								<option value="no">No</option>
							<%
								}else{
							%>
                                <option value="no">No</option>
                                <option value="yes">Si</option>
							<%
								}
							%>
                    <%
                        }
                    %>
						<br/>
						<input type="submit" value="Submit">
					</form>
				<% } %>
		</main>
	</body>
</html>