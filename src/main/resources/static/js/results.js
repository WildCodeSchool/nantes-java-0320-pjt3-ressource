//Tags on each side
let tag_width_1 = document.getElementById("slider-width-tag-1");
let tag_width_2 = document.getElementById("slider-width-tag-2");
let tag_weight_1 = document.getElementById("slider-weight-tag-1");
let tag_weight_2 = document.getElementById("slider-weight-tag-2");
let sliders = document.getElementsByClassName('slider');

for (let i = 0; i < sliders.length; i++) {

    if (sliders[i].id === "slider-weight") {
        tag_width_1.innerHTML = sliders[i].value;
    } else if (sliders[i].id === "slider-weight-2") {
        tag_width_2.innerHTML = sliders[i].value;
    } else if (sliders[i].id === "slider-width") {
        tag_weight_1.innerHTML = sliders[i].value;
    } else if (sliders[i].id === "slider-width-2") {
        tag_weight_2.innerHTML = sliders[i].value;
    }

    sliders[i].oninput = function () {
        if ((this.value >= 30 && this.value <= 235 && this.id === "slider-weight")) {
            tag_width_1.innerHTML = this.value;
        } else if ((this.value >= 236 && this.value <= 500) && this.id === "slider-weight-2") {
            tag_width_2.innerHTML = this.value;
        } else if ((this.value >= 75 && this.value <= 137) && this.id === "slider-width") {
            tag_weight_1.innerHTML = this.value;
        } else if ((this.value >= 138 && this.value <= 200) && this.id === "slider-width-2") {
            tag_weight_2.innerHTML = this.value;
        }
    }
}

// Sticky nav bar
$(window).on("scroll", function () {
    if ($(window).scrollTop()) {
        $('.results-header-mobile').addClass('sticky');
    } else {
        $('.results-header-mobile').removeClass('sticky');
    }
});

/* SEARCH MODIFY ON MOBILE */
let search = document.getElementById("search-icon-nav");
let navMenu = document.getElementById("results-menu-nav");
let navSearch = document.getElementById("results-nav-search");

search.onclick = function () {
    navMenu.style.display = "none";
    navSearch.classList.toggle('show');
};

/* color of the label when radio button clicked */
let radiosPrice = $('.results-filters-radio-input-price');
let divRadiosPrice = $('.results-filters-radio-table-price');
clicking(radiosPrice, divRadiosPrice);

let radiosCompo = $('.results-filters-radio-input-compo');
let divRadiosCompo = $('.results-filters-radio-table-compo');
clicking(radiosCompo, divRadiosCompo);

let radiosFabric = $('.results-filters-radio-input-fabric');
let divRadiosFabric = $('.results-filters-radio-table-fabric');
clicking(radiosFabric, divRadiosFabric);

let radiosMaterial = $('.results-filters-radio-input-material');
let divRadiosMaterial = $('.results-filters-radio-table-material');
clicking(radiosMaterial, divRadiosMaterial);

let radiosOrigin = $('.results-filters-radio-input-origin');
let divRadiosOrigin = $('.results-filters-radio-table-origin');
clicking(radiosOrigin, divRadiosOrigin);

let radiosSupplier = $('.results-filters-radio-input-supplier');
let divRadiosSupplier = $('.results-filters-radio-table-supplier');
clicking(radiosSupplier, divRadiosSupplier);

function clicking(radios, divRadios) {
    radios.click(function () {
        divRadios.removeClass("clicked");
        $(this).filter(':checked').closest(divRadios).addClass("clicked");
    });
}

/* FILTERS OPEN */
let filter = document.getElementById("mobile-filter");
let resultFilter = document.getElementById("results-filters");
let resultsResults = document.getElementById("results-results");

filter.onclick = function () {
    resultFilter.classList.toggle('open');
    resultsResults.classList.toggle('active');
    $('body').toggleClass('color');
};