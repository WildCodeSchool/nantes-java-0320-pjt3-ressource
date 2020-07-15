//Tags on each side
let tag_width_1 = document.getElementById("slider-width-tag-1");
let tag_width_2 = document.getElementById("slider-width-tag-2");
let tag_weight_1 = document.getElementById("slider-weight-tag-1");
let tag_weight_2 = document.getElementById("slider-weight-tag-2");
let sliders = document.getElementsByClassName('slider');

let radiosClicked = {};

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
        if ((this.value >= 75 && this.value <= 137 && this.id === "slider-weight")) {
            tag_width_1.innerHTML = this.value;
        } else if ((this.value >= 138 && this.value <= 200) && this.id === "slider-weight-2") {
            tag_width_2.innerHTML = this.value;
        } else if ((this.value >= 30 && this.value <= 235) && this.id === "slider-width") {
            tag_weight_1.innerHTML = this.value;
        } else if ((this.value >= 236 && this.value <= 500) && this.id === "slider-width-2") {
            tag_weight_2.innerHTML = this.value;
        }
    };

    sliders[i].addEventListener('mouseup', function () {
        filters(this, false, true);
    });

    sliders[i].addEventListener('touchend', function () {
        filters(this, false, true);
    })
}

/* FUNCTION TO INSERT EACH RADIO ON THE DICTIONARY */
function filters(radio, out, slider) {
    if (radiosClicked[radio.name] && out && !slider) {
        radiosClicked[radio.name].pop(radio.value);
    } else if (slider) {
        radiosClicked[radio.name] = radio.value;
    } else {
        if (radiosClicked[radio.name]) {
            radiosClicked[radio.name].push(radio.value);
        } else {
            radiosClicked[radio.name] = [radio.value];
        }
    }
    //console.log(radiosClicked)
    radioFilter();
}


/* color of the label when radio button clicked */

initClicking();

function initClicking() {
    let radiosPrice = document.getElementsByClassName('results-filters-radio-input-price');
    let divRadiosPrice = document.getElementsByClassName('results-filters-radio-table-price');

    let radiosCompo = document.getElementsByClassName('results-filters-radio-input-compo');
    let divRadiosCompo = document.getElementsByClassName('results-filters-radio-table-compo');

    let radiosFabric = document.getElementsByClassName('results-filters-radio-input-fabric');
    let divRadiosFabric = document.getElementsByClassName('results-filters-radio-table-fabric');

    let radiosMaterial = document.getElementsByClassName('results-filters-radio-input-material');
    let divRadiosMaterial = document.getElementsByClassName('results-filters-radio-table-material');

    let radiosOrigin = document.getElementsByClassName('results-filters-radio-input-origin');
    let divRadiosOrigin = document.getElementsByClassName('results-filters-radio-table-origin');

    let radiosSupplier = document.getElementsByClassName('results-filters-radio-input-supplier');
    let divRadiosSupplier = document.getElementsByClassName('results-filters-radio-table-supplier');

    let radiosCert = document.getElementsByClassName('results-filters-radio-input-cert');
    let divRadiosCert = document.getElementsByClassName('results-filters-radio-table-cert');
    clicking(radiosCompo, divRadiosCompo);
    clicking(radiosPrice, divRadiosPrice);
    clicking(radiosFabric, divRadiosFabric);
    clicking(radiosMaterial, divRadiosMaterial);
    clicking(radiosOrigin, divRadiosOrigin);
    clicking(radiosSupplier, divRadiosSupplier);
    clicking(radiosCert, divRadiosCert);
}

function clicking(radios, divRadios) {
    for (let i = 0; i < radios.length; i++) {
        radios[i].onclick = function () {

            if (radios[i].checked === false) {
                divRadios[i].classList.remove("clicked");
                filters(this, true, false);
            } else {
                divRadios[i].classList.add("clicked");
                filters(this, false, false);
            }

        };
    }
}

