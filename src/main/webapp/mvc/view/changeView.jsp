<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/index.css" rel="stylesheet" />
		<title>Modificar datos</title>
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

				if (customerBean == null || customerBean.getEmailUser().equals("")) {
					nextPage = "/index.jsp";
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
					<h1>Modificar datos</h1>
					<form method="post" action="../controller/changeController.jsp">
						<label for="name">Nombre: </label>
							<input type="text" name="name" value="">
						<label for="password">Contraseña: </label>
							<input type="text" name="password">
                    <%
                        if(customerBean.getType() == UserType.ASSISTANT){
                    %>
                        <label for="surname">Apellidos: </label>
                            <input type="text" name="surname">
                        <label for="birthdate">Fecha de nacimiento: </label>
                            <input type="date" name="birthdate">
                        <label for="atention">¿Necesita atencion especial?: </label>
                            <select name="atention">
                                <option value="yes">Si</option>
                                <option value="no">No</option>
                    <%
                        }
                    %>
						<br/>
						<input type="submit" value="Submit">
					</form>
				<% } %>
		</main>
	</body>
</html>