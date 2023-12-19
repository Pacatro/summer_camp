<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/loginView.css" rel="stylesheet" />
		<title>Registrarse</title>
	</head>
	<body>
		<div class="container">
			<header>
				<h1>Summer Camp<h1>
			</header>

			<main>
				<%
					String nextPage = "../controller/signupController.jsp";
					String messageNextPage = request.getParameter("message");
					if (messageNextPage == null) messageNextPage = "";

					if (customerBean != null && !customerBean.getEmailUser().equals("")) {
						if(customerBean.getType() == UserType.ASSISTANT)
							nextPage = "AssistantView.jsp";
						else
							nextPage = "Adminview.jsp";
				%>
					<jsp:forward page="<%=nextPage%>">
						<jsp:param value="<%=messageNextPage%>" name="message"/>
					</jsp:forward>
				<%
					} else {
						%>

						<%= messageNextPage %>
						<br/>
						<br/>
						<div class="login-section">
						<h1>Registro</h1>
						<form method="post" action="../controller/signupController.jsp">
								<input type="text" name="name" value="" placeholder="Nombre">
								<input type="text" name="email" value="" placeholder="Correo">
								<input type="text" name="password" value="" placeholder="Contraseña">
							<label for="type" class="label-section">Tipo de usuario: </label>
								<select name="type" class=select>
									<option value="ASSISTANT">Asistente</option>
									<option value="ADMIN">Administrador</option>
							<br/>
							<input type="submit" value="Submit">
						</form>
					<% } %>
						</div>
			</main>
		</div>
	</body>
</html>