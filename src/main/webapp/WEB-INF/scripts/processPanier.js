$(document).ready(function () {
    $("#panierData").submit(function(e){

        e.preventDefault();     

        $.ajax({
        type: "POST",
        url : "validerPanier",
        error : showError,
        success : 
                function(data) {
                    window.location.href="commandes";
                }
        });
        console.log("preventing submit");
    });
});

function showError(xhr, status, message) {
    console.log(xhr.responseText);
    //alert(JSON.parse(xhr.responseText).message);
}
