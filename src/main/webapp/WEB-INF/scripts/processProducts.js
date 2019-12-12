$(document).ready(function () {
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
                    
                    $('[data-toggle="popover"]').popover();
                    $('.popover-dismiss').popover({
                        trigger: 'focus'
                    });
 
                    $(".productData").submit(function(e){

                        e.preventDefault();

                        var form = $(this);        
                        var unites_en_stock = parseInt($(this [name="unites_en_stock"]).val());
                        console.log("stock :" + unites_en_stock);
                        
                        var quantite = parseInt($(this [name="quantite"]).val());
                        console.log("quantite :" + quantite);
                        var idSumbitBtn = $(this [name="button"]);
                        
                        if (unites_en_stock < quantite){
                            idSumbitBtn.popover('disable');    
                            idSumbitBtn.attr("disabled",true);
                            
                            $(this [name="quantite"]).change( function (e) {

                                if(parseInt($(this).val()) <= unites_en_stock){
                                    console.log("oui");
                                    idSumbitBtn.popover('enable');    
                                    idSumbitBtn.removeAttr("disabled");
                                }
                            });
                        }
                        else{
                            $(this [name="unites_en_stock"]).val(unites_en_stock - quantite);
                            
                            $.ajax({
                            type: "POST",
                            url : "addProduitPanier",
                            data : form.serialize(),
                            error : showError,
                            success : 
                                    function(data) {

                                    }
                            });
                            console.log("preventing submit");
                        }

                        
                    });
                }
    });
}




function showError(xhr, status, message) {
    console.log(xhr.responseText);
    //alert(JSON.parse(xhr.responseText).message);
}
