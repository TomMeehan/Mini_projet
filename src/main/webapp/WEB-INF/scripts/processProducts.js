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
                        var id = $(this).attr("id");
                        
                        var unites_en_stock = parseInt($("#unites_en_stock"+id).val());
                        console.log("stock :" + unites_en_stock);
                        
                        var quantite = parseInt($("#quantite"+id).val());
                        console.log("quantite :" + quantite);
                        var idSumbitBtn = $("#submit"+id);
                        
                        if (unites_en_stock < quantite){
                            idSumbitBtn.popover('disable');    
                            idSumbitBtn.attr("disabled",true);
                            
                            $("#quantite"+id).change( function (e) {

                                if(parseInt($(this).val()) <= unites_en_stock){
                                    console.log("oui");
                                    idSumbitBtn.popover('enable');    
                                    idSumbitBtn.removeAttr("disabled");
                                }
                            });
                        }
                        else{                          
                            
                            $.ajax({
                            type: "POST",
                            url : "addProduitPanier",
                            data : form.serialize(),
                            error : showError,
                            success : 
                                    function(data) {
                                        unites_en_stock -= quantite;
                                        $("#unites_en_stock"+id).val(unites_en_stock);
                                        console.log($("#unites_en_stock"+id).val());
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
