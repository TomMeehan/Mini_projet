$(document).ready(function () {
    console.log("ready");
    drawProductTable();
});

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

function drawProductTable() {
    $.ajax({
        url : "productsInJSON",
        data : { "cat" : getUrlParameter("cat") },
        dataType : "json",
        error : showError,
        success : 
                function(result) {
                    
                    result.titre = "Produits";
                    
                    result.cat = getUrlParameter("cat");
                    
                    if (result.cat) {
                        console.log(result);
                        result.titre = result.produits[0].categorie.libelle;
                    }
                    
                    result.produits.forEach( p => {
                        if (p.indisponible == 0)
                            p.dispo = "Oui";
                        else
                            p.dispo = "Non";
                    });
                    
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
