<%-- 
    Document   : home
    Created on : 4 déc. 2019, 08:57:24
    Author     : pedago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <jsp:include page="/WEB-INF/includes/head.jsp"/>
        <title>Products</title>
        
    </head>
    <body>
        <jsp:include page="/WEB-INF/includes/header.jsp"/>
        <div id="productsTable"></div>
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
            <jsp:include page="/WEB-INF/includes/footer.jsp"/>
    <script><jsp:include page="/WEB-INF/scripts/processProducts.js"/></script>
    </body>
    
</html>
