<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enter</title>
</head>
<body>
<form action="/sign-in" method="post">
    <input id="email" type="text" name="email" placeholder="email"/>
    <input id="password" type="password" name="password" placeholder="password"/>
    <input type="submit">
</form>
</body>
</html>

