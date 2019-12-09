<%-- 
    Document   : header
    Created on : 4 déc. 2019, 09:30:06
    Author     : pedago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/header.css"
    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="home">Comptoirs</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
              <ul class="navbar-nav">
                <li class="nav-item">
                  <a class="nav-link" href="produits">Nos produits</a>
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
                  <a class="nav-link" href="productsInJSON">Produits RAW</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="categoriesInJSON">Categories RAW</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="sessionInJSON">Session RAW</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="clientInJSON">Client RAW</a>
                </li>
                <!-- END OF TOREMOVE -->
              </ul>
                
            <c:if test="${empty sessionScope.userSession}">
                <form class="form-inline my-2 my-lg-0 ml-auto" action="login">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
                </form>
            </c:if>
            <c:if test="${!empty sessionScope.userSession}">
                <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown my-2 my-lg-0 ml-auto">
                    <a class="nav-link dropdown-toggle " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <img src="images/profil_image.png" width="30" height="30"/>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                      <a href="profil" class="dropdown-item">Profile</a>
                      <div class="dropdown-divider"></div>
                      <a href="disconnect" class="dropdown-item">Logout</a>
                    </div>
                </li>
                </ul>
            </c:if> 
            
            
        </nav>
    <jsp:include page="bootstrap.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.8.1/mustache.min.js"></script>
    <script><jsp:include page="/WEB-INF/scripts/header.js"/></script>
    </body>
    
</html>
