<%-- 
    Document   : header
    Created on : 4 déc. 2019, 09:30:06
    Author     : pedago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="Home">Comptoirs</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
              <ul class="navbar-nav">
                <li class="nav-item">
                  <a class="nav-link" href="Produits">Nos produits</a>
                </li>
                 <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      Catégories
                    </a>
                    <div id ="dropDownCat" class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink"> 
                    </div>
                </li>
                <!-- TO REMOVE -->
                <li class="nav-item">
                  <a class="nav-link" href="ProductsInJSON">Produits RAW</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="CategoriesInJSON">Categories RAW</a>
                </li>
                <!-- END OF TOREMOVE -->
              </ul>
              <form class="form-inline my-2 my-lg-0 ml-auto" action="/WEB-INF/pages/login.jsp">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
              </form>
            </div>
            
        </nav>
    </body>
    <jsp:include page="bootstrap.jsp"/>
    <script><jsp:include page="/WEB-INF/scripts/header.js"/></script>
</html>
