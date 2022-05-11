<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Book</title>

    <link href="/front/css/menu.css" rel="stylesheet">
    <link href="/front/css/bookInfo.css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/front/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/js/comments.js"></script>
    <script src="${pageContext.request.contextPath}/front/js/addToCart.js"></script>
</head>
<body>
<jsp:include page="menu.jsp" />

    <div class="card">
        <div>
            <c:if test="${! (book.imageId == 0)}">
                <img class="image" alt="IMAGE" src="/files/${book.imageId}"/>
            </c:if>
            <c:if test="${book.imageId == 0}">
                <img class="image" alt="IMAGE" src="/img/no-book.png"/>
            </c:if>
        </div>

        <div class="info">
            <h2 class = "product-title">${book.name}</h2>
            <div class = "product-detail">
                <h3>Описание книги: </h3>
                <p>${book.description}</p>
                <h3>Автор:</h3>
                <p>${book.author}</p>
                <h3>Год:</h3>
                <p>${book.year}</p>
            </div>

            <div class = "price">${book.price} руб.</div>

            <div class = "purchase-info">
                <form id="addToCart" action="${pageContext.request.contextPath}/cart" method="post">
                    <input id="toCart" name="toCart" type="submit" value="В корзину" class="btn"/>
                </form>
            </div>
        </div>
    </div>

    <div class="comments">
        <form id="comment-form" action="${pageContext.request.contextPath}/addComment" method="post">
            <div class="com-label">Ваш коментарий:</div>
            <textarea id="text" name="text" type="text" placeholder="не более 255 символов"></textarea>
            <input type="submit">
        </form>


        <div id="comments-list">
            <c:forEach items="${comments}" var="comment">
                <div class="comment-card">
                    <div>
                        <c:if test="${! (comment.user.avatarId == 0)}">
                            <img class="avatar" alt="IMAGE" src="/files/${comment.user.avatarId}"/>
                        </c:if>
                        <c:if test="${comment.user.avatarId == 0}">
                            <img class="avatar" alt="IMAGE" src="/img/no-avatar.jpg"/>
                        </c:if>
                    </div>


                    <div class="content">
                        <div class="name_date">
                            <div class="name">${comment.user.firstName}</div>
                            <div class="date"><fmt:formatDate value="${comment.createdAt}" pattern="dd.MM.yyyy, HH:mm"/></div>
                        </div>
                        <div class="comment-text">${comment.text}</div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</body>
</html>