/* FETCH TO UPDATE THE RESULTS WHEN EVENT ON FILTERS */
function radioFilter() {

    let searchValue = document.getElementById("search");
    let params = "search=" + searchValue.value + "&";

    for (let radio in radiosClicked) {
        params += radio + "=" + radiosClicked[radio] + "&";
    }
    fetch('/results/filter?' + params)
        .then(function (response) {
            return response.text()
        }).then(function (content) {
        let parser = new DOMParser();
        let html = parser.parseFromString(content, 'text/html');
        let result = document.getElementById('results-products');
        result.innerHTML = html.getElementById('results-products').innerHTML;
    });
    window.history.pushState("object or string", "Results", "/results/filter?" + params + "submit=true");
}

/* FILTERS CLICK HERE TO SEE MORE */
let clickHere = document.getElementsByClassName('filters-see-more');

Array.prototype.forEach.call(clickHere, link => {
    link.onclick = function () {
        let idName;
        let className;
        let all = true;
        let less;
        if (link.id === 'more-origin') {
            idName = 'filter-origin';
            className = 'results-filters-radio-table-origin';
            less = document.getElementById('less-origin');
        } else if (link.id === 'more-composition') {
            idName = 'filter-composition';
            className = 'results-filters-radio-table-compo';
            less = document.getElementById('less-composition');
        } else if (link.id === 'more-supplier') {
            idName = 'filter-supplier';
            className = 'results-filters-radio-table-supplier';
            less = document.getElementById('less-supplier');
        } else if (link.id === 'more-certification') {
            idName = 'filter-certification';
            className = 'results-filters-radio-table-cert';
            less = document.getElementById('less-certification');
        }
        fetch('/results/more/' + link.id + '/' + all)
            .then(function (response) {
                return response.text()
            }).then(function (content) {
            let parser = new DOMParser();
            let html = parser.parseFromString(content, 'text/html');
            let result = document.getElementById(idName);
            let divs = html.getElementsByClassName(className);
            for (let i = 0; i < divs.length; i++) {
                result.append(divs[i].cloneNode(true));
            }
            initClicking();
        });

        this.style.display = 'none';
        less.style.display = 'initial';
    }
});

/* FILTERS CLICK HERE TO SEE LESS */
let clickHereLess = document.getElementsByClassName('filters-see-less');

Array.prototype.forEach.call(clickHereLess, link => {
    link.onclick = function () {
        let idName;
        let idChanged;
        let all = false;
        let filter;
        if (link.id === 'less-origin') {
            idName = 'filter-origin';
            idChanged = 'filter-more-origin';
            filter = 'more-origin';
        } else if (link.id === 'less-composition') {
            idName = 'filter-composition';
            idChanged = 'filter-more-composition';
            filter = 'more-composition';
        } else if (link.id === 'less-supplier') {
            idName = 'filter-supplier';
            idChanged = 'filter-more-supplier';
            filter = 'more-supplier'
        } else if (link.id === 'less-certification') {
            idName = 'filter-certification';
            idChanged = 'filter-more-certification';
            filter = 'more-certification';
        }

        fetch('/results/more/' + filter + '/' + all)
            .then(function (response) {
                return response.text()
            }).then(function (content) {
            let parser = new DOMParser();
            let html = parser.parseFromString(content, 'text/html');
            let result = document.getElementById(idName);
            let divs = html.getElementById(idChanged);
            result.innerHTML = divs.innerHTML;
            initClicking();
        });
        this.style.display = 'none';
        let more = document.getElementById(filter);
        more.style.display = 'initial';
    }
});


/* FILTERS OPEN  MOBILE*/
let filter = document.getElementById("mobile-filter");
let resultFilter = document.getElementById("results-filters");
let resultsResults = document.getElementById("results-results");

filter.onclick = function () {
    resultFilter.classList.toggle('open');
    resultsResults.classList.toggle('active');
    $('body').toggleClass('color');
};

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
