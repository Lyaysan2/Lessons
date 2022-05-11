<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>
    <link href="/front/css/menu.css" rel="stylesheet">
    <link href="/front/css/addBook.css" rel="stylesheet">
</head>
<body>
<jsp:include page="menu.jsp" />

<form method="post" action="/addBook" enctype="multipart/form-data" class="block">

        <div class="title">Добавить книгу!</div>

        <div class="subheadings">Название книги</div>
        <input id="name" name="name" placeholder="Название" class="input">

        <div class="subheadings">Автор</div>
        <input id="author" name="author" placeholder="Автор" class="input">

        <div class="subheadings">Год издания книги</div>
        <input id="year" name="year" placeholder="Год" class="input">

        <div class="subheadings">Краткое описание</div>
        <input id="description" type="text" name="description" placeholder="Описание" class="input">

        <div class="subheadings">Цена</div>
        <input id="price" name="price" placeholder="Цена в рублях" class="input">

        <div class="subheadings">Фото книги:</div>
        <input id="file" type="file" name="file" class="input-file">

        <input type="submit" value="Add">

</form>
</body>
</html>
