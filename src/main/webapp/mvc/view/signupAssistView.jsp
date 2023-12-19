<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page import = "es.uco.pw.business.common.userType.UserType"%>

<%
    String nextPage = "../controller/signupAssistController.jsp";
    String messageNextPage = request.getParameter("message");
    if (messageNextPage == null) messageNextPage = "";

    // Validación para asegurarse de que el DNI no sea negativo
    int dni = 0;
    try {
        dni = Integer.parseInt(request.getParameter("dni"));
        if (dni < 0) {
            messageNextPage = "El DNI no puede ser un número negativo.";
        }
    } catch (NumberFormatException e) {}
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="/summer_camp/styles/loginView.css" rel="stylesheet" />
    <title>Registro asistente</title>
</head>
<body>
    <div class="container">
        <header>
            <h1>Summer Camp</h1>
        </header>

		<main>
			<%
				String nextPage = "../controller/signupAssistController.jsp";
				String messageNextPage = request.getParameter("message");
				if (messageNextPage == null) messageNextPage = "";

				if(customerBean == null || customerBean.getEmailUser().equals("")){
					nextPage = "/index.jsp";
				}else if(customerBean.getType() == UserType.ADMIN){
					nextPage = "Adminview.jsp";
					messageNextPage = "Bienvenido/a" + customerBean.getName();
			%>
					<jsp:forward page="<%=nextPage%>">
						<jsp:param value="<%=messageNextPage%>" name="message"/>
					</jsp:forward>
			<%
				} else {
			%>
            
            <%= messageNextPage %>
            <br/>
            <br/>
            <div class="login-section">
                <h1>Complete el resto de la información</h1>
                <form method="post" action="../controller/signupAssistController.jsp">
                    <input type="number" name="dni" value="" placeholder="DNI">
                    <input type="text" name="surname" value="" placeholder="Apellido">
                    <label for="birthdate" class="label-section">Fecha de nacimiento: </label>
                    <input type="date" name="birthdate">
                    <label for="atention" class="label-section">¿Necesita atención especial? </label>
                    <select name="atention" class="select">
                        <option value="yes">Si</option>
                        <option value="no">No</option>
                    </select>
                    <br/>
                    <input type="submit" value="Submit">
                </form>
            </div>
        </main>
    </div>
</body>
</html>
        