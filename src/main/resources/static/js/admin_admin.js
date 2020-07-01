$(document).ready(function(){
    $('.modal').modal();
});
$(document).ready(function(){
    $('select').formSelect();
});

let pass = document.getElementById("password");
let passc = document.getElementById("passwordConfirm");

pass.addEventListener("focusout", function () {
    if (this.value !== passc.value) {
        console.log("hole")
        passc.classList.remove("valid");
        passc.classList.add("invalid");
    } else {
        passc.classList.remove("invalid");
        passc.classList.add("valid");
    }
});

passc.addEventListener("keyup", function () {
    if (this.value !== pass.value) {
        console.log("hola")
        passc.classList.remove("valid");
        passc.classList.add("invalid");
    } else {
        passc.classList.remove("invalid");
        passc.classList.add("valid");
    }
});
