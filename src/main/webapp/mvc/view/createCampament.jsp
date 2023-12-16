<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="../../styles/loginView.css" rel="stylesheet" />
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

				if (customerBean != null && !customerBean.getEmailUser().equals("")) { %>
					<h1>Campament</h1>
					<form method="post" action="/summer_camp/campaments">
						<input type="text" name="start-date" value="" placeholder="startdate">
						<input type="text" name="end-date" placeholder="enddate">	
						<input type="text" name="level" placeholder="level">
						<input type="text" name="max-assistants" placeholder="max">	
						<input type="submit" value="Submit">
					</form>
				<% } %>
		</main>

		<footer>
			<h3>Summer Camp<h3>
		</footer>
	</body>
</html>