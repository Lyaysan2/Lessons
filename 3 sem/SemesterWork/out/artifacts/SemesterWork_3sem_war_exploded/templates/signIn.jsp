<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
    <link href="/front/css/sign.css" rel="stylesheet">
</head>
<body>
<form method="post" action="/signIn" class="box">
    <h1>Sign In</h1>
    <input id="email" type="email" name="email" placeholder="Почта" class="input">
    <input id="password" type="password" name="password" placeholder="Пароль" class="input">
    <input type="submit" value="Sign In">

    <div class="signUp">
        <div class="signUp-text">No account?</div>
        <a href="/signUp" class="signUp-link">SignUp</a>
    </div>
</form>
</body>
</html>
