<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/index.css" rel="stylesheet" />
		<title>Registrarse</title>
	</head>
	<body>
		<header>
			<h1>Summer Camp<h1>
		</header>

		<main>
			<%
				String nextPage = "../controller/signupController.jsp";
				String messageNextPage = request.getParameter("message");
				if (messageNextPage == null) messageNextPage = "";

				if (customerBean != null && !customerBean.getEmailUser().equals("")) {
					if(customerBean.getType() == UserType.ASSISTANT){
						nextPage = "AssistantView.jsp";
						messageNextPage = "Bienvenido/a" + customerBean.getName();
					}else{
						nextPage = "Adminview.jsp";
						messageNextPage = "Bienvenido/a" + customerBean.getName();
					}
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
					<h1>Registro</h1>
					<form method="post" action="../controller/signupController.jsp">
						<label for="name">Nombre: </label>
							<input type="text" name="name" value="" placeholder="Nombre">
						<label for="email">Email: </label>
							<input type="text" name="email" placeholder="Email">
						<label for="password">Contraseña: </label>
							<input type="text" name="password" placeholder="Contraseña">
						<label for="type">Tipo de usuario: </label>
							<select name="type">
								<option value="ASSISTANT">Asistente</option>
								<option value="ADMIN">Administrador</option>
						<br/>
						<input type="submit" value="Submit">
					</form>
				<% } %>
		</main>
	</body>
</html>