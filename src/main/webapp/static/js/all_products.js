DivContent = $(".content")[0];

$.ajax({
    url:'api/products/all',
    method:'get',
    contentType : 'application/json',
    success : response => {
        setProductListHtml(response);
    }
});

function setProductListHtml(response){
    for(let product of response){
        DivContent.innerHTML += makeProductHtml(product);
    }
}

function makeProductHtml(product){
    let html =
        `<div class="div__product">
            <img alt="product" src="/static/images/jonkler.webp" class="image__product">
            <p class="p__product__name">${product.name}</p>
            <p class="p__product__price">${product.price} р</p>
            <p class="p__product__in__stock">${product.stockQuantity} в наличии</p>
        </div>`
    return html;
}