PInCartInfo = $(".p__in__cart__data")[0];
SpanProductName = $(".span__product__name")[0];
ASellerName = $(".link__seller")[0];
ButtonPlus = $(".button__plus__one")[0];
ButtonMinus = $(".button__minus__one")[0];
ButtonAddToCart = $(".button__add__to__cart")[0];

params = new URLSearchParams(window.location.search);

function getProductInfoAjax(){
    $.ajax({
        url:`api/product?id=${params.get("id")}`,
        method:'get',
        contentType : 'application/json',
        success : product => {
            getSellerInfoAjax(product.sellerId);
            setProductHTML(product);
        }
    });
}

function getSellerInfoAjax(sellerId){
    $.ajax({
        url:`api/seller?id=${sellerId}`,
        method:'get',
        contentType : 'application/json',
        success : seller => {
            ASellerName.innerText = seller.name;
            ASellerName.href = `/seller?id=${seller.id}`;
        }
    });
}

function getInCartInfo() {
    if(getCookie("email") && getCookie("password_hash")){
        $.ajax({
            url:`api/cart?id=${params.get(id)}`,
            method:'get',
            contentType : 'application/json',
            success : ProcessServerResponse
        });
    }
}

ASellerName.innerText = "nigga";

function setProductHTML(product){
    SpanProductName.innerText = product.name;
}

getProductInfoAjax();