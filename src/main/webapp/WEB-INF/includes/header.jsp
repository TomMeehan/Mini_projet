<%-- 
    Document   : header
    Created on : 4 déc. 2019, 09:30:06
    Author     : pedago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="home">Comptoirs</a>   
            <form class="form-inline">
                <button class="btn btn-outline-success" type="button">Login</button>
            </form>
        </nav>
    </body>
    <jsp:include page="bootstrap.jsp"/>  
</html>
