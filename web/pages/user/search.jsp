<%@ page import="com.ghstk.domain.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ghStk
  Date: 2020/11/21
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>title_test</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/css">
        table {
            width: 140px;
            border: 1px;
            border-collapse: collapse;
        }
    </script>
</head>
<body>

<%
    if (request.getAttribute("searchValue") == null) {
        request.setAttribute("searchValue", "请输入用户名");
    }
%>
<h1>用户查询</h1><br>
<form action="userServlet" method="post">
    <input type="hidden" name="action" value="search">
    <input type="text" name="searchValue" autofocus="autofocus" onfocus="this.select()"
           value=${requestScope.searchValue}>
    <input type="submit" value="查询"><br>
</form>
<table>
    <tr style="align-content: center">
        <td>用户名</td>
        <td>密码</td>
        <td>邮箱</td>
    </tr>
    <%
        User user = (User) request.getAttribute("user");
        if (user != null) {
    %>

    <tr>
        <td><%=user.getUsername()%>
        </td>
        <td><%=user.getPassword()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
    </tr>

    <%} else {%>

    <tr>
        <td>nul</td>
        <td>nul</td>
        <td>nul</td>
    </tr>

    <%}%>

</table>

<hr>
<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>
