<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.activity.ActivityDTO" %>
<%@ page import="es.uco.pw.business.common.level.Level" %>
<%@ page import="es.uco.pw.business.common.schedule.Schedule" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prueba de MVC</title>
</head>
<body>

    <h1>Hola mundo</h1>

    <%
		ArrayList<String> cars = new ArrayList<String>();
		cars.add("Volvo");
		cars.add("BMW");
		cars.add("Ford");
		cars.add("Mazda");
		ActivityDTO activity = new ActivityDTO("Paco", Level.CHILD, Schedule.MORNING, 100, 10);
    %>

	<%for (int i = 0; i < cars.size(); i++) {%>
		<h1><%= cars.get(i) %></h1>
    <%}%>

	<h1><%= activity %></h1>

</body>
</html>
