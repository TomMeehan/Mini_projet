$(document).ready(function () {
    drawClientInfo();
});


function drawClientInfo() {
    $.ajax({
        url : "clientInJSON",
        dataType : "json",
        error : showError,
        success : 
                function(result) {
                    
                    var template = $('#clientTemplate').html();
                    var processedTemplate = Mustache.to_html(template,result);
                    $('#clientInfos').html(processedTemplate);
                }
    });
}

function showError(xhr, status, message) {
    console.log(xhr.responseText);
    //alert(JSON.parse(xhr.responseText).message);
}
