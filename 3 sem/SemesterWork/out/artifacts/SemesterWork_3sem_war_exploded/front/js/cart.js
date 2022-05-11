$(document).ready(function (){
    getAllBook()
})

function getAllBook(){
    $.ajax({
        url: "/profile",
        method: "POST",
        dataType: "json",
        success: function (data){
            let tableBody = $('#block');
            tableBody.empty();
            let newBook = $("<div></div>")
            newBook.append("<tr class=\"item\">\n" +
                "                        <td>Товар</td>\n" +
                "                        <td>Автор</td>\n" +
                "                        <td>Цена</td>\n" +
                "                        <td>Удалить</td>\n" +
                "                    </tr>")

            $(data).each(function (index, element){
                newBook.append($("<tr></tr>"))
                newBook.append("<td><a href=bookInfo?bookId="+element.book.id+" class='bookName'>"+element.book.name+"</a></td>")
                newBook.append("<td><div class='bookAuthor'>"+element.book.author+"</div></td>")
                newBook.append("<td><div class='bookPrice'>"+element.book.price+" руб. </div></td>")
                newBook.append('<td><button onclick="deleteBook('+element.id+')">Delete</button></td>')
                tableBody.prepend(newBook)
            })
        },
        error: function (error){
            console.log(error);
        }
    })
}

function deleteBook(id){
    $.ajax({
        url: "/profile?id="+id,
        method: "DELETE",
        success: function (){
            getAllBook();
        },
        error: function (error){
            console.log(error);
        }
    })
}
