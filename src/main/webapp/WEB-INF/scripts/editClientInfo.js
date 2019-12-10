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
                    
                    var template = $('#clientTemplateEdit').html();
                    var processedTemplate = Mustache.to_html(template,result);
                    $('#clientInfosEdit').html(processedTemplate);
                }
    });
}

function showError(xhr, status, message) {
    console.log(xhr.responseText);
    //alert(JSON.parse(xhr.responseText).message);
}
