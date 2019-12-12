$(document).ready(function () {
    drawCommandes();
});


function drawCommandes() {
    $.ajax({
        url : "commandesInJSON",
        dataType : "json",
        error : showError,
        success : 
                function(result) {
                    
                    var template = $('#commandesTemplate').html();
                    var processedTemplate = Mustache.to_html(template,result);
                    $('#commandesData').html(processedTemplate);

                }
    });
}

function showError(xhr, status, message) {
    console.log(xhr.responseText);
    //alert(JSON.parse(xhr.responseText).message);
}

