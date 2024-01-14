<%@ include file="../common/IncludeTop.jsp"%>

<h2 align="center">My Orders</h2>
<%--style="width: 100%;float: left"style="width:10%" --%>
<div style="width: 100%;float: left">
	<table style="color: black">
		<tr>
			<th >Order ID</th>
			<th >Date</th>
			<th >Total Price</th>
		</tr>

		<c:forEach var="order" items="${sessionScope.orderList}">
			<tr>
				<td><a href="viewOrder?orderId=${order.orderId}">${order.orderId}</a>

				</td>
				<td>
					<fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" />
				</td>
				<td>
					<fmt:formatNumber value="${order.totalPrice}" pattern="$#,##0.00" />
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<br/>
<%@ include file="../common/IncludeBottom.jsp"%>


