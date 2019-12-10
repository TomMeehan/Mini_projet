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
            <style>
                .row.vdivide [class*='col-']:not(:last-child):after {
                background: #e0e0e0;
                width: 1px;
                content: "";
                display:block;
                position: absolute;
                top:0;
                bottom: 0;
                right: 0;
                min-height: 25px;
                }
            </style>
        <div id ="clientInfos"></div>
        
        <script id="clientTemplate" type="text/template">
           Nom : {{contact}}
        </script>
            <jsp:include page="/WEB-INF/includes/footer.jsp"/>
     <script><jsp:include page="/WEB-INF/scripts/processClientInfo.js"/></script>
    </body>
</html>
