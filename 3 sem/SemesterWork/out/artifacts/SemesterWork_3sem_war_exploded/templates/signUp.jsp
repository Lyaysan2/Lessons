<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link href="/front/css/sign.css" rel="stylesheet">
</head>
<body>
<form method="post" action="/signUp" enctype="multipart/form-data" class="box">
    <h1>Sign Up</h1>
    <input id="firstName" name="firstName" placeholder="Имя" class="input">
    <input id="lastName" name="lastName" placeholder="Фамилия" class="input">
    <input id="age" name="age" placeholder="Возраст" class="input">
    <input id="city" name="city" placeholder="Город" class="input">
    <input id="email" type="email" name="email" placeholder="Почта" class="input">
    <input id="password" type="password" name="password" placeholder="Пароль" class="input">
    <label for="file">Выберите аватарку:</label>
    <input id="file" type="file" name="file" class="ava-img">
    <input type="submit" value="Sign Up">
</form>
</body>
</html>
