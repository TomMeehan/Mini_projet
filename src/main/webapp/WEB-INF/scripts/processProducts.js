$(document).ready(function () {
    console.log("ready");
});

function drawProductTable() {
    $.ajax({
        url : "ProductsInJSON",
        dataType : "json",
        error : showError,
        success : 
                function(result) {
                    var template = $('#productsTemplate').html();
                    var processedTemplate = Mustache.to_html(template,result);
                    $('#productsTable').html(processedTemplate);
                }
    });
}

function showError(xhr, status, message) {
    console.log(xhr.responseText);
    //alert(JSON.parse(xhr.responseText).message);
}
