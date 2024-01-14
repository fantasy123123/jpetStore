<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog"><form action="newAccount" method="post">
	<h3>User Information</h3>

	<table id="accountform">

		<tr>
			<td>User ID:</td>
			<td><input type="text" name="username" id="username"  onblur="usernameIsExist();"/></td>
		</tr>
		    <!--提醒消息-->
			&nbsp;&nbsp;&nbsp;&nbsp;<span id="isExistInfo1"></span><br/>
		</tr>
		<tr>
			<td>New password:</td>
			<td><input type="password" name="password" id="password"/></td>
		</tr>
		<tr>
			<td>Repeat password:</td>
			<td><input type="password" name="repeatedPassword" id="repeatedPassword" onblur="checkPassword()"/></td>
		</tr>
	</table>
	&nbsp;&nbsp;&nbsp;&nbsp;<span id="isExistInfo2"></span><br/>
	<script type="text/javascript" src="../../../js/register.js"></script>
	<%@ include file="IncludeAccountFields.jsp"%>
	<br/>
	<input type="submit"  name="newAccount" value="Sign Up" />

</form></div>

<%@ include file="../common/IncludeBottom.jsp"%>