<%--
  Created by IntelliJ IDEA.
  User: jeongdaun
  Date: 2021/08/14
  Time: 10:10 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="uploadFormAction" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile" multiple>
    <button>submit</button>
</form>
</body>
</html>
