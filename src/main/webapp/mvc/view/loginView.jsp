<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Acceso</title>
	</head>
	<body>
		<%
		/* Posibles flujos:
			1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp (no debería estar aquí pero hay que comprobarlo)
			2) customerBean no está logado:
				a) Hay parámetros en el request  -> procede del controlador /con mensaje 
				b) No hay parámetros en el request -> procede del controlador /sin mensaje
		*/
		String nextPage = "../controller/loginController.jsp";
		String messageNextPage = request.getParameter("message");
		if (messageNextPage == null) messageNextPage = "";

		if (customerBean != null && !customerBean.getEmailUser().equals("")) {
			//No debería estar aquí -> flujo salta a index.jsp
			nextPage = "../../index.jsp";
		} else { %>
			<%= messageNextPage %><br/><br/>
			<h1>Acceso</h1>
			<form method="post" action="../controller/loginController.jsp">
				<label for="email">Email: </label>
				<input type="text" name="email" value=""><br/>
				<label for="password">Password: </label>
				<input type="text" name="password">	
				<br/>
				<input type="submit" value="Submit">
			</form>
		<% } %>

	</body>
</html>