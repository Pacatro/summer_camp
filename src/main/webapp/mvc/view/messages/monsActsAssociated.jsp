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
				String nextPage = "";
				String messageNextPage = "";

				if(customerBean != null && !customerBean.getEmailUser().equals("")){

					if(customerBean.getType() == UserType.ASSISTANT){
						nextPage = "/summer_camp/mvc/view/AssistantView.jsp";
                        %>
                            <jsp:forward page="<%=nextPage%>">
                                <jsp:param value="<%=messageNextPage%>" name="message"/>
                            </jsp:forward>
                        <%
					}else{
						nextPage = "/summer_camp/mvc/view/Adminview.jsp";
					}
				}
			%>

            <h1>El monitor ha sido asociado a la actividad correctamente</h1>

			<button>
				<a href="<%=nextPage%>">Volver a página principal</a>
			</button>
		</main>
	</body>
</html>
