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
        <div class="card">
            <div class="card-body bg-light">
              <h1 class="display-4 text-center">Modifier vos données</h1>
            </div>
        </div>
        <div class="container  my-4">
            <c:if test="${not empty requestScope.errors}">
               <div class="alert alert-danger" role="alert">
                   <strong>Erreur!</strong> ${requestScope.errors}
              </div>
            </c:if>
        <c:if test="${!empty sessionScope.userSession}">
            <form  method="psot" action="updateClient">
                <table class="table table-bordered" >
                    <tbody>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Nom</th>
                          <td><input type="text" name="contact" class="form-control" placeholder="Votre nom" value='${userSession.contact}'></td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Societe</th>
                          <td><input type="text" name="societe" class="form-control" placeholder="Votre societe" value='${userSession.societe}'></td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Fonction</th>
                          <td><input type="text" name="fonction" class="form-control" placeholder="Votre fonction" value='${userSession.fonction}'></td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Adresse</th>
                          <td><input type="text" name="adresse" class="form-control" placeholder="Votre adresse" value='${userSession.adresse}'></td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Code postal</th>
                          <td><input type="text" name="code_postal" class="form-control" placeholder="Votre code postal" value='${userSession.code_postal}'></td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Ville</th>
                          <td><input type="text" name="ville" class="form-control" placeholder="Votre ville" value='${userSession.ville}'></td>
                        </tr>
                         <tr>
                          <th scope="row" class="bg-dark text-light">Pays</th>
                          <td><input type="text" name="pays" class="form-control" placeholder="Votre pays" value='${userSession.pays}'></td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Région</th>
                          <td><input type="text" name="region" class="form-control" placeholder="Votre région" value='<c:if test="${!empty sessionScope.userSession.region}">${userSession.region}</c:if>'></td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">Tel.</th>
                          <td><input type="text" name="telephone" class="form-control" placeholder="Votre telephone" value='${userSession.telephone}'></td>
                        </tr>
                        <tr>
                          <th scope="row" class="bg-dark text-light">FAX</th>
                          <td><input type="text" name="fax" class="form-control" placeholder="Votre fax" value='${userSession.fax}'></td>
                        </tr>
                    </tbody>
                </table>
                <div class="text-center my-4">
                    <button class="btn btn-success" type="submit">Valider</button>
                </div>
            </form>
            </c:if>
        </div>
    </body>
</html>
