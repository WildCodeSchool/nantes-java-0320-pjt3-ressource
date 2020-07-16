//Start for Materialize
$(document).ready(function(){
    $('select').formSelect();
    $('.modal').modal();
});
//End fot Materialize

fetch('/admin/company/new')
    .then(function (response) {
        return response.text()
    }).then(function (content) {
    let parser = new DOMParser();
    let html = parser.parseFromString(content, 'text/html');
    let sectionCompany = document.getElementById("templateCompany");
    let newResult = html.getElementById("newCompanySelected");
    sectionCompany.innerHTML = newResult.innerHTML;
});

//Start to collect the selected company
let companies = document.getElementById("company-choice");
let companySelected = companies.addEventListener('change', function() {

    let valueCompany = companies.options[companies.selectedIndex].value;
    fetch('/admin/company/' + valueCompany)
        .then(function (response) {
            return response.text()
        }).then(function (content) {
        let parser = new DOMParser();
        let html = parser.parseFromString(content, 'text/html');
        let sectionCompany = document.getElementById("templateCompany");
        let newResult = html.getElementById("newCompanySelected");
        sectionCompany.innerHTML = newResult.innerHTML;
        });
    });
//End