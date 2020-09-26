<%--
  Created by IntelliJ IDEA.
  User: angelong
  Date: 2020/9/26
  Time: 下午1:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>save</title>
</head>
<body>
    <h1>Doing save</h1>
    <form method="post" action="/save">
        <input type="text" name="id"><br>
        <input type="text" name="name"><br>
        <input type="text" name="money"><br>
        <input type="submit" value="save">
    </form>
</body>
</html>
