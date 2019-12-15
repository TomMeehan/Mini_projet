var arr = [];
$(document).ready(function () {    
    google.charts.load('current', {'packages':['corechart']});
    
});

function sendToServlet(){
    $.ajax({
        type: "POST",
        url : "adminStats",
        data : $("#majPie").serialize(),
        dataType : "json",
        error : showError,
        success : 
                function(result) {
                    arr = [[result.type,"Chiffre d'affaire"]];
                    result.data.forEach(c => {
                        arr.push([c.first,c.second]);
                    });
                    switch (result.type) {
                        case "Categories":
                            google.charts.setOnLoadCallback(drawStatCategories);
                            break;
                        case "Pays":
                            google.charts.setOnLoadCallback(drawStatPays);
                            break;
                        case "Clients":
                            google.charts.setOnLoadCallback(drawStatClients);
                            break;
                        default:
                            break;
                    }
                    
                }
    }); 
}

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
};

function drawStatCategories() {
    
  var data = google.visualization.arrayToDataTable(arr);

  var options = {
    title: "Chiffre D'affaire par categorie"
  };

  var chart = new google.visualization.PieChart(document.getElementById('pie1'));

  chart.draw(data, options);
}
function drawStatPays() {
    var data = google.visualization.arrayToDataTable(arr);  
  var options = {
    title: "Chiffre D'affaire par pays"
  };

  var chart = new google.visualization.PieChart(document.getElementById('pie2'));

  chart.draw(data, options);
}
function drawStatClients() {
  var data = google.visualization.arrayToDataTable(arr);
  var options = {
    title: "Chiffre D'affaire par client"
  };

  var chart = new google.visualization.PieChart(document.getElementById('pie3'));

  chart.draw(data, options);
}
function showError(xhr, status, message) {
    console.log(xhr.responseText);
    //alert(JSON.parse(xhr.responseText).message);
}
   