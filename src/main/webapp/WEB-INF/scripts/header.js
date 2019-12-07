$(document).ready(function () {
    console.log("header generated");
    putCategories();
});

function putCategories() {
    console.log("Insertion des catégories...");
    
    $.ajax({
        url : "CategoriesInJSON",
        dataType : "json",
        error : showError,
        success : 
                function(result) {       
                    result.categories.forEach( c => {
                        $('#dropDownCat').append('<a class="dropdown-item" href="Produits?categorie=' + c.code + '">' + c.libelle + '</a>');
                    });
                }
    });
}

function showError(xhr, status, message) {
    console.log(xhr.responseText);
    //alert(JSON.parse(xhr.responseText).message);
}

