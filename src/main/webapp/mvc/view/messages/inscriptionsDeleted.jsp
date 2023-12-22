<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>

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

            <h1>La inscripcion ha sido eliminada correctamente</h1>

			<button class="buts">
				<a href="<%=nextPage%>">Volver a página principal</a>
			</button>
		</main>
	</div>
	</body>
</html>
