<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="/summer_camp/styles/index.css" rel="stylesheet" />
		<title>Summer Camp</title>
	</head>
	<body>
		<header>
			<h1>Summer Camp<h1>
		</header>

		<main>

			<%
				if(customerBean != null || !customerBean.getEmailUser().equals("")){
					String nextPage = "";
					String messageNextPage = "";

					if(customerBean.getType() == UserType.ASSISTANT){
						nextPage = "./mvc/view/AssistantView.jsp";
					}else{
						nextPage = "./mvc/view/Adminview.jsp";
					}
			%>
				<jsp:forward page="<%=nextPage%>">
					<jsp:param value="<%=messageNextPage%>" name="message"/>
				</jsp:forward>
			<%
				}
			%>

			<button>
				<a href="/summer_camp/mvc/view/loginView.jsp">Acceder</a>
			</button>
			<button>
				<a href="/summer_camp/mvc/view/signupView.jsp">Registrarse</a>
			</button>
		</main>

		<footer>
			<h3>Summer Camp<h3>
		</footer>
	</body>
</html>
