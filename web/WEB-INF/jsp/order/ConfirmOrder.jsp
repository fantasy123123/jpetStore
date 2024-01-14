<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">Please confirm the information below and then
	press continue...
	<form action="newOrder?confirmed=true" method="post">
		<table>
			<tr>
				<th align="center" colspan="2"><font size="4"><b>Order</b></font>
					<br />
					<font size="3">
						<b>
							<fmt:formatDate value="${sessionScope.order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" />
						</b>
					</font>
				</th>
			</tr>

			<tr>
				<th colspan="2">Billing Address</th>
			</tr>
			<tr>
				<td>First name:</td>
				<td><input type="text" name="order.billToFirstName" value="${sessionScope.order.billToFirstName}" /></td>
			</tr>
			<tr>
				<td>Last name:</td>
				<td><input type="text" name="order.billToLastName" value="${sessionScope.order.billToLastName}" /></td>
			</tr>
			<tr>
				<td>Address 1:</td>
				<td><input type="text" name="order.billAddress1" value="${sessionScope.order.billAddress1}" /></td>
			</tr>
			<tr>
				<td>Address 2:</td>
				<td><input type="text" name="order.billAddress2" value="${sessionScope.order.billAddress2}" /></td>
			</tr>
			<tr>
				<td>City:</td>
				<td><input type="text" name="order.billCity" value="${sessionScope.order.billCity}" /></td>
			</tr>
			<tr>
				<td>State:</td>
				<td><input type="text" name="order.billState" value="${sessionScope.order.billState}" /></td>
			</tr>
			<tr>
				<td>Zip:</td>
				<td><input type="text" name="order.billZip" value="${sessionScope.order.billZip}" /></td>
			</tr>
			<tr>
				<td>Country:</td>
				<td><input type="text" name="order.billCountry" value="${sessionScope.order.billCountry}" /></td>
			</tr>
			<c:if test="${sessionScope.shippingAddressRequired == true}">
				<tr>
					<th colspan="2">Shipping Address</th>
				</tr>
				<tr>
					<td>First name:</td>
					<td><input type="text" name="order.shipToFirstName" value="${sessionScope.order.shipToFirstName}" /></td>
				</tr>
				<tr>
					<td>Last name:</td>
					<td><input type="text" name="order.shipToLastName" value="${sessionScope.order.shipToLastName}" /></td>
				</tr>
				<tr>
					<td>Address 1:</td>
					<td><input type="text" name="order.shipAddress1" value="${sessionScope.order.shipAddress1}" /></td>
				</tr>
				<tr>
					<td>Address 2:</td>
					<td><input type="text" name="order.shipAddress2" value="${sessionScope.order.shipAddress2}" /></td>
				</tr>
				<tr>
					<td>City:</td>
					<td><input type="text" name="order.shipCity" value="${sessionScope.order.shipCity}" /></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><input type="text" name="order.shipState" value="${sessionScope.order.shipState}" /></td>
				</tr>
				<tr>
					<td>Zip:</td>
					<td><input type="text" name="order.shipZip" value="${sessionScope.order.shipZip}" /></td>
				</tr>
				<tr>
					<td>Country:</td>
					<td><input type="text" name="order.shipCountry" value="${sessionScope.order.shipCountry}" /></td>
				</tr>
			</c:if>


		</table>
		<br/>
		<input type="submit" name="newOrder" value="Confirm" />
	</form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>