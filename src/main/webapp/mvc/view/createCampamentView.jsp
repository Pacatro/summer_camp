<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/index.css" rel="stylesheet" />
		<title>Crear campamento</title>
	</head>
	<body>
		<header>
			<h1>Summer Camp<h1>
		</header>

		<main>
			<h1>Crear campamento</h1>

			<%
				String nextPage = "";
				String messageNextPage = request.getParameter("message");
				if (messageNextPage == null) messageNextPage = "";

				if(customerBean == null || customerBean.getEmailUser().equals("")) {
					nextPage = "/mvc/view/errors/error.jsp";
					messageNextPage = "Debes iniciar sesion primero.";
					%>
						<jsp:forward page="<%=nextPage%>">
							<jsp:param value="<%=messageNextPage%>" name="message"/>
						</jsp:forward>
					<%
				} else if (customerBean.getType() == UserType.ASSISTANT) {
					nextPage = "/mvc/view/errors/error.jsp";
					messageNextPage = "No estas autorizado para entrar en esta pagina.";
					%>
						<jsp:forward page="<%=nextPage%>">
							<jsp:param value="<%=messageNextPage%>" name="message"/>
						</jsp:forward>
					<%
				} else { %>
					<form method="post" action="/summer_camp/campaments">
						<label for="start-date">Fecha de inicio</label>
						<input type="date" name="start-date" value="" placeholder="Fecha de inicio">
						<label for="end-date">Fecha de fin</label>
						<input type="date" name="end-date" placeholder="Fecha de fin">
						<label for="Nivel educativo">Nivel educativo</label>
						<select name="level" id="level">
							<option value="CHILD">Infantil</option>
							<option value="YOUTH">Juvenil</option>
							<option value="TEENAGER">Adolescente</option>
						</select>
						<label for="max-assistants">Asistentes maximos</label>
						<input type="number" name="max-assistants" placeholder="Asistentes maximos">	
						<input type="submit" value="Submit">
					</form>
				<% } 
			%>
		</main>
	</body>
</html>