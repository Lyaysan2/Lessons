<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Book</title>
    <link href="/front/css/menu.css" rel="stylesheet">
    <link href="/front/css/myBooks.css" rel="stylesheet">
</head>
<body>
<jsp:include page="menu.jsp" />

<table>
    <tr class="head">
        <th>Id</th>
        <th>Name</th>
        <th>Author</th>
        <th>Year</th>
        <th>Description</th>
    </tr>
    <c:forEach items="${allMyBook}" var="allMyBook">
        <tr>
            <td>${allMyBook.id}</td>
            <td><a href="/bookInfo?bookId=${allMyBook.id}">${allMyBook.name}</a></td>
            <td>${allMyBook.author}</td>
            <td>${allMyBook.year}</td>
            <td>${allMyBook.description}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
