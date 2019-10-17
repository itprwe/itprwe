<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhengxr
  Date: 2019/10/17
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:if test="${ empty existUser and not empty tips}">
    用户名或密码错误，请重新登录<br/>
</c:if>

<form action="LoginServlet" method="post">
    用户名：<input type="text" name="username"/><br/>
    密码：<input type="text" name="password"/><br/>
    <input type="checkbox" name="autologin" value="auto_ok"/>自动登录<br/>
    <input type="submit" value="登陆"/>
</form>
</body>
</html>
