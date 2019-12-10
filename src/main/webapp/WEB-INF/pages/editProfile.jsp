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
                      <td><input type="text" class="form-control" placeholder="Votre fonction" value='{{fonction}}'></td>
                    </tr>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Adresse</th>
                      <td><input type="text" class="form-control" placeholder="Votre adresse" value='{{adresse}}'></td>
                    </tr>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Code postal</th>
                      <td><input type="text" class="form-control" placeholder="Votre code postal" value='{{code_postal}}'></td>
                    </tr>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Ville</th>
                      <td><input type="text" class="form-control" placeholder="Votre ville" value='{{ville}}'></td>
                    </tr>
                     <tr>
                      <th scope="row" class="bg-dark text-light">Pays</th>
                      <td><input type="text" class="form-control" placeholder="Votre pays" value='{{pays}}'></td>
                    </tr>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Région</th>
                      <td><input type="text" class="form-control" placeholder="Votre région" value='{{#region}}{{region}}{{/region}}'></td>
                    </tr>
                    <tr>
                      <th scope="row" class="bg-dark text-light">Tel.</th>
                      <td><input type="text" class="form-control" placeholder="Votre telephone" value='{{telephone}}'></td>
                    </tr>
                    <tr>
                      <th scope="row" class="bg-dark text-light">FAX</th>
                      <td><input type="text" class="form-control" placeholder="Votre fax" value='{{telephone}}'></td>
                    </tr>
                </tbody>
            </table>
        </script>
        <script><jsp:include page="/WEB-INF/scripts/editClientInfo.js"/></script>
    </body>
</html>
