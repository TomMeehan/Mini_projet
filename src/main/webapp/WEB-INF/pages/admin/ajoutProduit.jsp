<%-- 
    Document   : editProduit
    Created on : Dec 15, 2019, 12:38:47 PM
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <jsp:include page="/WEB-INF/includes/head.jsp"/>
        <title>Ajout produit</title>
        
    </head>
    <body>
        <jsp:include page="/WEB-INF/includes/header.jsp"/> 
        <div class="card">
            <div class="card-body bg-light">
              <h1 class="display-4 text-center">Ajouter un produit</h1>
            </div>
        </div>
        <div class="container  my-4">
        <c:if test="${not empty requestScope.errors}">
           <div class="alert alert-danger" role="alert">
               <strong>Erreur!</strong> ${requestScope.errors}
          </div>
        </c:if>
        <c:if test="${!empty sessionScope.adminSession}">
            <form  method="post" action="editProduit">
                <table class="table table-bordered" >
                    <tbody>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Nom</th>
                          <td><input type="text" name="nom" class="form-control" placeholder="Nom du produit"></td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Fournisseur</th>
                          <td><input type="number" step="1" name="fournisseur" class="form-control" placeholder="Numero fournisseur"></td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Categorie</th>
                          <td><input type="number" step="1"name="categorie" class="form-control" placeholder="Numero categorie"></td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Quantite par unité</th>
                          <td><input type="text" name="quantite_par_unite" class="form-control" placeholder="Quantite par unité"></td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Prix unitaire</th>
                          <td><input type="number" step="0.01" name="prix_unitaire" class="form-control" placeholder="Prix unitaire"></td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Unités en stock</th>
                          <td><input type="number" step="1"  name="unites_en_stock" class="form-control" placeholder="Unites en stock"></td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Niveau de réapprovisionnement</th>
                          <td><input type="number" step="1"  name="niveau_de_reapprovi" class="form-control" placeholder="Niveau de reapprovisionnement"></td>
                        </tr>
                    </tbody>
                </table>
                <div class="text-center my-4">
                    <button class="btn btn-success" type="submit" name="action" value="add">Valider</button>
                </div>
            </form>
            </c:if>
        </div>
    </body>
</html>
