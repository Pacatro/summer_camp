<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>

<!DOCTYPE html>
<html>
		<head>
			<meta charset="UTF-8">
			<link href="/summer_camp/styles/loginView.css" rel="stylesheet" />
			<title>Acceso</title>
		</head>
		<body>
		<div class="container">
			<header>
				<h1>Summer Camp<h1>
			</header>

			<main>
				<%
					String nextPage = "../controller/loginController.jsp";
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
						
					} else { %>
					<div class="login-section">
							<h1>Acceso</h1>
							<form method="post" action="../controller/loginController.jsp">
								<input type="text" name="email" value="" placeholder="Correo">
								<input type="password" name="password" placeholder="Contraseña">	
								<input type="submit" value="Submit">
							</form>
						<% } %>
					</div>
			</main>
		</div>
		</body>
</html>