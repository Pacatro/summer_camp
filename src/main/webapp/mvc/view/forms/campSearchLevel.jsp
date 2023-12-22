<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/loginView.css" rel="stylesheet" />
		<title>Buscar campamentos</title>
	</head>
	<body>
		<div class="container">
			<header>
				<h1>Summer Camp<h1>
			</header>

			<main>
			<div class="login-section">
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
						<form method="post" action="/summer_camp/availableCampsLevel">
							<label for="level" class="label-section">Nivel educativo</label>
							<select name="level" class="select">
								<option value="CHILD">Infantil</option>
								<option value="YOUTH">Juvenil</option>
								<option value="TEENAGER">Adolescente</option>
							</select>
							<input type="submit" value="Submit">
						</form>
					<% } 
				%>
			</div>	
			</main>
		</div>
	</body>
</html>