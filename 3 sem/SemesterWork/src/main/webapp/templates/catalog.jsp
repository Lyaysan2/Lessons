<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalog</title>

    <link href="/front/css/menu.css" rel="stylesheet">
    <link href="/front/css/catalog.css" rel="stylesheet">
</head>
<body>
<jsp:include page="menu.jsp" />

    <c:forEach items="${allBook}" var="allBook">
            <div class="product">
                <div class="image">
                    <c:if test="${! (allBook.imageId == 0)}">
                        <img class="image" alt="IMAGE" src="/files/${allBook.imageId}" />
                    </c:if>
                    <c:if test="${allBook.imageId == 0}">
                        <img class="image" alt="IMAGE" src="/img/no-book.png" />
                    </c:if>
                </div>
                <div class="info">
                    <div class="price">${allBook.price} руб</div>
                    <a href="/bookInfo?bookId=${allBook.id}" class="name">${allBook.name}</a>
                    <div class="author">${allBook.author}</div>
                </div>
            </div>
    </c:forEach>

</body>
</html>



