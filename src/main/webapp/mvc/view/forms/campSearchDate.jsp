<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/loginView.css" rel="stylesheet" />
		<title>Realizar inscripcion completa</title>
	</head>
	<body>
		<div class="container">
			<header>
				<h1>Summer Camp<h1>
			</header>

			<main>
				<h1>Buscar campamentos</h1>

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
					} else {
                %>
						<form method="post" action="/summer_camp/availableCampsDate">
							<label for="start-date">Fecha de inicio</label>
							<input type="date" name="start-date">
							<label for="end-date">Fecha de fin</label>
                            <input type="date" name="end-date">
							<input type="submit" value="Submit">
						</form>
					<% } 
				%>
			</main>
		</div>
	</body>
</html>