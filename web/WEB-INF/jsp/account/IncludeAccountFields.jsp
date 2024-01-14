 <h3>Account Information</h3>

<table>
	<tr>
		<td>First name:</td>
		<td><input type="text" name="account.firstName" value="${sessionScope.account.firstName}"/></td>
	</tr>
	<tr>
		<td>Last name:</td>
		<td><input type="text" name="account.lastName" value="${sessionScope.account.lastName}"/></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><input type="text" size="40" name="account.email" value="${sessionScope.account.email}"/></td>
	</tr>
	<tr>
		<td>Phone:</td>
		<td><input type="text" size="40" name="account.phone" value="${sessionScope.account.phone}"/></td>
	</tr>
	<tr>
		<td>Address 1:</td>
		<td><input type="text" size="40" name="account.address1" value="${sessionScope.account.address1}"/></td>
	</tr>
	<tr>
		<td>Address 2:</td>
		<td><input type="text" size="40" name="account.address2" value="${sessionScope.account.address2}"/></td>
	</tr>
	<tr>
		<td>City:</td>
		<td><input type="text" size="40" name="account.city" value="${sessionScope.account.city}"/></td>
	</tr>
	<tr>
		<td>State:</td>
		<td><input type="text" size="40" name="account.state" value="${sessionScope.account.state}"/></td>
	</tr>
	<tr>
		<td>Zip:</td>
		<td><input type="text" size="40" name="account.zip" value="${sessionScope.account.zip}"/></td>
	</tr>
	<tr>
		<td>Country:</td>
		<td><input type="text" size="40" name="account.country" value="${sessionScope.account.country}"/></td>
	</tr>
</table>

<h3>Profile Information</h3>

<table>
	<tr>
		<td>Language Preference:</td>
		<td>
			<select name="account.languagePreference">
				<option value="English" selected>English</option>
				<option value="Chinese">Chinese</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>Favourite Category:</td>
		<td>
			<select name="account.favouriteCategoryId">
				<option value="Fish">FISH</option>
				<option value="Bird">BIRDS</option>
				<option value="Cat">CATS</option>
				<option value="Dog">DOGS</option>
				<option value="REPTILES">REPTILES</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>Enable MyList</td>
		<td>
			<input type="checkbox" name="account.listOption" />
		</td>
	</tr>
	<tr>
		<td>Enable MyBanner</td>
		<td><input type="checkbox" name="account.bannerOption" /></td>
	</tr>

</table>
