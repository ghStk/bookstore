<%--
  Created by IntelliJ IDEA.
  User: ghStk
  Date: 2020/11/26
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $("#search").click(function () {
            var pageNo = $("#pn_input").val();
            location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
        });
    });
</script>

<div id="page_nav">
    <%--如果不在首页,则显示首页和上一页--%>
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1" >首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>
    <c:if test="${requestScope.page.pageNo==1}">
        <a>首页</a>
        <a>上一页</a>
    </c:if>

    <%--确定显示的页码范围--%>
    <c:choose>
        <%--总页数小于等于5--%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%--总页数大于5--%>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <%--当前页小于等于3--%>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--当前页在后3页中--%>
                <c:when test="${requestScope.page.pageNo>=(requestScope.page.pageTotal-2)}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <%--其他情况--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <%--输出显示的范围--%>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i==requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i!=requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <%--如果不在最后一页,则显示最后一页和上一页--%>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    <c:if test="${requestScope.page.pageNo>=requestScope.page.pageTotal}">
        <a>下一页</a>
        <a>末页</a>
    </c:if>

    <%--其他信息--%>
    共${requestScope.page.pageTotal}页,${requestScope.page.itemCount}条记录

    <%--跳转到其他页--%>
    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" value="确定" id="search">
</div>
<br>
</div>