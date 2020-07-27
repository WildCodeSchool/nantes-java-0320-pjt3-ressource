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

// 5MB Images not allowed
let form = document.getElementById("new-company-admin");
let inputFileLogo = document.getElementById("logo");
let inputFilePicFromSky = document.getElementById("picFromSky");
let inputFileCompMap = document.getElementById("compMap");
let inputFileCeoPhoto = document.getElementById("ceo-photo");
let inputFileThumbnailOneTopLeft = document.getElementById("thumbnailOneTopLeft");
let inputFileThumbnailOneTopMiddle = document.getElementById("thumbnailOneTopMiddle");
let inputFileThumbnailOneTopRight = document.getElementById("thumbnailOneTopRight");
let inputFileThumbnailSideWords = document.getElementById("thumbnailSideWords");
let inputFileThumbnailTwoTopLeft = document.getElementById("thumbnailTwoTopLeft");
let inputFileThumbnailTwoTopMiddle= document.getElementById("thumbnailTwoTopMiddle");
let inputFileThumbnailTwoTopRight= document.getElementById("thumbnailTwoTopRight");
let inputFileThumbnailTwoSideWords= document.getElementById("thumbnailTwoSideWords");
let inputFileEndPhoto = document.getElementById("endPhoto");



form.addEventListener("submit", function (event) {

    let size = 5000000;
    let logo = inputFileLogo.files[0];
    let picFromSky = inputFilePicFromSky.files[0];
    let compMap = inputFileCompMap.files[0];
    let ceoPhoto = inputFileCeoPhoto.files[0];
    let thumbnailOneTopLeft = inputFileThumbnailOneTopLeft.files[0];
    let thumbnailOneTopMiddle = inputFileThumbnailOneTopMiddle.files[0];
    let thumbnailOneTopRight = inputFileThumbnailOneTopRight.files[0];
    let thumbnailSideWords = inputFileThumbnailSideWords.files[0];
    let thumbnailTwoTopLeft = inputFileThumbnailTwoTopLeft.files[0];
    let thumbnailTwoTopMiddle = inputFileThumbnailTwoTopMiddle.files[0];
    let thumbnailTwoTopRight = inputFileThumbnailTwoTopRight.files[0];
    let thumbnailTwoSideWords = inputFileThumbnailTwoSideWords.files[0];
    let endPhoto = inputFileEndPhoto.files[0];


    if (logo.size > size){
        event.preventDefault();
        errorImage("error-logo", "input-logo");
        return false;
    } else if (picFromSky.size > size) {
        event.preventDefault();
        errorImage("error-picFromSky", "input-picFromSky");
        return false;
    } else if (compMap.size > size) {
        event.preventDefault();
        errorImage("error-compMap", "input-compMap");
        return false;
    } else if (ceoPhoto.size > size) {
        event.preventDefault();
        errorImage("error-ceo-photo", "input-ceo-photo");
        return false;
    } else if (thumbnailOneTopLeft.size > size) {
        event.preventDefault();
        errorImage("error-ThumbnailOneTopLeft", "input-ThumbnailOneTopLeft");
        return false;
    } else if (thumbnailOneTopMiddle.size > size) {
        event.preventDefault();
        errorImage("error-ThumbnailOneTopMiddle", "input-ThumbnailOneTopMiddle");
        return false;
    } else if (thumbnailOneTopRight.size > size) {
        event.preventDefault();
        errorImage("error-ThumbnailOneTopRight", "input-ThumbnailOneTopRight");
        return false;
    } else if (thumbnailSideWords.size > size) {
        event.preventDefault();
        errorImage("error-ThumbnailSideWords", "input-ThumbnailSideWords");
        return false;
    } else if (thumbnailTwoTopLeft.size > size) {
        event.preventDefault();
        errorImage("error-ThumbnailTwoTopLeft", "input-ThumbnailTwoTopLeft");
        return false;
    } else if (thumbnailTwoTopMiddle.size > size) {
        event.preventDefault();
        errorImage("error-ThumbnailTwoTopMiddle", "input-ThumbnailTwoTopMiddle");
        return false;
    } else if (thumbnailTwoTopRight.size > size) {
        event.preventDefault();
        errorImage("error-ThumbnailTwoTopRight", "input-ThumbnailTwoTopRight");
        return false;
    } else if (thumbnailTwoSideWords.size > size) {
        event.preventDefault();
        errorImage("error-ThumbnailTwoSideWords", "input-ThumbnailTwoSideWords");
        return false;
    } else if (endPhoto.size > size) {
        event.preventDefault();
        errorImage("error-EndPhoto", "input-EndPhoto");
        return false;
    }
    return  true;
});

function errorImage(id, input) {
    let span = document.getElementById(id);
    let inputFile = document.getElementById(input);
    inputFile.classList.remove("valid");
    inputFile.classList.add("invalid");
    span.innerText = "The size of the image must be less than 5MB";
    span.style.color = "red";
}