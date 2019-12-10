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
        <p>${form.result}</p>
        <c:if test="${not empty sessionScope.userSession}">
            <p> VOUS ETES CONNECTE EN TANT QUE : ${sessionScope.userSession.username}</p>
        </c:if>
            
        <div id ="clientInfos"></div>
        
        <script id="clientTemplate" type="text/template">
            <div class="container">
                <div class="row">
                  <div class="col">
                    <ul class="list-group">
                        <li class="list-group-item active">Nom :</li>
                        <li class="list-group-item">Dapibus ac facilisis in</li>
                        <li class="list-group-item active">Morbi leo risus</li>
                        <li class="list-group-item">Porta ac consectetur ac</li>
                        <li class="list-group-item active">Vestibulum at eros</li>
                    </ul>
                  </div>
                  <div class="col">
                    <ul class="list-group">
                        <li class="list-group-item active">{{contact}}</li>
                        <li class="list-group-item">Dapibus ac facilisis in</li>
                        <li class="list-group-item active">Morbi leo risus</li>
                        <li class="list-group-item">Porta ac consectetur ac</li>
                        <li class="list-group-item active">Vestibulum at eros</li>
                    </ul>
                  </div>
                </div>
            </div>
        </script>
            <jsp:include page="/WEB-INF/includes/footer.jsp"/>
     <script><jsp:include page="/WEB-INF/scripts/processClientInfo.js"/></script>
    </body>
</html>
