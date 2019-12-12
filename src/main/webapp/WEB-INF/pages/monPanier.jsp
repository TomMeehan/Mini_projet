<%-- 
    Document   : monPanier
    Created on : Dec 11, 2019, 1:20:06 PM
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="/WEB-INF/includes/head.jsp"/>
        <title>Mon Panier</title>
        <link rel="stylesheet" type="text/css" href ="css/profil.css"/>
        
    </head>
    <body>
        <jsp:include page="/WEB-INF/includes/header.jsp"/>
        <c:if test="${not empty sessionScope.userSession}">
            <div id="panierInfos"></div>
            <script id="panierTemplate" type="text/template">
                <div class="card">
                    <div class="card-body bg-light">
                      <h1 class="display-4">Mon panier</h1>
                    </div>
                  </div>
                <table class = "table">
                <tr><th>Ref.</th><th>Catégorie</th><th>Nom</th><th>Prix unitaire</th><th>Quantite</th></tr>
                {{#produits}}
                    <tr><td>{{reference}}</td><td>{{categorie}}</td><td>{{nom}}</td><td>{{prix_unitaire}} €</td><td>{{quantite}}</td></tr>
                {{/produits}}
                </table>
                <form id="panierData">
                    <div class="input-group">
                        <input id="produits" name="produits" type="hidden" value="{{produits}}">
                        <button id="submit" name="button" type="sumbit" class="btn btn-secondary form-control">Valider</button>
                    </div>
                </form>
            </script>   
        </c:if>
    <script><jsp:include page="/WEB-INF/scripts/processPanier.js"/></script> 
    </body>
</html>
