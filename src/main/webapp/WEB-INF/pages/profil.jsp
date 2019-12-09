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
            
        <div id ="clientInfos"></div>
        
        <script id="clientTemplate" type="text/template">
            <p>VOTRE ADRESSE : {{adresse}}</p>
        </script>
     <script><jsp:include page="/WEB-INF/scripts/processClientInfo.js"/></script>
    </body>
</html>
