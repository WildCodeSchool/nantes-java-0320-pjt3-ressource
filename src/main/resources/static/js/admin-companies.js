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



form.addEventListener("submit", function (event) {

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

    if ((logo && logo.size > size) || (picFromSky && picFromSky.size > size) || (compMap && compMap.size > size) ||
        (ceoPhoto && ceoPhoto.size > size) || (thumbnailOneTopLeft && thumbnailOneTopLeft.size > size) ||
        (thumbnailOneTopMiddle && thumbnailOneTopMiddle.size > size) ||
        (thumbnailOneTopRight && thumbnailOneTopRight.size > size) || (thumbnailSideWords && thumbnailSideWords.size > size) ||
        (thumbnailTwoTopLeft && thumbnailTwoTopLeft.size > size) || (thumbnailTwoTopMiddle && thumbnailTwoTopMiddle.size > size) ||
        (thumbnailTwoTopRight && thumbnailTwoTopRight.size > size) || (thumbnailTwoSideWords && thumbnailTwoSideWords.size > size) ||
        (endPhoto && endPhoto.size > size)) {

        event.preventDefault();
        if (logo && logo.size > size){
            errorImage("input-logo");
        } else if (picFromSky && picFromSky.size > size) {
            errorImage("input-picFromSky");
        } else if (compMap && compMap.size > size) {
            errorImage("input-compMap");
        } else if (ceoPhoto && ceoPhoto.size > size) {
            errorImage("input-ceo-photo");
        } else if (thumbnailOneTopLeft && thumbnailOneTopLeft.size > size) {
            errorImage("input-ThumbnailOneTopLeft");
        } else if (thumbnailOneTopMiddle && thumbnailOneTopMiddle.size > size) {
            errorImage("input-ThumbnailOneTopMiddle");
        } else if (thumbnailOneTopRight && thumbnailOneTopRight.size > size) {
            errorImage("input-ThumbnailOneTopRight");
        } else if (thumbnailSideWords && thumbnailSideWords.size > size) {
            errorImage("input-ThumbnailSideWords");
        } else if (thumbnailTwoTopLeft && thumbnailTwoTopLeft.size > size) {
            errorImage("input-ThumbnailTwoTopLeft");
        } else if (thumbnailTwoTopMiddle && thumbnailTwoTopMiddle.size > size) {
            errorImage("input-ThumbnailTwoTopMiddle");
        } else if (thumbnailTwoTopRight && thumbnailTwoTopRight.size > size) {
            errorImage("input-ThumbnailTwoTopRight");
        } else if (thumbnailTwoSideWords && thumbnailTwoSideWords.size > size) {
            errorImage("input-ThumbnailTwoSideWords");
        } else if (endPhoto && endPhoto.size > size) {
            errorImage("input-EndPhoto");
        }
        return false;
    }
    return  true;
});

function errorImage(input) {

    let inputFile = document.getElementById(input);
    inputFile.classList.remove("valid");
    inputFile.classList.add("invalid");
}