<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>
<%@ page import ="java.util.*" %>
<%@ page import = "es.uco.pw.business.campament.CampamentDTO"%>
<%@ page import = "es.uco.pw.business.monitor.MonitorDTO"%>
<%@ page import = "es.uco.pw.business.activity.ActivityDTO"%>
<%@ page import = "es.uco.pw.business.managers.CampamentsManager"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/loginView.css" rel="stylesheet" />
		<title>Asociar monitor a campamentos</title>
	</head>
	<body>
		<div class="container">
			<header>
				<h1>Summer Camp<h1>
			</header>

			<main>
				<h1>Asociar monitor a campamentos</h1>
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
						ArrayList<MonitorDTO> monitors = camp_man.getAllMonitors();
						ArrayList<ActivityDTO> activities = camp_man.getAllActivities();
					%>

						<table>
							<thead>
								<th>Monitor</th>
								<th>Campamentos validos</th>
							</thead>
							<tbody>
								<%
								for(MonitorDTO mon: monitors){
									if(!mon.isEspecial()){
										for(ActivityDTO act: activities){
											ArrayList<MonitorDTO> aux_monitors = act.getMonitors();
											for(MonitorDTO aux_mon: aux_monitors){
												if(aux_mon.getID() == mon.getID()){
													for(CampamentDTO camp: campaments){
														ArrayList<ActivityDTO> aux_activities = camp.getActivities();
														for(ActivityDTO aux_act: aux_activities){
															if(aux_act.getname().equals(act.getname())){
								%>
																<tr>
																	<td><%=mon.getName() + " " + mon.getSurname()%></td>
																	<td><%=camp.getId()%></td>
																</tr>
								<%
															}
														}
													}
												}
											}
										}
									}
								}
								%>
							</tbody>
						</table>
						<br/>
				<div class="login-section">
						<form method="post" action="/summer_camp/campamentMonitor">
							<label for="camp-id" class="label-section">Identificador del campamento</label>
							<select name="camp-id" class="select">
							<%
								for(int i = 0; i < campaments.size(); i++){
							%>
									<option value="<%=campaments.get(i).getId()%>"><%=campaments.get(i).getId()%></option>
								<% } %>
							</select>
							<br/>
							<label for="mon-id" class="label-section">Nombre del monitor</label>
							<select name="mon-id" class="select">
							<%
								for(int i = 0; i < monitors.size(); i++){
							%>
									<option value="<%=monitors.get(i).getID()%>"><%=monitors.get(i).getName() + " " + monitors.get(i).getSurname()%></option>
								<% } %>
							</select>
							<input type="submit" value="Submit">
						</form>
					<% } %>
				</div>
			</main>
		</div>
	</body>
</html>