//Start for Materialize
$(document).ready(function(){
    $('select').formSelect();
    $('.modal').modal();
});

let form = document.getElementById("productFile");
let inputProductFile = $("#inputProductFile")[0];

form.addEventListener("submit", function (event) {

    if( inputProductFile.value === ""){
        event.preventDefault();
        inputProductFile.classList.remove("valid");
        inputProductFile.classList.add("invalid");
        return false;
    }
    return  true;
});