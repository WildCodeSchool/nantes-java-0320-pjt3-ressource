$(document).ready(function(){
    $('.modal').modal();
    $('select').formSelect();
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

    if (passc.value !== pass.value || passc.value === "" || pass.value === "" || username.value === "" || email.value === "" || selectRoles.value === "0") {
        event.preventDefault();
        if (username.value === "") {
            username.classList.remove("valid");
            username.classList.add("invalid");
        } else if (email.value === "") {
            email.classList.remove("valid");
            email.classList.add("invalid");
        } else if (pass.value === "") {
            pass.classList.remove("valid");
            pass.classList.add("invalid");
        } else if (selectRoles.value === "0") {
            let span = document.getElementById("select-error");
            span.innerText = "Missing role selection";
            span.style.color = "red";
        }
        return false;
    }
    $('.modal').close();
    return true;
});

