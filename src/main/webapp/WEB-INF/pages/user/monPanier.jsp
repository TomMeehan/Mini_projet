<%-- 
    Document   : monPanier
    Created on : Dec 11, 2019, 1:20:06 PM
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "java.util.*"%>
<%@ page import = "java.lang.*"%>
    <jsp:include page="/WEB-INF/includes/head.jsp"/>
        <title>Mon Panier</title>
        
    </head>
    <body>
        <jsp:include page="/WEB-INF/includes/header.jsp"/>
        <div class="container">
                <div class="card my-4">
                    <div class="card-body bg-light text-center">
                        <h1 class="display-4">Mon panier</h1>
                    </div>
                </div>
                <form id="panierData" class="form" action="validerPanier">
                
                <c:choose>
                    <c:when test="${empty panier.produits}">
                        <div class="card my-4">
                            <div class="card-body bg-light text-center">
                                <p class="card-text">Votre panier est vide.</p>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <table class="table table-striped table-bordered">
                            <thead class="thead-dark"><tr><th>Ref.</th><th>Catégorie</th><th>Nom</th><th>Prix unitaire</th><th>Quantite</th><th></th></tr></thead>
                        <c:forEach var="produits" items="${panier.produits}">
                                <div id="div${produits.reference}">
                                <tr id="tr${produits.reference}"><td>${produits.reference}</td><td>${produits.categorie}</td><td>${produits.nom}</td><td>${produits.prix_unitaire} €</td>
                                    <td><input class="quantite form-control" id="${produits.reference}" name="quantite" type="number" value="${produits.quantite}" min="0" max="${produits.stock_initial}"></td>
                                    <td align="center"><button id="btn${produits.reference}" type="button" class="btn btn-danger" onclick="removeFromPanier(${produits.reference});">Supprimer</button>
                                </tr>
                                <input id="qteInit${produits.reference}" name="quantite_initiale" type="hidden" value="${produits.quantite}">
                                <input id="cat${produits.reference}" name="categorie" type="hidden" value="${produits.categorie}">
                                <input id="nom${produits.reference}" name="nom" type="hidden" value="${produits.nom}">
                                <input id="prix${produits.reference}" name="prix_unitaire" type="hidden" value="${produits.prix_unitaire}">
                                <input id="stock${produits.reference}" name="unites_en_stock" type="hidden" value="${produits.unites_en_stock}">
                                </div>   
                        </c:forEach>
                            <tfoot><tr><th></th><th></th><th></th><th></th><th>Total :</th><th class="text-center"><strong>${panier.prixTotal} €</strong></th></tr></tfoot>

                        </table>
                        <div class="input-group justify-content-center">
                            <button id="submit" name="button" type="sumbit" class="btn btn-secondary">Valider la commande</button>
                        </div>
                    </c:otherwise>
                        
                </c:choose>
                </form>
            
        </div>
    <script><jsp:include page="/WEB-INF/scripts/processPanier.js"/></script>
    </body>
</html>
