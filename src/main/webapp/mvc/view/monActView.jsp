<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/index.css" rel="stylesheet" />
		<title>Asociar monitor a actividades</title>
	</head>
	<body>
		<header>
			<h1>Summer Camp<h1>
		</header>

		<main>
			<h1>Asociar monitor a actividades</h1>

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
						<label for="act-id">Identificador de la actividad</label>
						<input type="number" name="act-id" value="" placeholder="Identificador de la actividad" min="0">
						<label for="mon-id">Identificador del monitor</label>
						<input type="number" name="mon-id" value="" placeholder="Identificador del monitor" min="0">
						<input type="submit" value="Submit">
					</form>
				<% } 
			%>
		</main>
	</body>
</html>