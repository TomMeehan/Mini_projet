$(document).ready(function () {
    putCategories();
});

function putCategories() {
    
    $.ajax({
        url : "categoriesInJSON",
        dataType : "json",
        error : showError,
        success : 
                function(result) {       
                    result.cat.forEach( c => {
                        $('#dropDownCat').append('<a class="dropdown-item" href="produits?cat=' + c.code + '">' + c.libelle + '</a>');
                    });
                }
    });
}

function showError(xhr, status, message) {
    console.log(xhr.responseText);
    //alert(JSON.parse(xhr.responseText).message);
}

