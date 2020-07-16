//Start for Materialize
$(document).ready(function(){
    $('.modal').modal();
});

let pass = document.getElementById("password");
let passc = document.getElementById("passwordConfirm");

pass.addEventListener("focusout", function () {
    if (this.value !== passc.value) {
        passc.classList.remove("valid");
        passc.classList.add("invalid");
    } else {
        passc.classList.remove("invalid");
        passc.classList.add("valid");
    }
});

passc.addEventListener("keyup", function () {
    if (this.value !== pass.value) {
        passc.classList.remove("valid");
        passc.classList.add("invalid");
    } else {
        passc.classList.remove("invalid");
        passc.classList.add("valid");
    }
});

let form = document.getElementById("createForm");
let username = $("#user_name")[0];
let email = $("#email")[0];
let selectRoles = document.getElementById("roles");

form.addEventListener("submit", function (event) {

    if (passc.value !== pass.value || passc.value === "" || pass.value === "") {
        event.preventDefault();
        if (pass.value === "") {
            pass.classList.remove("valid");
            pass.classList.add("invalid");
        } else if (selectRoles.value === "select") {
            let span = document.getElementById("select-error");
            span.innerText = "Missing role selection";
            span.style.color = "red";
        }
        return false;
    }
    return true;
});