<%-- 
    Document   : clientPage
    Created on : Dec 8, 2019, 3:17:55 PM
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="/WEB-INF/includes/head.jsp"/>
        <title>Page client</title>
        
    </head>
    <body>
        <jsp:include page="/WEB-INF/includes/header.jsp"/>
        
        <c:if test="${not empty sessionScope.userSession}">
            <p> VOUS ETES CONNECTE EN TANT QUE : ${sessionScope.userSession.username}</p>
        </c:if>
            
        <div id ="clientInfos"></div>
        
        <script id="clientTemplate" type="text/template">
            <table class="table table-bordered">
                <tbody>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Nom</th>
                      <td>{{contact}}</td>
                    </tr>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Societe</th>
                      <td>{{contact}}</td>
                    </tr>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Societe</th>
                      <td>{{contact}}</td>
                    </tr>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Societe</th>
                      <td>{{contact}}</td>
                    </tr>
                </tbody>
            </table>
        </script>
     <script><jsp:include page="/WEB-INF/scripts/processClientInfo.js"/></script>
    </body>
</html>
