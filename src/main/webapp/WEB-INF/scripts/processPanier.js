$(document).ready(function () {
    drawPanier();
});


function drawPanier() {
    $.ajax({
        url : "panierInJSON",
        dataType : "json",
        error : showError,
        success : 
                function(result) {
                    
                    var template = $('#panierTemplate').html();
                    var processedTemplate = Mustache.to_html(template,result);
                    $('#panierInfos').html(processedTemplate);

                }
    });
}

function showError(xhr, status, message) {
    console.log(xhr.responseText);
    //alert(JSON.parse(xhr.responseText).message);
}
