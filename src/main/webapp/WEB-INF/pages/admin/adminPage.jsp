<%-- 
    Document   : adminPage
    Created on : 14 déc. 2019, 19:52:12
    Author     : Gwen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <jsp:include page="/WEB-INF/includes/head.jsp"/>
    <title>Statistiques</title>
    </head>
    
    <body>
        <jsp:include page="/WEB-INF/includes/header.jsp"/>
        <div class="container">
            <form id="majPie" class="form" action="adminStats" >
                <div class="input-group mb-3 my-4">
                    <div class="input-group-prepend">
                      <label class="input-group-text" for="inputGroupSelect01">Par ...</label>
                    </div>
                    <select name="typeStat" class="custom-select" id="typeStat">
                      <option selected>Categories</option>
                      <option>Pays</option>
                      <option>Clients</option>
                    </select>
                </div>
                <div class="form-group row">
                    <label for="example-date-input" class="col-2 col-form-label">Date de début</label>
                    <div class="col-10">
                      <input class="form-control" type="date"id="startDate" name="startDate" value="2018-07-22">
                    </div>
                    <label for="example-date-input" class="col-2 col-form-label">Date de fin</label>
                    <div class="col-10">
                      <input class="form-control" type="date" id="endDate" name="endDate" value="2018-07-22">
                    </div>
                    
                </div>
                <div class="text-center my-4">
                    <button class="btn btn-secondary" type="button" onclick="sendToServlet();" value="Envoyer">Envoyer</button>
                </div>
            </form>
        <div id = "div1" > Visualiser les chiffres d'affaire par catégorie d'article, en choisissant la période (date de début / date de fin) sur laquelle doit porter la statistique. <br>   
        <div id="pie1" style="width: 900px; height: 500px;"> </div>
        <br></div>
        <div id = "div2" > Visualiser les chiffres d'affaire par pays, en choisissant la période (date de début / date de fin) sur laquelle doit porter la statistique. <br>
            <div id="pie2" style="width: 900px; height: 500px;"> </div>
        <br></div>
        <div id = "div3" > Visualiser les chiffres d'affaire par client, en choisissant la période (date de début / date de fin) sur laquelle doit porter la statistique. <br> 
            <div id="pie3" style="width: 900px; height: 500px;"> </div>
        <br></div>
        </div>
    <script><jsp:include page="/WEB-INF/scripts/adminScript.js"/></script>
    </body>
   
</html>


