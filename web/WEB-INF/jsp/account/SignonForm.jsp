<%@ include file="../common/IncludeTop.jsp"%>

<div id="sign-in-container">

	<div id="demo" class="carousel slide myCaroudel " data-ride="carousel">

		<!-- 指示符 -->
		<ul class="carousel-indicators">
			<li data-target="#demo" data-slide-to="0" class="active"></li>
			<li data-target="#demo" data-slide-to="1"></li>
			<li data-target="#demo" data-slide-to="2"></li>
			<li data-target="#demo" data-slide-to="3"></li>
			<li data-target="#demo" data-slide-to="4"></li>
		</ul>

		<!-- 轮播图片 -->
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="images/cat-signin.jpg">
			</div>
			<div class="carousel-item">
				<img src="images/songshu.jpg">
			</div>
			<div class="carousel-item">
				<img src="images/cat_index.png">
			</div>
			<div class="carousel-item">
				<img src="images/bird-signin.jpg">
			</div>
			<div class="carousel-item">
				<img src="images/cat-signin2.jpg">
			</div>
		</div>

		<!-- 左右切换按钮 -->
		<a class="carousel-control-prev" href="#demo" data-slide="prev">
			<span class="carousel-control-prev-icon"></span>
		</a>
		<a class="carousel-control-next" href="#demo" data-slide="next">
			<span class="carousel-control-next-icon"></span>
		</a>

	</div>

	<div>
		<form id="loginForm" action = "login" method="post" >

			<fieldset  class="Sign-in-Field">
				<legend>Sign In Now</legend>
				<div class="login-form">
					<label for="username" >Username:</label>
					<input type="text" class="input-box" id="username" name="username" placeholder="Username" required autofocus/><br/>
					&nbsp;&nbsp;&nbsp;&nbsp;<span id="isExistInfo"></span><br/>
					<label for="password" >Password:</label>
					<input type="password" id="password" name="password" class="input-box" placeholder="Password" required/><br/>

                    <br>
					<dl>
						<dd><input name="phone" type="text" id="phone" placeholder="Your phone number"/></dd>
					</dl>

					<br>
					<dl>
						<dd><input type="text" id="code" name="code" onkeydown="enterHandler(event)" style="width: 133px;"
								   placeholder="SMS verification code"/><input type="button" id="zphone" class="btn_mfyzm" value="Get SMS verification by pressing" /></dd>
					</dl>
					<br>
					<p style="font-size: small">Need a username ?
						<a href="newAccountForm">Register Now!</a></p>
					<button class="submit-Btn" id="dynamicLogon" >Sign In</button><br/>
				</div>
			</fieldset>
		</form>
	</div>

</div>

<div align="center">
	<div id="slideBar"></div>
</div>
<script type="text/javascript" src="../../../js/jquery-3.4.1.js"></script>
<script src="../../../js/jquery-1.10.2.js"></script>
<script src="../../../js/dialog.js"></script>
<script type="text/javascript" src="/js/SignIn.js"></script>


<script type="text/javascript">
	$(function () {
		$("#zphone").click(function () {
			//先判断是否输入手机号码，再判断手机号格式是否正确，最后判断是否是已经注册过的手机号
			var phone=$("#phone").val();
			if(phone==null||phone==""){
				// $("#msg").text("手机号不能为空").css("color","red");
				Tip('手机号不能为空！');
				$("#phone").focus();
			}else if(!(/^1[3456789]\d{9}$/.test(phone))){
				// $("#msg").text("手机号格式不正确").css("color","red");
				Tip('手机号格式不正确！');
				$("#phone").focus();
			}else{
				$.ajax({
					url:'user/checkPhone',
					type:'post',
					data:{"phone":phone},
					dataType:'json',
					success:function (data) {
						if(data){
							//发送验证码
							alert("通过啦！准备执行发送验证码方法");
							sendCode(phone);
						}else{
							Tip('手机号尚未注册，请注册后再登陆!')
							// $("#msg").html("<a href='login.html'>手机号尚未注册，请注册后再登陆</a>");
						}
					}
				});
			}
		})

		//登陆
		$("#dynamicLogon").click(function () {
			var phone=$("#phone").val();
			var code=$("#code").val();
			$.ajax({
				url:'toPhoneLogin',
				type:'post',
				data:{"code":code,"phone":phone},
				dataType:'json',
				success:function (data) {
					if(data){
						//登陆成功，网站首页
						window.location.href="home";
					}else{
						//失败
						Tip('登陆失败，请重试')
						// $("#msg").text("登陆失败，请重试").css("color","red");
					}
				}
			})
		});

	})

	function sendCode(phone) {
		$.ajax({
			url:'sendCode',
			type:'post',
			data:{"phone":phone},
			dataType:'json',
			success:function (data) {
				if(data){
					// $("#msg").text("验证码发送成功").css("color","green");
					Tip('验证码发送成功！');
				}else{
					// $("#msg").text("验证码发送失败，请重试").css("color","red");
					Tip('验证码发送失败，请重试！');
				}
			}

		})
	}
</script>

<%@ include file="../common/IncludeBottom.jsp"%>

