<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>CoCoa</title>
<link href="resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link href="resources/css/sb-admin-2.min.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="resources/js/signUp.js"></script>
<script type="text/javascript" src="resources/js/autoHyphen.js"></script>
</head>
<body class="bg-gradient-primary" style="min-width: 1000px; max-width: 1920px;">
	<div class="container">
		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<input type="button" class="h2 text-gray-900 mb-4"
									style="border: none; background-color: white;"
									onclick="location.href='/cocoa/'" value="CoCoa">
							</div>

							<!-- 회원가입 폼 -->
							<form class="user" action="/cocoa/signUp" method="post"
								id="signUp">

								<!-- 아이디 -->
								<div class="form-group row">
									<div class="col-sm-9">
										<input type="text" class="form-control form-control-user"
											id="id" name="id" placeholder="ID">
									</div>
									<div class="col-sm-3">
										<input type="button"
											class="btn btn-primary btn-user btn-block" id="idCheck"
											name="idCheck" value="중복확인">
									</div>
								</div>

								<!-- 비밀번호 확인 -->
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="password" class="form-control form-control-user"
											id="pwd1" name="pwd1" placeholder="Password">
									</div>
									<div class="col-sm-6">
										<input type="password" class="form-control form-control-user"
											id="pwd2" name="pwd2" placeholder="Repeat Password">
									</div>
								</div>

								<!-- 비밀번호 확인 멘트 -->
								<div class="form-group" style="padding-left: 15px;">
									<span id="alert-success"
										style="display: none; color: green; font-size: 13px;">
										<i class="fas fa-check"></i> 비밀번호 일치
									</span> <span id="alert-danger"
										style="display: none; color: red; font-size: 13px;"> <i
										class="fas fa-times"></i> 비밀번호 불일치
									</span>
								</div>

								<!-- 이름 -->
								<div class="form-group">
									<input type="text" class="form-control form-control-user"
										id="name" name="name" placeholder="Name">
								</div>

								<!-- 전화번호 -->
								<div class="form-group">
									<input type="tel" class="form-control form-control-user"
										id="phone" name="phone" required
										pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" maxlength="13"
										placeholder="Phone (ex)010-1234-5678">
								</div>

								<!-- 회원가입 버튼 -->
								<input type="submit" id="register"
									class="btn btn-primary btn-user btn-block"
									value="Register Account">

								<hr>
								<a href="#" class="btn btn-google btn-user btn-block"> <i
									class="fab fa-google fa-fw"></i> Register with Google
								</a> <a href="#" class="btn btn-facebook btn-user btn-block"> <i
									class="fab fa-facebook-f fa-fw"></i> Register with Facebook
								</a>
							</form>

							<hr>
							<div class="text-center">
								<a class="small" href="#">Forgot Password?</a>
							</div>
							<div class="text-center">
								<a class="small" href="/cocoa/goLogin">Already have an
									account? Login!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 참조 -->
	<script src="resources/vendor/jquery/jquery.min.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<script src="resources/js/sb-admin-2.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>

</body>
</html>