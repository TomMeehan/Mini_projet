$(document).ready(function () {
    
    $(".quantite").change( function(e){     
        
        
        
        var id = $(this).attr("id");
        /*if ($(this).val() == 0)
            removeFromPanier(id);
        else{*/
            var categorie = $("#cat"+id).val();
            var nom = $("#nom"+id).val();
            var prix_unitaire = $("#prix"+id).val();
            var unites_en_stock = $("#stock"+id).val();
            var quantite_initiale = $("#qteInit"+id).val();
            console.log("init:"+quantite_initiale);
            $.ajax({
            type: "POST",
            url : "updatePanier",
            data : { action : "update",
                reference : id, 
                categorie : categorie, 
                nom : nom, 
                prix_unitaire : prix_unitaire,
                unites_en_stock : unites_en_stock,
                quantite_initiale : quantite_initiale,
                quantite : $(this).val()}, 
            error : showError,
            success : 
                    function(data) {
                        //window.location.href="commandes";
                    }
            }); 
        //}
        
    });
    /*$("#panierData").submit(function(e){

        e.preventDefault();     
    
        $.ajax({
        type: "POST",
        url : "validerPanier",
        data : $(this).serialize(),
        error : showError,
        success : 
                function(data) {
                    window.location.href="commandes";
                }
        });
        console.log("preventing submit");
    });*/
});

function showError(xhr, status, message) {
    console.log(xhr.responseText);
    //alert(JSON.parse(xhr.responseText).message);
}
