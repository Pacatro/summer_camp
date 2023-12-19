<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/index.css" rel="stylesheet" />
		<title>Registro asistente</title>
	</head>
	<body>
		<header>
			<h1>Summer Camp<h1>
		</header>

		<main>
			<%
				String nextPage = "../controller/signupAssistController.jsp";
				String messageNextPage = request.getParameter("message");
				if (messageNextPage == null) messageNextPage = "";

				if(customerBean == null || customerBean.getEmailUser().equals("")){
					nextPage = "/index.jsp";
				}else if(customerBean.getType() == UserType.ADMIN){
					nextPage = "Adminview.jsp";
					messageNextPage = "Bienvenido/a" + customerBean.getName();
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
					<h1>Complete el resto de la información</h1>
					<form method="post" action="../controller/signupAssistController.jsp">
						<label for="dni">DNI: </label>
							<input type="number" name="dni" placeholder="DNI">
						<label for="surname">Apellidos: </label>
							<input type="text" name="surname" placeholder="Apellidos">
						<label for="birthdate">Fecha de nacimiento: </label>
							<input type="date" name="birthdate">
						<label for="atention">¿Necesita atencion especial?: </label>
							<select name="atention">
								<option value="no">No</option>
								<option value="yes">Si</option>
						<br/>
						<input type="submit" value="Submit">
					</form>
			<%  }  %>
		</main>
	</body>
</html>