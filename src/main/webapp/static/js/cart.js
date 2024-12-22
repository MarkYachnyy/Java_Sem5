DivContent = $(".content")[0];

redirectIfBadCredentials("/login", loadCartData);
console.log("evil joe");

function loadCartData(){

    $.ajax({
        url:'/api/cart',
        method:'get',
        headers:{
            "email":getCookie("email"),
            "password_hash": getCookie("password_hash")
        },
        contentType : 'application/json',
        success : response => {
            setCartListHtml(response);
        }
    });
}

function setCartListHtml(item_list){
    for(let item of item_list){
        DivContent.innerHTML += makeCartItemHTML(item);
    }
}

function makeCartItemHTML(item){
    let html =
        `<div class="div__cart__item" id="div-cart-item-${item.id}">
            <img src="/static/images/jonkler.webp" class="image__cart__item">
            <a class="a__cart__item__product__name" href="/product?id=${item.id}">product</a>
            <button class="button__minus__one">-</button>
            <p class="p__cart__item__quantity">${item.quantity}</p>
            <button class="button__plus__one">+</button> 
        </div>`;
    return html;
}