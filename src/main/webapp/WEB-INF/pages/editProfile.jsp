<%-- 
    Document   : editProfil
    Created on : 10 déc. 2019, 16:18:01
    Author     : pedago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <jsp:include page="/WEB-INF/includes/head.jsp"/>
        <title>Edit profile</title>
        
    </head>
    <body>
        <jsp:include page="/WEB-INF/includes/header.jsp"/> 
        <div id ="clientInfosEdit"></div>
        <script id="clientTemplateEdit" type="text/template">
            <div class="container">
            <table class="table table-bordered" >
                <tbody>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Nom</th>
                      <td><input type="text" class="form-control" placeholder="Votre nom" value='{{contact}}'></td>
                    </tr>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Societe</th>
                      <td><input type="text" class="form-control" placeholder="Votre societe" value='{{societe}}'></td>
                    </tr>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Fonction</th>
                      <td><input type="text" class="form-control" placeholder="Votre societe" value='{{fonction}}'></td>
                    </tr>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Societe</th>
                      <td><input type="text" class="form-control" placeholder="Votre societe" value='{{fonction}}'></td>
                    </tr>
                </tbody>
            </table>
        </script>
        <script><jsp:include page="/WEB-INF/scripts/editClientInfo.js"/></script>
    </body>
</html>
