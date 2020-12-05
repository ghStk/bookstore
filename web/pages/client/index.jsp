<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            //绑定"加入购物车"按钮事件
            $("button.addToCart").click(function () {
                var bookId = $(this).attr("bookId");
                $.getJSON("${basePath}cartServlet?action=ajaxAddItem&id=" + bookId,
                    function (data) {
                        //status-0,代表正常回传
                        if (data.status == 0) {
                            $("#totalCount").html("您的购物车中有" + data.cartTotalCount + "件商品");
                            $("#latestItem").html("您刚刚将<span style=\"color: red\">" + data.latestItemName + "</span>加入到了购物车中");
                        }
                    }
                )
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <%--如果未登录--%>
    <c:if test="${ empty sessionScope.loginUserName}">
        <div>
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/register.jsp">注册</a> &nbsp;&nbsp;
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="pages/manager/manager.jsp">后台管理</a>
        </div>
    </c:if>
    <%--如果已登录--%>
    <c:if test="${not empty sessionScope.loginUserName}">
        <%@include file="/pages/common/login_success_menu.jsp" %>
    </c:if>
</div>

<div id="main">
    <div id="book">

        <%--按价格搜索--%>
        <div class="book_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>

        <%--购物车--%>
        <div style="text-align: center">
            <c:if test="${sessionScope.cart.totalCount>0}">
                <span id="totalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
                <div id="latestItem"> 您刚刚将<span style="color: red">${sessionScope.latestItemName}</span>加入到了购物车中</div>
            </c:if>
            <c:if test="${(empty sessionScope.cart)||(sessionScope.cart.totalCount==0)}">
                <span id="totalCount">你,购物车空的!</span>
                <div id="latestItem">&nbsp;</div>
            </c:if>
        </div>

        <%--开始打印列表--%>
        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button bookId="${book.id}" class="addToCart">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>

    <%--静态包含导航条--%>
    <%@include file="/pages/common/navigation_page.jsp" %>

</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>