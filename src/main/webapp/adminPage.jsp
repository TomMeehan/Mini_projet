<%-- 
    Document   : adminPage
    Created on : 14 déc. 2019, 19:52:12
    Author     : Gwen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
         <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
         <script type="text/javascript" src="adminScript.js"></script>
         <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawStatCategories);
      google.charts.setOnLoadCallback(drawStatPays);
      google.charts.setOnLoadCallback(drawStatClients);

      function drawStatCategories() {

        var data = google.visualization.arrayToDataTable([
          ['Categories', "Chiffre D'affaire"],
          ['Boissons',     11],
          ['Condiments',      2],
          ['Desserts',  2],
          ['Produits Laitiers', 6],
          ['Pâtes et céréales',    9],
          ['Viandes',    15],
          ['Produits secs',   22],
          ['Poissons et fruits de mer',    1],
        ]);

        var options = {
          title: "Chiffre D'affaire par categorie"
        };

        var chart = new google.visualization.PieChart(document.getElementById('pie1'));

        chart.draw(data, options);
      }
      function drawStatPays() {

        var data = google.visualization.arrayToDataTable([
          ['Pays', 'Hours per Day'],
          ['France',     11],
          ['Belgique',      2],
          ['Etats-Unis',  2],
        ]);

        var options = {
          title: "Chiffre D'affaire par pays"
        };

        var chart = new google.visualization.PieChart(document.getElementById('pie2'));

        chart.draw(data, options);
      }
      function drawStatClients() {

        var data = google.visualization.arrayToDataTable([
          ['Client', 'Hours per Day'],
          ['Paul',     11],
          ['Marc',      58],
          ['Hugue',  98],
          ['Jean', 150],
          ['Maurice',    45]
        ]);

        var options = {
          title: "Chiffre D'affaire par client"
        };

        var chart = new google.visualization.PieChart(document.getElementById('pie3'));

        chart.draw(data, options);
      }
      
      $.ajax({
            
        url : "adminStats",
        data : {typeStat : $("#typeStat").val(),
            startDate : $("#startDate").val(),
            endDate : $("#endDate").val(),

        });
      
    </script>
    

    </head>
    
    <body>
        
        <div id = "div1" > Visualiser les chiffres d'affaire par catégorie d'article, en choisissant la période (date de début / date de fin) sur laquelle doit porter la statistique. <br>
            <form id="majPie" class="form" action="adminStats" >
                Chiffre d'affaire par : 
                <SELECT name="nom" id="typeStat" size="1">
                    <OPTION>Categories
                    <OPTION>Pays
                    <OPTION>Client
                </SELECT>
                <br>
                Début:<input type="date" id="startDate" name="start"       value="2018-07-22"       min="2018-01-01" max="2018-12-31"><br>
                Fin :<input type="date" id="endDate" name="end"       value="2018-07-22"       min="2018-01-01" max="2018-12-31"><br>
                <input type="submit" value="Envoyer"><br>
            </form>
            
        <div id="pie1" style="width: 900px; height: 500px;"> </div>
        <br></div>
        <div id = "div2" > Visualiser les chiffres d'affaire par pays, en choisissant la période (date de début / date de fin) sur laquelle doit porter la statistique. <br>
            <div id="pie2" style="width: 900px; height: 500px;"> </div>
        <br></div>
        <div id = "div3" > Visualiser les chiffres d'affaire par client, en choisissant la période (date de début / date de fin) sur laquelle doit porter la statistique. <br> 
            <div id="pie3" style="width: 900px; height: 500px;"> </div>
        <br></div>

    </body>
    
    
    
</html>


