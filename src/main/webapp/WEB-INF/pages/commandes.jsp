<%-- 
    Document   : commandes
    Created on : Dec 12, 2019, 7:23:25 PM
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="/WEB-INF/includes/head.jsp"/>
        <title>Mes commandes</title>
        
    </head>
    <body>
       <jsp:include page="/WEB-INF/includes/header.jsp"/>
       <c:if test="${not empty sessionScope.userSession}">
            <div class="container">
                <div id="commandesData"></div>
            </div>
            <script id="commandesTemplate" type="text/template">
                <div class="card my-4">
                    <div class="card-body bg-light">
                      <h1 class="display-4">Mes commandes</h1>
                    </div>
                  </div>
                {{#commandes}}
                <div class="card my-4">
                    <div class="card-header">
                    <h4>Commande N°{{numero}}</h4>
                  </div>
                    <div class="card-body">
                      <h5 class="card-title">Date de saisie : {{saisie_le}}</h5>
                      <p class="card-text"><strong>Destinataire :</strong>  {{Destinataire}}</p>     
                      <p class="card-text"><strong>Frais de port :</strong> {{port}} €</p>
                      <p class="card-text"><strong>Remise :</strong> {{remise}} €</p>
                    </div>
                </div>
                {{/commandes}}       
            </script>   
        </c:if>
       <script><jsp:include page="/WEB-INF/scripts/processCommandes.js"/></script> 
    </body>
</html>
