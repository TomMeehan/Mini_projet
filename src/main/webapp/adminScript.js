var div1 = document.getElementById('test1');
var div2 = document.getElementById('test2');
var div3 = document.getElementById('test3');
div1.innerHTML += "plop";
div2.innerHTML += "plop2";
div3.innerHTML += "plo3";


function drawChart() {

  var data = google.visualization.arrayToDataTable([
    ['Task', 'Hours per Day'],
    ['Work',     11],
    ['Eat',      2],
    ['Commute',  2],
    ['Watch TV', 2],
    ['Sleep',    7]
  ]);

  var options = {
    title: 'My Daily Activities'
  };

  var chart = new google.visualization.PieChart(document.getElementById('piechart'));

  chart.draw(data, options);
}

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);
      
