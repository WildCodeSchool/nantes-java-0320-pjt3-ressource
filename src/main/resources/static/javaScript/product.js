var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function currentSlide(n) {
    showSlides(slideIndex = n);
}

function showSlides(n) {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    if (n > slides.length) {
        slideIndex = 1
    }
    if (n < 1) {
        slideIndex = slides.length
    }
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slides[slideIndex - 1].style.display = "block";
}

/* caroussel 2eme partie de page */


/* carousel 2ème partie de la page */
class Carousel {

    /**
     * @param {HTMLElement} element
     * @param{{slidesToScroll: number, slidesVisible: number}} options
     * @param{Object} options.slidesToScroll Nombre d'élément a faire defiler'
     * @param{Object} options.slidesVisibles Nombre d'étément visible dans un slide
     */
    constructor(element, options = {}) {
        this.element = element
        this.options = Object.assign({}, {
            slidesToScroll: 1,
            slidesVisible: 1
        }, options)
        let children = [].slice.call(element.children)
        this.currentItem = 0
        this.root = this.createDivWithClass('product-carousel')
        this.container = this.createDivWithClass('carousel-container')
        this.root.appendChild(this.container)
        this.element.appendChild(this.root)
        this.items = children.map((child) => {
            let item = this.createDivWithClass('product-carousel-item')
            item.appendChild(child)
            this.container.appendChild(item)
            return item
        })
        this.createNavigation()
    }

    /**
     Applique les bonnes dimmention aux élément du carousel
     */
    createNavigation (){
        let nextButton = this.createDivWithClass('carousel-next')
        let prevButton = this.createDivWithClass('carousel-prev')
        this.root.appendChild(nextButton)
        this.root.appendChild(prevButton)
        nextButton.addEventListener('click', this.next.bind(this))
        prevButton.addEventListener('click', this.prev.bind(this))
    }

    next(){
        this.gotoItem(this.currentItem + this.options.slidesToScroll)
    }

    prev(){
        this.gotoItem(this.currentItem - this.options.slidesToScroll)
    }
    /**
     * @param {number} index
     * deplace l"element vers l'élément ciblé
     */
    gotoItem (index){
        if(index <0){
            index = this.items.length - this.options.slidesVisible
        }else  if (index >= this.items.length){
            index = 0
        }
        let translateX = index * -365
        this.container.style.transform = 'translate3d(' + translateX + 'px, 0, 0)'
        this.currentItem = index
    }

    /**
     * @param {string} className
     * @returns {HTMLElement}
     */
    createDivWithClass(className) {
        let div = document.createElement('div')
        div.setAttribute('class', className)
        return div

    }
}

document.addEventListener('DOMContentLoaded', function () {

    new Carousel(document.querySelector('.product-carousel-bot'), {
        slidesToScroll: 1,
    })

    new Carousel(document.querySelector('.product-carousel-bot2'), {
        slidesToScroll: 1,
    })
})

