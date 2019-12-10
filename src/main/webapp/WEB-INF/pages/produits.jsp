<%-- 
    Document   : home
    Created on : 4 déc. 2019, 08:57:24
    Author     : pedago
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <jsp:include page="/WEB-INF/includes/head.jsp"/>
        <title>Products</title>
        
    </head>
    <body>
        <jsp:include page="/WEB-INF/includes/header.jsp"/>
        <div id="productsTable"></div>
        <c:choose>
            <c:when test="${(not empty sessionScope.userSession)}" >
            <script id="productsTemplate" type="text/template">
                <div class="card">
                    <div class="card-body bg-light">
                      <h1 class="display-4">{{titre}}</h1>
                    </div>
                  </div>
                <table class = "table">
                <tr><th>Ref.</th>{{^cat}}<th>Catégorie</th>{{/cat}}<th>Nom</th><th>Prix unitaire</th><th>Disponible</th></tr>
                {{#produits}}
                    <tr><td>{{reference}}</td>{{^cat}}<td>{{categorie.libelle}}</td>{{/cat}}<td>{{nom}}</td><td>{{prix_unitaire}} €</td><td>{{dispo}}</td><td><button type="button" class="btn btn-secondary">Ajouter</button></td></tr>
                {{/produits}}
                </table>
            </script>                
            </c:when>
            <c:otherwise>
            <script id="productsTemplate" type="text/template">
                <div class="card">
                    <div class="card-body bg-light">
                      <h1 class="display-4">{{titre}}</h1>
                    </div>
                  </div>
                <table class = "table">
                <tr><th>Ref.</th>{{^cat}}<th>Catégorie</th>{{/cat}}<th>Nom</th><th>Prix unitaire</th><th>Disponible</th></tr>
                {{#produits}}
                    <tr><td>{{reference}}</td>{{^cat}}<td>{{categorie.libelle}}</td>{{/cat}}<td>{{nom}}</td><td>{{prix_unitaire}} €</td><td>{{dispo}}</td></tr>
                {{/produits}}
                </table>
            </script>   
            </c:otherwise>
        </c:choose>

    <script><jsp:include page="/WEB-INF/scripts/processProducts.js"/></script>
    </body>
</html>
