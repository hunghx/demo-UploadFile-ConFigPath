<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/5/2023
  Time: 9:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<h1>Đây là trang hello </h1>
<h2>${id}</h2>
<h3>${name}</h3>
<h2>Form upload file</h2>
<form action="<%=request.getContextPath()%>/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="image">
    <button type="submit">Upload</button>
</form>
</body>
</html>
