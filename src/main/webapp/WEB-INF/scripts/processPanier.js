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
                    
                    $("#panierData").submit(function(e){

                        e.preventDefault();

                        var form = $(this);        
                        console.log(JSON.parse($(this [name="produits"]).val()));
                        
                        if (true){
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
