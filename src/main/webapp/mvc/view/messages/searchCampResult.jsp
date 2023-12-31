<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>
<%@ page import = "java.util.*" %>
<%@ page import = "es.uco.pw.business.common.level.Level" %>
<%@ page import = "es.uco.pw.business.campament.CampamentDTO" %>
<%@ page import = "es.uco.pw.business.activity.ActivityDTO" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="/summer_camp/styles/loginView.css" rel="stylesheet" />
		<title>Summer Camp</title>
	</head>
	<body>
		<div class="container">
			<header>
				<h1>Summer Camp<h1>
			</header>

			<main>
				<%
					String nextPage = "";
					String messageNextPage = "";

					if(customerBean != null && !customerBean.getEmailUser().equals("")){
						if(customerBean.getType() == UserType.ASSISTANT){
							nextPage = "/summer_camp/mvc/view/AssistantView.jsp";
						}else{
							nextPage = "/summer_camp/mvc/view/Adminview.jsp";
						}
					}
				%>


				<%
				ArrayList<CampamentDTO> campaments = (ArrayList<CampamentDTO>)session.getAttribute("campaments");
				boolean hasChild = false, hasYouth = false, hasTeen = false;

				for(int i = 0; i < campaments.size() && (!hasChild || !hasYouth || !hasTeen); i++){
					if(!hasChild && campaments.get(i).getLevel() == Level.CHILD){
						hasChild = true;
					}else if(!hasYouth && campaments.get(i).getLevel() == Level.YOUTH){
						hasYouth = true;
					}else if(!hasTeen && campaments.get(i).getLevel() == Level.TEENAGER){
						hasTeen = true;
					}
				}

				if(hasChild || hasYouth || hasTeen){
				%>
					<h1>Resultado de la b&uacute;squeda:</h1>
				<%
				}else{
				%>
					<h1>No se encontraron resultados</h1>
				<%	
				}

				if(hasChild){
					%>
					<h2>Campamentos Infantiles</h2>

					<table>
						<thead>
							<th>Id</th>
							<th>Fecha de inicio</th>
							<th>Fecha de fin</th>
							<th>Actividades</th>
						</thead>
						<tbody>
							<%
							for(CampamentDTO c: campaments){
								if(c.getLevel() == Level.CHILD){
							%>
									<tr>
										<td><%=c.getId()%></td>
										<td><%=c.getInitDate()%></td>
										<td><%=c.getFinalDate()%></td>
										<%
										ArrayList<ActivityDTO> activities = c.getActivities();
										String actString = "";
										for(int i = 0; i < activities.size()-1; i++){
												actString += (activities.get(i).getname() + ", ");
										}
										if(activities.size() > 0)
											actString += activities.get(activities.size()-1).getname();
										%>
										<td><%=actString%></td>
									</tr>
							<%
								}
							}
							%>
						</tbody>
					</table>
				<%
				} 
				
				if(hasYouth){
				%>

					<h2>Campamentos Juveniles</h2>

					<table>
						<thead>
							<th>Id</th>
							<th>Fecha de inicio</th>
							<th>Fecha de fin</th>
							<th>Actividades</th>
						</thead>
						<tbody>
							<%
							for(CampamentDTO c: campaments){
								if(c.getLevel() == Level.YOUTH){
							%>
									<tr>
										<td><%=c.getId()%></td>
										<td><%=c.getInitDate()%></td>
										<td><%=c.getFinalDate()%></td>
										<%
										ArrayList<ActivityDTO> activities = c.getActivities();
										String actString = "";
										for(int i = 0; i < activities.size()-1; i++){
											actString += (activities.get(i).getname() + ", ");
										}
										if(activities.size() > 0)
											actString += activities.get(activities.size()-1).getname();
										%>
										<td><%=actString%></td>
									</tr>
							<%
								}
							}
							%>
						</tbody>
					</table>
				<%
				}

				if(hasTeen){
				%>

					<h2>Campamentos para Adolescentes</h2>

					<table>
						<thead>
							<th>Id</th>
							<th>Fecha de inicio</th>
							<th>Fecha de fin</th>
							<th>Actividades</th>
						</thead>
						<tbody>
							<%
							for(CampamentDTO c: campaments){
								if(c.getLevel() == Level.TEENAGER){
							%>
									<tr>
										<td><%=c.getId()%></td>
										<td><%=c.getInitDate()%></td>
										<td><%=c.getFinalDate()%></td>
										<%
										ArrayList<ActivityDTO> activities = c.getActivities();
										String actString = "";
										for(int i = 0; i < activities.size()-1; i++){
											actString += (activities.get(i).getname() + ", ");
										}
										if(activities.size() > 0)
											actString += activities.get(activities.size()-1).getname();
										%>
										<td><%=actString%></td>
									</tr>
							<%
								}
							}
							%>
						</tbody>
					</table>
				<%
				}
				%>
				<br/>
				<div class="assis-buttons">
					<button class="buts">
						<a href="<%=nextPage%>">Volver a página principal</a>
					</button>
				</div>
			</main>
		</div>
	</body>
</html>
