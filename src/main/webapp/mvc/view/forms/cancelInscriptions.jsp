<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>
<%@ page import ="java.util.*" %>
<%@ page import = "es.uco.pw.business.campament.CampamentDTO"%>
<%@ page import = "es.uco.pw.business.managers.CampamentsManager"%>
<%@ page import = "java.time.*"%>
<%@ page import = "java.time.temporal.ChronoUnit"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/loginView.css" rel="stylesheet" />
		<title>Realizar inscripcion completa</title>
	</head>
	<body>
		<div class="container">
			<header>
				<h1>Summer Camp<h1>
			</header>

			<main>
				<h1>Realizar inscripcion completa</h1>

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
					} else { 
						CampamentsManager camp_man = new CampamentsManager(sqlprop, configprop);
						ArrayList<CampamentDTO> campaments = camp_man.getAllCampaments();
					%>
						<form method="post" action="/summer_camp/completeInscription">
							<label for="assis-id">DNI</label>
							<input type="number" name="assis-id" value="" placeholder="DNI" min="0">
							<label for="camp-id">ID del campamento</label>
							<select name="camp-id">
							<%
								for(int i = 0; i < campaments.size(); i++){
									if(ChronoUnit.DAYS.between(LocalDate.now(), campaments.get(i).getInitDate()) > 0){
							%>
										<option value="<%=campaments.get(i).getId()%>"><%=campaments.get(i).getId()%></option>
							<%
									}
								}
							%>
							</select>
							<label for="schedule">Escoja el horario</label>
							<select name="schedule" id="schedule">
								<option value="MORNING">Mañanas</option>
								<option value="AFTERNOON">Tardes</option>
							</select>
							<input type="submit" value="Submit">
						</form>
					<% } 
				%>
			</main>
		<div>
	</body>
</html>