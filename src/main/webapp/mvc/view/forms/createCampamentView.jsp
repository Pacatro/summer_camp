<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"> 
        <link href="/summer_camp/styles/loginView.css" rel="stylesheet" />
		<title>Crear campamento</title>
	</head>
	<body>
		<div class="container">
			<header>
				<h1>Summer Camp<h1>
			</header>

			<main>
			<div class="login-section">
				<h1>Crear campamento</h1>

				<%
					String nextPage = "";
					String messageNextPage = request.getParameter("message");
					if (messageNextPage == null) messageNextPage = "";

					if(customerBean == null || customerBean.getEmailUser().equals("")) {
						nextPage = "/mvc/view/loginView.jsp";
						%>
							<jsp:forward page="<%=nextPage%>">
								<jsp:param value="<%=messageNextPage%>" name="message"/>
							</jsp:forward>
						<%
					} else if (customerBean.getType() == UserType.ASSISTANT) {
						nextPage = "/mvc/view/AssistantView.jsp";
						messageNextPage = "Bienvenido/a " + customerBean.getName();
						%>
							<jsp:forward page="<%=nextPage%>">
								<jsp:param value="<%=messageNextPage%>" name="message"/>
							</jsp:forward>
						<%
					} else { %>
						<form method="post" action="/summer_camp/campaments">
							<label for="start-date" class="label-section">Fecha de inicio</label>
							<input type="date" name="start-date" value="" placeholder="Fecha de inicio">
							<label for="end-date" class="label-section">Fecha de fin</label>
							<input type="date" name="end-date" placeholder="Fecha de fin">
							<label for="Nivel educativo" class="label-section">Nivel educativo</label>
							<select name="level" id="level" class="select">
								<option value="CHILD">Infantil</option>
								<option value="YOUTH">Juvenil</option>
								<option value="TEENAGER">Adolescente</option>
							</select>
							<br/>
							<input type="number" name="max-assistants" placeholder="Asistentes m&aacute;ximos">	
							<input type="submit" value="Submit">
						</form>
					<% } 
				%>
			</div>
			</main>
		</div>
	</body>
</html>