<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="../../styles/index.css" rel="stylesheet" />
		<title>Acceso</title>
	</head>
	<body>
		<header>
			<h1>Summer Camp<h1>
		</header>

		<main>
			<%
				String nextPage = "../controller/loginController.jsp";
				String messageNextPage = request.getParameter("message");
				if (messageNextPage == null) messageNextPage = "";

				System.out.println(customerBean.getEmailUser());

				if (customerBean != null && !customerBean.getEmailUser().equals("")) {
					nextPage = "../../index.html";
				} else { %>
					<h1>Acceso</h1>
					<form method="post" action="../controller/loginController.jsp">
						<input type="text" name="email" value="" placeholder="Correo">
						<input type="text" name="password" placeholder="Contraseña">	
						<input type="submit" value="Submit">
					</form>
				<% } %>
		</main>

		<footer>
			<h3>Summer Camp<h3>
		</footer>
	</body>
</html>