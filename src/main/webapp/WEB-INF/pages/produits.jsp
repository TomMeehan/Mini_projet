<%-- 
    Document   : home
    Created on : 4 déc. 2019, 08:57:24
    Author     : pedago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
        <jsp:include page="/WEB-INF/includes/header.jsp"/>
    </head>
    <body>
        <div id="productsTable"></div>
        <script id="productsTemplate" type="text/template">
            <div class="card">
                <div class="card-body bg-light">
                  <h1 class="display-4">{{titre}}</h1>
                </div>
              </div>
            <table class = "table">
            <tr><th>Ref.</th>{{^cat}}<th>Catégorie</th>{{/cat}}<th>Nom</th><th>Disponible</th></tr>
            {{#produits}}
                <tr><td>{{reference}}</td>{{^cat}}<td>{{categorie.libelle}}</td>{{/cat}}<td>{{nom}}</td><td>{{dispo}}</td></tr>
            {{/produits}}
            </table>
        </script>
        
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.8.1/mustache.min.js"></script>
    <script><jsp:include page="/WEB-INF/scripts/processProducts.js"/></script>
    </body>
    
</html>
