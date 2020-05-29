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

