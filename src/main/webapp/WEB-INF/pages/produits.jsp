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
            <c:when test="${(not empty sessionScope.adminSession)}" >
            <script id="productsTemplate" type="text/template">
                <div class="card">
                    <div class="card-body bg-light">
                      <h1 class="display-4">{{titre}}</h1>
                    </div>
                  </div>                   
                    <table class = "table table-striped">
                    <thead class="thead-dark"><tr><th>Ref.</th>{{^cat}}<th>Catégorie</th>{{/cat}}<th>Nom</th><th>Prix unitaire</th><th>Disponible</th><th></th></tr></thead>
                    {{#produits}}
                        <tr id="td{{reference}}"><td>{{reference}}</td>{{^cat}}<td>{{categorie.libelle}}</td>{{/cat}}<td>{{nom}}</td><td>{{prix_unitaire}} €</td><td>{{dispo}}</td>     
                                <td>
                                <form class="productDataAdmin" id={{reference}} action="toEditProduit">
                                    <div class="input-group">
                                   
                                        <button id="editbtn{{reference}}" name="action" type="submit" class="btn btn-secondary mx-2" value="edit">Modifier</button>
                                         
                                        <input id="reference{{reference}}" name="reference" type="hidden" value="{{reference}}"><!--
                                        <input id="nom{{reference}}" name="nom" type="hidden" value="{{nom}}">
                                        <input id="fournisseur{{reference}}" name="fournisseur" type="hidden" value="{{fournisseur}}">
                                        <input id="categorie{{reference}}" name="categorie" type="hidden" value="{{categorie.code}}">
                                        <input id="quantite_par_unite{{reference}}" name="quantite_par_unite" type="hidden" value="{{quantite_par_unite}}">
                                        <input id="prix_unitaire{{reference}}" name="prix_unitaire" type="hidden" value="{{prix_unitaire}}">
                                        <input id="unites_en_stock{{reference}}" name="unites_en_stock" type="hidden" value="{{unites_en_stock}}">                                       
                                        <input id="niveau_de_reapprovi{{reference}}" name="niveau_de_reapprovi" type="hidden" value="{{niveau_de_reapprovi}}">-->
                                        
                                        <button id="deletebtn{{reference}}" name="delete" type="button" onclick="deleteProduct();" class="btn btn-secondary mx-2" value="delete">Supprimer</button>
                                        
                                    </div>
                                </form>
                                </td>
                        </tr>
                    {{/produits}}
                    </table>                   
            </script>                
            </c:when>
            <c:when test="${(not empty sessionScope.userSession)}" >
            <script id="productsTemplate" type="text/template">
                <div class="card">
                    <div class="card-body bg-light">
                      <h1 class="display-4">{{titre}}</h1>
                    </div>
                  </div>                   
                    <table class = "table table-striped">
                    <thead class="thead-dark"><tr><th>Ref.</th>{{^cat}}<th>Catégorie</th>{{/cat}}<th>Nom</th><th>Prix unitaire</th><th>Disponible</th><th></th></tr></thead>
                    {{#produits}}
                        <tr><td>{{reference}}</td>{{^cat}}<td>{{categorie.libelle}}</td>{{/cat}}<td>{{nom}}</td><td>{{prix_unitaire}} €</td><td>{{dispo}}</td>     
                                <td>
                                <form class="productData" id={{reference}}>
                                    <div class="input-group">
                                        <input class="form-control" id="quantite{{reference}}" name="quantite" type="number" value="1" min="0" max="100">
                                        <input id="reference{{reference}}" name="reference" type="hidden" value="{{reference}}">
                                        <input id="categorie{{reference}}" name="categorie" type="hidden" value="{{categorie.libelle}}">
                                        <input id="nom{{reference}}" name="nom" type="hidden" value="{{nom}}">
                                        <input id="prix_unitaire{{reference}}" name="prix_unitaire" type="hidden" value="{{prix_unitaire}}">
                                        <input id="unites_en_stock{{reference}}" name="unites_en_stock" type="hidden" value="{{unites_en_stock}}">
                                        <button id="submit{{reference}}" name="button" type="sumbit" class="btn btn-secondary form-control" data-toggle="popover" data-trigger="focus" data-placement="top" data-content="Produit ajouté au panier" >Ajouter</button>
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
