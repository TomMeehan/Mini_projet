$(document).ready(function () {
    
    $(".quantite").change( function(e){     
        
        
        var id = $(this).attr("id");
        if ($(this).val() == 0)
            removeFromPanier(id);
        else{
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
                        window.location.href="toPanier";
                    }
            }); 
        }
        
    });
});

function removeFromPanier(id){
    
    var answer = confirm("Voulez vous vraiment supprimer cet article ?");
    
    if (answer){
        
        var prix_unitaire = $("#prix"+id).val();
        var quantite = $("#"+id).val();

        
        $.ajax({
        type: "POST",
        url : "updatePanier",
        data : { action : "remove",
            reference : id,
            quantite : quantite,
            prix_unitaire : prix_unitaire},
        error : showError,
        success : 
                function(data) {
                    $("#tr"+id).remove();
                    $("#qteInit"+id).remove();
                    $("#cat"+id).remove();
                    $("#nom"+id).remove();
                    $("#prix"+id).remove();
                    $("#stock"+id).remove();
                    $("#btn"+id).remove();
                    window.location.href="toPanier";
                }
    }); 
    }
    
    
}

function showError(xhr, status, message) {
    console.log(xhr.responseText);
    //alert(JSON.parse(xhr.responseText).message);
}
