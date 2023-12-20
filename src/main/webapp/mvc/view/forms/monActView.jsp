<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>
<%@ page import ="java.util.*" %>
<%@ page import = "es.uco.pw.business.monitor.MonitorDTO"%>
<%@ page import = "es.uco.pw.business.activity.ActivityDTO"%>
<%@ page import = "es.uco.pw.business.managers.CampamentsManager"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/index.css" rel="stylesheet" />
		<title>Asociar monitor a actividades</title>
	</head>
	<body>
		<header>
			<h1>Summer Camp<h1>
		</header>

		<main>
			<h1>Asociar monitor a actividades</h1>

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
					<form method="post" action="/summer_camp/activityMonitor">
						<label for="act-id">Nombre de la actividad</label>
						<select name="act-id">
						<%
							CampamentsManager camp_man = new CampamentsManager(sqlprop, configprop);
							ArrayList<ActivityDTO> activities = camp_man.getAllActivities();
							for(int i = 0; i < activities.size(); i++){
						%>
								<option value="<%=activities.get(i).getname()%>"><%=activities.get(i).getname()%></option>
							<% } %>
						</select>
						<label for="mon-id">Nombre del monitor</label>
						<select name="mon-id">
						<%
							ArrayList<MonitorDTO> monitors = camp_man.getAllMonitorsNotEspecial();
							for(int i = 0; i < monitors.size(); i++){
						%>
								<option value="<%=monitors.get(i).getID()%>"><%=monitors.get(i).getName() + " " + monitors.get(i).getSurname()%></option>
							<% } %>
						</select>
						<input type="submit" value="Submit">
					</form>
				<% } 
			%>
		</main>
	</body>
</html>