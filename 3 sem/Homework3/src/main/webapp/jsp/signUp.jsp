<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="/sign-up" method="post" enctype="multipart/form-data">
    <input id="firstName" type="text" name="firstName" placeholder="firstName"/>
    <input id="lastName" type="text" name="lastName" placeholder="lastName"/>
    <input id="email" type="text" name="email" placeholder="email"/>
    <input id="subject" type="text" name="subject" placeholder="subject"/>
    <input id="password" type="password" name="password" placeholder="password"/>
    <input id="file" type="file" name="file">
    <input type="submit">
</form>
</body>
</html>

