$(document).ready(function (){
    let button = $("#addToCart")
    button.on('submit', function (){
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/cart",
            headers: {
                'Content-Type':'application/x-www-form-urlencoded'
            },
            success: function (){
                document.querySelector('#toCart').style.color = "#045E04FF"
                button.find("#toCart").val("Добавлено")
            }
        })
        return false
    })
})


