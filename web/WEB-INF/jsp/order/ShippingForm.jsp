<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
	<form action="newOrder?newOrderFormSubmited=true" method="post">

		<table>
			<tr>
				<th colspan=2>Payment Details</th>
			</tr>
			<tr>
				<td>Card Type:</td>
				<td>
					<select name="order.cardType">
						<option value="Visa">Visa</option>
						<option value="wechat">wechat</option>
						<option value="alipay">alipay</option>
						<option value="others">others</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Card Number:</td>
				<td>
					<input type="text" name="order.creditCard" value="${sessionScope.order.creditCard}"/> * Use a fake number!
				</td>
			</tr>
			<tr>
				<td>Expiry Date (MM/YYYY):</td>
				<td>
					<input type="text" name="order.expiryDate" value="${sessionScope.order.expiryDate}"/>
				</td>
			</tr>
			<tr>
				<th colspan=2>Billing Address</th>
			</tr>

			<tr>
				<td>First name:</td>
				<td><input type="text" name="order.billToFirstName" value="${sessionScope.account.firstName}"/></td>
			</tr>
			<tr>
				<td>Last name:</td>
				<td><input type="text" name="order.billToLastName" value="${sessionScope.account.lastName}"/></td>
			</tr>
			<tr>
				<td>Address 1:</td>
				<td><input type="text" size="40" name="order.billAddress1" value="${sessionScope.account.address1}"/></td>
			</tr>
			<tr>
				<td>Address 2:</td>
				<td><input type="text" size="40" name="order.billAddress2" value="${sessionScope.account.address2}"/></td>
			</tr>
			<tr>
				<td>City:</td>
				<td><input type="text" name="order.billCity" value="${sessionScope.account.city}"/></td>
			</tr>
			<tr>
				<td>State:</td>
				<td><input type="text" size="40" name="order.billState" value="${sessionScope.account.state}"/></td>
			</tr>
			<tr>
				<td>Zip:</td>
				<td><input type="text" size="40" name="order.billZip" value="${sessionScope.account.zip}"/></td>
			</tr>
			<tr>
				<td>Country:</td>
				<td><input type="text" size="40" name="order.billCountry" value="${sessionScope.account.country}"/></td>
			</tr>

			<tr>
				<td colspan=2>
					<input type="checkbox" name="shippingAddressRequired" />
					Ship to different address...
				</td>
			</tr>

		</table>
		<br/>

		<input type="submit" name="newOrder" value="Continue" />

	</form></div>

<%@ include file="../common/IncludeBottom.jsp"%>