<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.business.user.User,es.uco.pw.data.dao.UserDAO" %>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%
/* Posibles flujos:
	1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp
	2) customerBean no está logado:
		a) Hay parámetros en el request  -> procede de la vista 
		b) No hay parámetros en el request -> procede de otra funcionalidad o index.jsp
	*/
//Caso 1: Por defecto, vuelve al index
String nextPage = "../../index.jsp";
String mensajeNextPage = "";
//Caso 2
if (customerBean == null || customerBean.getEmailUser().equals("")) {
	String nameUser = request.getParameter("name");
	String emailUser = request.getParameter("email");

	//Caso 2.a: Hay parámetros -> procede de la VISTA
	if (nameUser != null) {
		//Se accede a bases de datos para obtener el usuario
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserByName(nameUser);

		//Se realizan todas las comprobaciones necesarias del dominio
		//Aquí sólo comprobamos que exista el usuario
		if (user != null && user.getEmail().equalsIgnoreCase(emailUser)) {
			// Usuario válido		
			CampamentDAO campamentDAO = new CampamentDAO();
            ArrayList<CampamentDTO> campaments = campamentDAO.getAll();
			customerBean.setCampaments(campaments);

%>
<jsp:setProperty property="emailUser" value="<%=emailUser%>" name="customerBean"/>
<jsp:setProperty property="campaments" value="<%=campaments%>" name="customerBean"/>
<%
		} else {
			// Usuario no válido
			nextPage = "../view/loginView.jsp";
			mensajeNextPage = "El usuario que ha indicado no existe o no es valido";
		}
	//Caso 2.b -> se debe ir a la vista por primera vez
	} else {
		nextPage = "../view/loginView.jsp";		
	}
}
%>

<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>