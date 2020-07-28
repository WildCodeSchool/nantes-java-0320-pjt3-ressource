//Start for Materialize
$(document).ready(function(){
    $('select').formSelect();
    $('.modal').modal();
});

let form = document.getElementById("productFile");
let inputProductFile = document.getElementById("inputProductFile");
let inputFile = document.getElementById("filePictureProduct");
form.addEventListener("submit", function (event) {

    let file = inputFile.files[0];

    if (inputProductFile.value === "" ){

        event.preventDefault();
        inputProductFile.classList.remove("valid");
        inputProductFile.classList.add("invalid");
        let span = document.getElementById("error");
        span.innerText = "Missing image";
        span.style.color = "red";
        return false;
    } else if (file.size > 5000000){

        event.preventDefault();
        let span = document.getElementById("error");
        inputProductFile.classList.remove("valid");
        inputProductFile.classList.add("invalid");
        span.innerText = "The size of the image must be less than 5MB";
        span.style.color = "red";

        return false;
    }
    return  true;
});


