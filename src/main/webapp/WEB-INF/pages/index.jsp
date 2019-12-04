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
        <title>Home</title>
        <jsp:include page="/WEB-INF/includes/header.jsp"/>
    </head>
    <body>
        <div class="jumbotron center"> 
            <div class="container">
                <a href="productsInJSON" class="btn btn-primary btn-lg active" role="button" aria-pressed="true" align="center">Primary link</a>
                <a href="ProductsInJSON" class="btn btn-primary btn-lg active" role="button" aria-pressed="true" align="center">Produits</a>
                <form onsubmit="event.preventDefault(); drawProductTable();">
                    <button type="submit" class="btn btn-outline-primary">Produits</button>
                </form>
            </div>
        </div>
        
        <div id="productsTable"></div>
        <script id="productsTemplate" type="text/template">
            <table class = "table">
            <tr><th>Ref.</th><th>Catégorie</th><th>Nom</th><th>Fournisseur</th><th>Indisponible</th></tr>
            {{#produits}}
                <tr><td>{{reference}}</td><td>{{categorie}}</td><td>{{nom}}</td><td>{{indisponible}}</td></tr>
            {{/produits}}
            </table>
        </script>
    
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.8.1/mustache.min.js"></script>
    <script><jsp:include page="/WEB-INF/scripts/processProducts.js"/></script>
    </body>
    
</html>
