<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>/sample/all page</h1>


<sec:authorize access="isAnonymous()">
    <a href="/login">로그인</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <a href="/logout">로그아웃</a>
</sec:authorize>
</body>
</html>
