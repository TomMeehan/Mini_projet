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
                <div class="card my-4">
                    <div class="card-body bg-light text-center">
                      <h1 class="display-4">Mes commandes</h1>
                    </div>
                  </div>
                <c:forEach var="record" items="${commandes}">
                <div class="card my-4">
                    <div class="card-header">
                    <h4>Commande N°${record.numero}</h4>
                  </div>
                    <div class="card-body">
                      <h5 class="card-title my-4 border">Date de saisie : ${record.saisieLe}</h5>
                      <p class="card-text"><strong>Envoyée le :</strong>  ${record.envoyeeLe}</p>    
                      <p class="card-text"><strong>Destinataire :</strong>  ${record.destinataire}</p>
                      <p class="card-text"><strong>Adresse de livraison :</strong>  ${record.adresseLivraison}, ${record.code_postalLivrais} ${record.villeLivraison}, ${record.paysLivraison}</p>    
                      <p class="card-text"><strong>Frais de port :</strong> ${record.port} €</p>
                      <p class="card-text"><strong>Remise :</strong> ${record.remise} €</p>
                    </div>
                </div>
                </c:forEach>       
            </div>                    
        </c:if>
    </body>
</html>
