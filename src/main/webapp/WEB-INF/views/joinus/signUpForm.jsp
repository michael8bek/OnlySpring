<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<div class="signUpContain">
		<!-- 로그인 폼 시작 -->	
		<div class="login-signup l-attop" id="login">
			<div class="login-signup-title">LOG IN</div>
			<c:choose>
			<c:when test="${not empty signUpResult }">
				<script type="text/javascript">alert("${signUpResult}");</script>
			</c:when>
			</c:choose>
			
			<c:choose>
			<c:when test="${not empty signUpResult }">
				<script type="text/javascript">alert("${signUpResult}");</script>
			</c:when>
			</c:choose>	
			<div class="login-signup-content">
				<div class="input-name">
					<h2>ID</h2>
				</div>
				<form action="login" name="loginForm" method="post">
					<input type="text" name="member_id" value="" class="field-input"
						id="login_inputId" autofocus="autofocus" />
					<div class="input-name input-margin">
						<h2>Password</h2>
					</div>
					<input type="password" name="password" value="" class="field-input" />
					<div class="input-r">
						<div class="check-input">
							<input type="checkbox" id="remember-me-2" name="rememberme"
								value="" class="checkme"> <label class="remmeberme-blue"
								for="remember-me-2"></label>
						</div>
						<div class="rememberme">
							<label for="remember-me-2">Remember Me</label>
						</div>
					</div>
					<button class="submit-btn sub-before">Enter</button>
				</form>
				<button type="submit" class="submit-btn sub-before" id="signup-btn">SignUp</button>
				<div class="forgot-pass">
					<a href="#">Forgot Password?</a>
				</div>
			</div>
		</div>
		<!-- 로그인 폼 끝 -->
		<!-- 회원가입 폼 시작 -->
		<div class="login-signup s-atbottom" id="signup">
			<div class="login-signup-title">SIGN UP</div>
			<div class="login-signup-content">
				<!-- <form action="signUp.jsp" method="post"> -->
				<form action="signUp" method="post">
					<div class="input-name">
						<h2>ID</h2>
					</div>
					<input type="text" name="userid" value=""
						class="field-input signup_id" id="member_id" onkeyup="checkId()" />
					<div id="checkMsg">아이디를 입력하세요</div>
					<div class="input-name input-margin">
						<h2>Password</h2>
					</div>
					<input type="password" name="password" value=""
						class="field-input signup_pwd" id="password" />
					<div class="input-name input-margin">
						<h2>Nickname</h2>
					</div>
					<input type="text" name="username" value=""
						class="field-input signup_nick" id="nickname" />
					<div class="input-name input-margin">
						<h2>E-Mail</h2>
					</div>

					<input type="email" name="email" value=""
						class="field-input signup_mail" id="email" />
					<button class="submit-btn sub-before" id="signup_fin"
						disabled="disabled">Enter</button>
				</form>
				<button class="submit-btn sub-before" id="login-btn">Login</button>
			</div>
		</div>
		<!-- 회원가입 폼 끝 -->
	</div>
</body>
</html>