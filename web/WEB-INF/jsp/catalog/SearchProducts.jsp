<%--
  Created by IntelliJ IDEA.
  User: 17817
  Date: 2019/10/19
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
    <a href="/main">Return to Main Menu</a>
</div>

<div id="Catalog">

    <table>
        <tr>
            <th>&nbsp;</th>
            <th><b>Product ID</b></th>
            <th><b>Name</b></th>
        </tr>
        <c:forEach var="product" items="${sessionScope.productsBySearch}">
            <tr>
                <td><a href="viewProduct?productId=${product.productId}">${product.description}</a></td>

                <td><b><a href="viewProduct?productId=${product.productId}">${product.productId}</a></b></td>

                <td><b><a href="viewProduct?productId=${product.productId}">${product.name}</a></b></td>

                <td></td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
        </tr>

    </table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>
