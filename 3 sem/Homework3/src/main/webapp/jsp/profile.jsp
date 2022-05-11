<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>

    <style>
        .avatar {
            width: 200px;
            height: 200px;
            display: inline;
            border-radius: 100px;
            justify-content: center;
        }
    </style>
</head>
<body>
<h1>Profile</h1>
<h3>User: ${firstName} ${lastName}</h3>
<h3>Email: ${email}</h3>
<h3>Subject: ${subject}</h3>
<c:if test="${! (avatar_id == 0)}">
    <img class="avatar" alt="IMAGE" src="/files/${avatar_id}" />
</c:if>
<c:if test="${avatar_id == 0}">
    <img class="avatar" alt="IMAGE" src="/no-avatar.jpg" />
</c:if>
</body>
</html>

