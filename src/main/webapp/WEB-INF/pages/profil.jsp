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
        <div class="card">
            <div class="card-body bg-light">
              <h1 class="display-4 text-center">Profil</h1>
            </div>
        </div>
        <%--
        <c:if test="${not empty sessionScope.userSession}">
            
            <p> VOUS ETES CONNECTE EN TANT QUE : ${sessionScope.userSession.username}</p>
        </c:if>
        --%> 
        <div id ="clientInfos"></div>
        
        <script id="clientTemplate" type="text/template">
            <div class="container">
            <table class="table table-bordered" >
                <tbody>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Nom</th>
                      <td>{{contact}}</td>
                    </tr>
                    <tr>
                      <th scope="row"class="bg-dark text-light">Societe</th>
                      <td>{{societe}}</td>
                    </tr>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Fonction</th>
                      <td>{{fonction}}</td>
                    </tr>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Adresse</th>
                      
                      <td><table>
                        <tr><td>{{adresse}}, {{code_postal}} {{ville}}, {{pays}}</td></tr>
                      </table></td>
                    </tr>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Telephone</th>
                      <td>{{telephone}}</td>
                    </tr>
                    {{#telephone}}
                    <tr>
                      <th scope="row" class="bg-dark text-light">Fax</th>
                      <td>{{telephone}}</td>
                    </tr>
                    {{/telephone}}
                </tbody>
            </table>
            <form class="form-inline my-2 my-lg-0 ml-auto" action="toEditProfile">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Modifier</button>
            </form>
    <div >
        </script>
     <script><jsp:include page="/WEB-INF/scripts/processClientInfo.js"/></script>
    </body>
</html>
