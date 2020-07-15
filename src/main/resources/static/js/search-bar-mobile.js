/* SEARCH MODIFY ON MOBILE */
let search = document.getElementById("search-icon-nav");
let navMenu = document.getElementById("results-menu-nav");
let navSearch = document.getElementById("results-nav-search");
let menuBack = document.getElementById("menu-nav-arrow-left");

search.onclick = function () {
    hideShow();
};

menuBack.onclick = function () {
    hideShow();
};

function hideShow() {
    navMenu.classList.toggle('hide');
    navSearch.classList.toggle('show');
}