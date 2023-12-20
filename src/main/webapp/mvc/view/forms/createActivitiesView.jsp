<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>
<%@ page import ="java.util.*" %>
<%@ page import = "es.uco.pw.business.campament.CampamentDTO"%>
<%@ page import = "es.uco.pw.business.monitor.MonitorDTO"%>
<%@ page import = "es.uco.pw.business.managers.CampamentsManager"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/loginView.css" rel="stylesheet" />
		<title>Crear actividad</title>
	</head>
	<body>
		<div class="container">
			<header>
				<h1>Summer Camp<h1>
			</header>

			<main>
				<h1>Crear actividad</h1>

				<%
					String file = application.getInitParameter("sqlproperties");
					String file1 = application.getInitParameter("configproperties");
					java.io.InputStream myIO = application.getResourceAsStream(file);
					java.io.InputStream myIO1 = application.getResourceAsStream(file1);
					java.util.Properties sqlprop = new java.util.Properties();
					java.util.Properties configprop = new java.util.Properties();
					sqlprop.load(myIO);
					configprop.load(myIO1);

					String nextPage = "";
					String messageNextPage = request.getParameter("message");
					if (messageNextPage == null) messageNextPage = "";

					if(customerBean == null || customerBean.getEmailUser().equals("")) {
						nextPage = "/mvc/view/errors/error.jsp";
						messageNextPage = "Debes iniciar sesion primero.";
						%>
							<jsp:forward page="<%=nextPage%>">
								<jsp:param value="<%=messageNextPage%>" name="message"/>
							</jsp:forward>
						<%
					} else if (customerBean.getType() == UserType.ASSISTANT) {
						nextPage = "/mvc/view/errors/error.jsp";
						messageNextPage = "No estas autorizado para entrar en esta pagina.";
						%>
							<jsp:forward page="<%=nextPage%>">
								<jsp:param value="<%=messageNextPage%>" name="message"/>
							</jsp:forward>
						<%
					} else { %>
						<form method="post" action="/summer_camp/activities">
							<label for="name">Nombre de la actividad</label>
							<input type="text" name="name" value="" placeholder="Nombre de la actividad">
							<label for="Nivel educativo">Nivel educativo</label>
							<select name="level" id="level">
								<option value="CHILD">Infantil</option>
								<option value="YOUTH">Juvenil</option>
								<option value="TEENAGER">Adolescente</option>
							</select>
							<label for="schedule">Escoja el horario</label>
							<select name="schedule" id="schedule">
								<option value="MORNING">Mañanas</option>
								<option value="AFTERNOON">Tardes</option>
							</select>
							<label for="max-participants">Participantes maximos</label>
							<input type="number" name="max-participants" placeholder="Participantes maximos">
							<label for="num-monitors">Numero de monitores</label>
							<input type="number" name="num-monitors" placeholder="Numero de monitores">	
							<input type="submit" value="Submit">
						</form>
					<% } 
				%>
			</main>
		</div>
	</body>
</html>