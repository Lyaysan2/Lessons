<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link href="/front/css/menu.css" rel="stylesheet">
    <link href="/front/css/profile.css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/front/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/js/cart.js"></script>

</head>

<body>
<jsp:include page="menu.jsp" />

<div class="all">
    <div class="profile">
        <div class="title prof">Профиль</div>
        <div class="info">

            <div>
                <c:if test="${! (user.avatarId == 0)}">
                    <img class="avatar" alt="IMAGE" src="/files/${user.avatarId}" />
                </c:if>
                <c:if test="${user.avatarId == 0}">
                    <img class="avatar" alt="IMAGE" src="/img/no-avatar.jpg" />
                </c:if>
            </div>

            <div class="user-info">
                <div class="user-info text">
                    <h5>First Name</h5>
                </div>
                <div class="user-info attr">
                    ${user.firstName}
                </div>
                <div class="user-info text">
                    <h5>Last Name</h5>
                </div>
                <div class="user-info attr">
                    ${user.lastName}
                </div>
                <div class="user-info text">
                    <h5>Email</h5>
                </div>
                <div class="user-info attr">
                    ${user.email}
                </div>
                <div class="user-info text">
                    <h5>Age</h5>
                </div>
                <div class="user-info attr">
                    ${user.age}
                </div>
                <div class="user-info text">
                    <h5>City</h5>
                </div>
                <div class="user-info attr">
                    ${user.city}
                </div>
            </div>
        </div>
    </div>

    <div class="myCart">
        <div class="title cart">
            <img class="cart-img" alt="IMAGE" src="/img/cart.JPG" />
            Корзина
        </div>

            <table id="block" class="block">

            </table>
    </div>
</div>
</body>
</html>
