<%-- 
    Document   : login
    Created on : 4 déc. 2019, 14:21:29
    Author     : pedago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/WEB-INF/includes/header.jsp"/>
    </head>
    <body>
        <form action="<c:url value="/" />" method="POST"> <!-- l'action par défaut est l'URL courant, qui va rappeler la servlet -->
                login (untel) : <input name='loginParam'><br>
                password (ABCD): <input name='passwordParam' type='password'><br>
                <input type='submit' name='action' value='login'>
        </form>
        <h1>Hello World!</h1>
    </body>
</html>
