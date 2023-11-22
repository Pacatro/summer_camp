<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel de Administrador</title>
</head>
<body>

    <c:choose>
        <c:when test="${not empty customerBean and customerBean.type2 == 'ADMIN'}">
            <!-- Contenido para el administrador -->
            <h2>Lista de Campamentos</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID del Campamento</th>
                        <th>Número de Personas Inscritas</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Itera sobre la lista de campamentos y muestra la información -->
                    <c:forEach var="campamento" items="${campaments}">
                        <tr>
                            <td>${campamento.id}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <!-- Si no es administrador, redirige o muestra un mensaje de error -->
            <c:redirect url="/pagina-de-error.jsp" />
        </c:otherwise>
    </c:choose>

</body>
</html>