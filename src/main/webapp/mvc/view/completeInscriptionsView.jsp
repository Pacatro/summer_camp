<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/index.css" rel="stylesheet" />
		<title>Acceso</title>
	</head>
	<body>
		<header>
			<h1>Summer Camp<h1>
		</header>

		<main>
			<h1>Realizar inscripcion completa</h1>

			<%
				String nextPage = "";
				String messageNextPage = request.getParameter("message");
				if (messageNextPage == null) messageNextPage = "";

				if(customerBean == null) {
					nextPage = "/index.jsp";
					%>
						<jsp:forward page="<%=nextPage%>">
							<jsp:param value="<%=messageNextPage%>" name="message"/>
						</jsp:forward>
					<%
				} else { %>
					<form method="post" action="/summer_camp/completeInscription">
						<label for="assis-id">DNI</label>
						<input type="number" name="assis-id" value="" placeholder="DNI">
						<label for="camp-id">ID del campamento</label>
						<input type="number" name="camp-id" placeholder="ID del campamento">	
						<label for="schedule">Escoja el horario</label>
						<select name="schedule" id="schedule">
							<option value="MORNING">Mañanas</option>
							<option value="AFTERNOON">Tardes</option>
						</select>
						<input type="submit" value="Submit">
					</form>
				<% } 
			%>
		</main>
	</body>
</html>