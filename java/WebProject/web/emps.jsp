<%@ page import="java.util.List" %>
<%@ page import="entity.Emp" %><%--
  Created by IntelliJ IDEA.
  User: zhengxr
  Date: 2019/9/9
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工查询</title>
</head>
<body>
<table border="1" width="40%" cellspacing="0">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>职位</th>
        <th>新增</th>
    </tr>
    <%
        //转发时Servlet将数据绑定到request上，
        //并将request转发给了JSP，所以此处
        //可以直接通过request获得绑定的数据。
        List<Emp> list = (List<Emp>) request.getAttribute("emps");
        if(list!=null){
            for (Emp e : list)
            {
    %>
                <tr>
                    <td><%=e.getEmpno() %></td>
                    <td><%=e.getEmname() %></td>
                    <td><%=e.getJob() %></td>
                    <td><%=e.getSal() %></td>
                </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
