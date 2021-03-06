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
<script type="text/javascript" src="resources/js/login.js"></script>
</head>
<body class="bg-gradient-primary"
	style="min-width: 1000px; max-width: 1920px;">

	<!-- 비정상 접근 차단 -->
	<jsp:include page="notTheWay.jsp"></jsp:include>

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<input type="button" class="h2 text-gray-900 mb-4"
											style="border: none; background-color: white;"
											onclick="location.href='/cocoa/'" value="CoCoa">
									</div>

									<div class="form-group">
										<input type="text" class="form-control form-control-user"
											id="inputID" name="id" aria-describedby="emailHelp"
											placeholder="ID">
									</div>
									<div class="form-group">
										<input type="password" class="form-control form-control-user"
											id="inputPassword" name="pwd" placeholder="Password">
									</div>
									<div class="form-group">
										<div class="custom-control custom-checkbox small">
											<input type="checkbox" class="custom-control-input"
												id="rememberMe" name="rememberMe"> <label
												class="custom-control-label" for="customCheck">Remember
												Me</label>
										</div>
									</div>
									<input type="button" value="Login" id="login"
										class="btn btn-primary btn-user btn-block">
									<hr>
									<a href="#" class="btn btn-google btn-user btn-block"> <i
										class="fab fa-google fa-fw"></i> Login with Google
									</a> <a href="#" class="btn btn-facebook btn-user btn-block"> <i
										class="fab fa-facebook-f fa-fw"></i> Login with Facebook
									</a>

									<hr>
									<div class="text-center">
										<a class="small" href="#">Forgot Password?</a>
									</div>
									<div class="text-center">
										<a class="small" href="/cocoa/goSignUp">Create an Account!</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 참조-->
	<script src="resources/vendor/jquery/jquery.min.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<script src="resources/js/sb-admin-2.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>

</body>
</html>