<%-- 
    Document   : clientPage
    Created on : Dec 8, 2019, 3:17:55 PM
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="/WEB-INF/includes/head.jsp"/>
        <title>Page client</title>
        <link rel="stylesheet" type="text/css" href ="css/profil.css"/>
        
    </head>
    <body>
        <jsp:include page="/WEB-INF/includes/header.jsp"/>
        
        <div class="container">
            <div class="card my-4">
                <div class="card-body bg-light">
                  <h1 class="display-4 text-center">Profil</h1>
                </div>
            </div>
        <c:if test="${!empty sessionScope.userSession}">
            
                <table class="table table-bordered" >
                    <tbody>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Nom</th>
                          <td>${userSession.contact}</td>
                        </tr>
                        <tr>
                          <th scope="row"class="bg-dark text-light">Societe</th>
                          <td>${userSession.societe}</td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Fonction</th>
                          <td>${userSession.fonction}</td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Adresse</th>
                          <td>${userSession.adresse}, ${userSession.code_postal} ${userSession.ville}, ${userSession.pays}</td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Telephone</th>
                          <td>${userSession.telephone}</td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Fax</th>
                          <td>${userSession.fax}</td>
                        </tr>
                    </tbody>
                </table>
                <form class="form" action="toEditProfile">
                    <div class="text-center my-4">
                        <button class="btn btn-secondary" type="submit">Modifier</button>
                    </div>
                </form>
            <div >
        </c:if>
        </div>
    </body>
</html>
