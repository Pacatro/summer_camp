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
			<h1>Crear campamento</h1>

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
				} else if (customerBean != null && customerBean.getType() == UserType.ASSISTANT) {
					nextPage = "/mvc/view/AssistantView.jsp";
					messageNextPage = "Bienvenido/a " + customerBean.getName();
					%>
						<jsp:forward page="<%=nextPage%>">
							<jsp:param value="<%=messageNextPage%>" name="message"/>
						</jsp:forward>
					<%
				} else if(customerBean != null && customerBean.getType() == UserType.ADMIN) { %>
					<form method="post" action="/summer_camp/campaments">
						<input type="text" name="start-date" value="" placeholder="startdate">
						<input type="text" name="end-date" placeholder="enddate">	
						<input type="text" name="level" placeholder="level">
						<input type="text" name="max-assistants" placeholder="max">	
						<input type="submit" value="Submit">
					</form>
				<% } 
			%>
		</main>
	</body>
</html>