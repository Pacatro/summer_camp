<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import ="java.util.*" %>
<%@ page import = "java.time.*"%>
<%@ page import = "java.time.temporal.ChronoUnit"%>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>
<%@ page import = "es.uco.pw.business.campament.CampamentDTO"%>
<%@ page import="es.uco.pw.business.managers.AssistantManager"%>
<%@ page import="es.uco.pw.business.factory.ParcialInscriptionDTO"%>
<%@ page import="es.uco.pw.business.managers.InscriptionsManager"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link href="/summer_camp/styles/loginView.css" rel="stylesheet" />
		<title>Cancelar inscripcion parcial</title>
	</head>
	<body>
		<div class="container">
			<header>
				<h1>Summer Camp<h1>
			</header>

			<main>
			<div class="login-section">
				<h1>Cancelar inscripcion parcial</h1>

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
						AssistantManager assis = new AssistantManager(sqlprop, configprop);
                    	ArrayList<CampamentDTO> campaments = assis.getCampaments(customerBean.getEmailUser());
						InscriptionsManager inscripcionManager = new InscriptionsManager(sqlprop, configprop);
    					ArrayList<Object> inscriptions = inscripcionManager.getAllByEmail(customerBean.getEmailUser());
					%>
						<table>
							<thead>
								<tr>
									<th>Campamento</th>
									<th>Precio</th>
								</tr>
							</thead>
							<tbody>
								<% for(Object o : inscriptions) {
									if(o instanceof ParcialInscriptionDTO) {
										ParcialInscriptionDTO parcialInscription = (ParcialInscriptionDTO) o; %>
										<tr>
											<td><%=parcialInscription.getIdCampament()%></td>
											<td><%=parcialInscription.getPrice()%></td>
										</tr> <%
									}
								} %>
							</tbody>
						</table>
						<br/>
						<form method="post" action="/summer_camp/deleteParcialInscription">
							<label for="camp-id" class="label-section">ID del campamento</label>
							<select name="camp-id" class="select">
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
							<input type="submit" value="Submit">
						</form>
					<% } 
				%>
			</div>
			</main>
		<div>
	</body>
</html>