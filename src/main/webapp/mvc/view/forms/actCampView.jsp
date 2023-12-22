<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>
<%@ page import ="java.util.*" %>
<%@ page import = "es.uco.pw.business.campament.CampamentDTO"%>
<%@ page import = "es.uco.pw.business.activity.ActivityDTO"%>
<%@ page import = "es.uco.pw.business.managers.CampamentsManager"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/loginView.css" rel="stylesheet" />
		<title>Asociar actividad a campamentos</title>
	</head>
	<body>
		<div class="container">
			<header>
				<h1>Summer Camp<h1>
			</header>

			<main>
			<div class="login-section">
				<h1>Asociar actividad a campamentos</h1>

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
					} else {
						CampamentsManager camp_man = new CampamentsManager(sqlprop, configprop);
						ArrayList<CampamentDTO> campaments = camp_man.getAllCampaments();
						ArrayList<ActivityDTO> activities = camp_man.getAllActivities();
					%>

						<table>
							<thead>
								<th>Id Campamento</th>
								<th>Nivel educativo</th>
							</thead>
							<tbody>
								<%
									for(CampamentDTO c: campaments){
								%>
										<tr>
											<td><%=c.getId()%></td>
											<td><%=c.getLevel()%></td>
										</tr>
								<%
									}
								%>
							</tbody>
						</table>
						<br/>
						<table>
							<thead>
								<th>Actividad</th>
								<th>Nivel educativo</th>
							</thead>
							<tbody>
								<%
									for(ActivityDTO a: activities){
								%>
									<tr>
										<td><%=a.getname()%></td>
										<td><%=a.getLevel()%></td>
									</tr>
								<%
									}
								%>
							</tbody>
						</table>

						<form method="post" action="/summer_camp/activityCampament">
							<label for="camp-id" class="label-section">Identificador del campamento</label>
							<select name="camp-id" class="select">
							<%
								for(CampamentDTO c: campaments){
							%>
									<option value="<%=c.getId()%>"><%=c.getId()%></option>
								<% } %>
							</select>
							<br/>
							<label for="act-id" class="label-section">Nombre de la actividad</label>
							<select name="act-id" class="select">
							<%
								for(ActivityDTO a: activities){
							%>
									<option value="<%=a.getname()%>"><%=a.getname()%></option>
								<% } %>
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