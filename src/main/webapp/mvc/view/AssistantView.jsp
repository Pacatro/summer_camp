<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="assistant" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pagina Asistente</title>
    </head>
    <body>
        <div class="welcome-message">
            <h2>Bienvenido, <%= assistant.getEmailUser() %></h2>
            <p>Fecha actual: <%= new java.util.Date() %></p>
        </div>

    </body>
</html>