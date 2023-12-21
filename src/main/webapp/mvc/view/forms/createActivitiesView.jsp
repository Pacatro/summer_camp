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
			<div class="login-section">
				<h1>Crear actividad</h1> 

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
					} else if (customerBean.getType() == UserType.ASSISTANT) {
						nextPage = "/mvc/view/AssistantView.jsp";
						messageNextPage = "Bienvenido/a " + customerBean.getName();
						%>
							<jsp:forward page="<%=nextPage%>">
								<jsp:param value="<%=messageNextPage%>" name="message"/>
							</jsp:forward>
						<%
					} else { %>
						<form method="post" action="/summer_camp/activities">
							<input type="text" name="name" value="" placeholder="Nombre de la actividad">
							<label for="Nivel educativo" class="label-section">Nivel educativo</label>
							<select name="level" id="level" class="select">
								<option value="CHILD">Infantil</option>
								<option value="YOUTH">Juvenil</option>
								<option value="TEENAGER">Adolescente</option>
							</select>
							<br/>
							<label for="schedule" class="label-section">Escoja el horario</label>
							<select name="schedule" id="schedule" class="select">
								<option value="MORNING">Mañanas</option>
								<option value="AFTERNOON">Tardes</option>
							</select>
							<input type="number" name="max-participants" placeholder="Participantes m&aacute;ximos">
							<input type="number" name="num-monitors" placeholder="N&uacute;mero de monitores">	
							<input type="submit" value="Submit">
						</form>
					<% } 
				%>
			</div>
			</main>
		</div>
	</body>
</html>