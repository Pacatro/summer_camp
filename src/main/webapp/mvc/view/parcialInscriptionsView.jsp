<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/loginView.css" rel="stylesheet" />
		<title>Realizar inscripcion parcial</title>
	</head>
	<body>
		<header>
			<h1>Summer Camp<h1>
		</header>

		<main>
			<h1>Realizar inscripcion parcial</h1>

			<%
				String nextPage = "";
				String messageNextPage = request.getParameter("message");
				if (messageNextPage == null) messageNextPage = "";

				if(customerBean == null || customerBean.getEmailUser().equals("")) {
					nextPage = "/mvc/view/errors/error.jsp";
					messageNextPage = "No estas autorizado para entrar en esta pagina.";
					%>
						<jsp:forward page="<%=nextPage%>">
							<jsp:param value="<%=messageNextPage%>" name="message"/>
						</jsp:forward>
					<%
				} else { %>
					<form method="post" action="/summer_camp/parcialInscription">
						<label for="assis-id">DNI</label>
						<input type="number" name="assis-id" placeholder="DNI" min="0">
						<label for="camp-id">ID del campamento</label>
						<input type="number" name="camp-id" placeholder="ID del campamento" min="0">
						<input type="submit" value="Submit">
					</form>
				<% } 
			%>
		</main>
	</body>
</html>