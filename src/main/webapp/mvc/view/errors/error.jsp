<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/summer_camp/styles/loginView.css" rel="stylesheet" />
        <title>Error page</title>
    </head>
    <body>
        <div class="container">
            <%
                String messageNextPage = request.getParameter("message");

                if(messageNextPage == null) {
                    messageNextPage = (String)session.getAttribute("message");
                }
            %>

            <header>
                <h1>Summer Camp<h1>
            </header>

            <main>
                <div class="message">
                    <% if(messageNextPage == null) { %>
                        <h1 class="error-message">Ha ocurrido un error inesperado.</h1>
                    <% } else { %>
                        <h1 class="error-message">Error: <%= messageNextPage %></h1>
                    <% } %>
                </div>

                <div class="buttons">
                    <button>
                        <a href="/summer_camp">Volver</a>
                    </button>
                </div>
            </main>
        </div>
    </body>
</html>