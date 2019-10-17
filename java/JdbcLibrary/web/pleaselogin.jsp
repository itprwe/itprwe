<%--
  Created by IntelliJ IDEA.
  User: zhengxr
  Date: 2019/10/17
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<c:if test="${ empty existUser }">
    <h3><a href="${ pageContext.request.contextPath }/login.jsp">亲，请登陆！</a></h3>
</c:if>

</body>
</html>
