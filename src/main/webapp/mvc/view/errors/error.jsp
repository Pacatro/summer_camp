<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error page</title>
    </head>
    <body>
        <head>
            <meta charset="UTF-8">
            <title>Summer Camp</title>
            <link href="../../../index.css" rel="stylesheet" />
        </head>
        <body>
            <%
                String messageNextPage = request.getParameter("message");
            %>

            <header>
                <h1>Summer Camp<h1>
            </header>
    
            <main>
                <div class="message">
                    <h1 class="error-message">Error: <%= messageNextPage %></h1>
                </div>

                <div class="buttons">
                    <button>
                        <a href="../../../index.jsp">Volver</a>
                    </button>
                </div>
            </main>
    
            <footer>
                <h3>Summer Camp<h3>
            </footer>
        </body>
    </body>
</html>