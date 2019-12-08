<%-- 
    Document   : clientPage
    Created on : Dec 8, 2019, 3:17:55 PM
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page client</title>
        <jsp:include page="/WEB-INF/includes/header.jsp"/>
    </head>
    <body>
        <p>${form.result}</p>
        <c:if test="${not empty sessionScope.userSession}">
            <p> VOUS ETES CONNECTE EN TANT QUE : ${sessionScope.userSession.username}</p>
        </c:if>
    <script src=>"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.8.1/mustache.min.js"></script>
    </body>
</html>
