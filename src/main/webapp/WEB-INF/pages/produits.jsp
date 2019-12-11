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
                        <tr><td>{{reference}}</td>{{^cat}}<td>{{categorie.libelle}}</td>{{/cat}}<td>{{nom}}</td><td>{{prix_unitaire}} €</td><td>{{dispo}}</td>     
                                <td>
                                <form class="productData">
                                    <div class="input-group">
                                        <input class="form-control" id="quantite" name="quantite" type="number" value="1" min="0" max="10">
                                        <input id="reference" name="reference" type="hidden" value="{{reference}}">
                                        <input id="categorie" name="categorie" type="hidden" value="{{categorie.libelle}}">
                                        <input id="nom" name="nom" type="hidden" value="{{nom}}">
                                        <input id="prix_unitaire" name="prix_unitaire" type="hidden" value="{{prix_unitaire}}">
                                        <button type="sumbit" class="btn btn-secondary form-control" data-toggle="popover" data-trigger="focus" data-placement="top" data-content="Produit ajouté au panier" >Ajouter</button>
                                    </div>
                                </form>
                                </td>
                        </tr>
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
