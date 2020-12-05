<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>

    <script type="text/javascript">
        $(function () {
            //修改商品数量
            $(".updateCount").change(function () {
                var id = $(this).attr("bookId");
                var count = $(this).attr("value");
                var thisObj = $(this);
                $.getJSON("${basePath}cartServlet?action=ajaxUpdateItem&id=" + id + "&count=" + count,
                    function (data) {
                        if (data.status == 0) {
                            //修改该商品的数量
                            $("#item_totalPrice_" + id).html(data.itemTotalPrice);
                            thisObj.attr("originalVal",count);
                            //修改cart总价和总数
                            $("#cart_totalCount").html(data.cartTotalCount);
                            $("#cart_totalPrice").html(data.cartTotalPrice);
                        } else {
                            //商品数量改回原值
                            $("#item_totalPrice_" + id).html(thisObj.attr("originalVal"));
                        }
                    }
                );
            });
        });
    </script>
</head>

<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>

    <%--如果未登录--%>
    <c:if test="${ empty sessionScope.loginUserName}">
        <div>
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/register.jsp">注册</a>&nbsp;&nbsp;
            <a href="index.jsp">返回</a>
        </div>
    </c:if>
    <%--如果已经登录--%>
    <c:if test="${not empty sessionScope.loginUserName}">
        <%--静态包含，登录成功之后的菜单 --%>
        <%@ include file="/pages/common/login_success_menu.jsp" %>
    </c:if>
</div>

<div id="main">
    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>

        <%--如果购物车为空--%>
        <c:if test="${empty sessionScope.cart.items}">
            <tr>
                <td colspan="5">亲,你没加购物车这怎么会有东西?懂?</td>
            </tr>
            <tr>
                <td colspan="5">
                    <a href="${bathPath}" style="font-size: 18px">赶紧去添加购物车</a>
                </td>
            </tr>
        </c:if>
        <%--如果购物车不为空--%>
        <c:if test="${not empty sessionScope.cart.items}">
            <c:forEach items="${sessionScope.cart.items}" var="item">
                <%--item是一个<id,CartItem>键值对--%>
                <tr>
                        <%--商品名称--%>
                    <td>${item.value.name}</td>
                        <%--商品数量--%>
                    <td>
                        <input type="text" class="updateCount" style="width: 60px"
                               bookId="${item.value.id}" value="${item.value.count}" originalVal="${item.value.count}">
                    </td>
                        <%--商品价格--%>
                    <td>${item.value.price}</td>
                        <%--商品总价--%>
                    <td id="item_totalPrice_${item.value.id}">
                            ${item.value.totalPrice}
                    </td>
                    <td><a href="cartServlet?action=deleteItem&id=${item.value.id}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

    <%--购物车总结--%>
    <div class="cart_info">
        <c:if test="${not empty sessionScope.cart.items}">
            <span class="cart_span">购物车中共有
                <span id="cart_totalCount" class="b_count">${sessionScope.cart.totalCount}</span>件商品
            </span>
            <span class="cart_span">
                总金额<span id="cart_totalPrice" class="b_price">${sessionScope.cart.totalPrice}</span>元
            </span>
            <span class="cart_span"><a href="cartServlet?action=clearCart">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </c:if>
    </div>

</div>


<%--静态包含页脚内容--%>
<%@ include file="/pages/common/footer.jsp" %>


</body>
</html>