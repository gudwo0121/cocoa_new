<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Cocoa</title>
<link href="resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href="resources/css/sb-admin-2.min.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
</head>
<body id="page-top">

	<div id="wrapper">

		<!-- 사이드바 -->
		<jsp:include page="sideBar.jsp"></jsp:include>

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">

				<!-- 상단바 -->
				<jsp:include page="header.jsp"></jsp:include>

				<!-- 메인 콘텐츠 -->
				<div class="container-fluid">

					<!-- 헤드라인 -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Coaching Page</h1>
						<label
							class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"
							onclick="location.href='/cocoa/goCoachingWrite'">코칭 등록</label>
					</div>

					<!-- 개발툴 -->
					<div class="row">

						<!-- (개발툴) 스프링 -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-primary shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div class="h5 mb-0 font-weight-bold text-gray-800">Spring
												Framework</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-calendar fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- (개발툴) 안스튜 -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-success shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div class="h5 mb-0 font-weight-bold text-gray-800">Android
												Studio</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- (개발툴) 라즈베리파이 -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-success shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div class="h5 mb-0 font-weight-bold text-gray-800">Raspberry
												Pi</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>

				<!-- 코칭 카탈로그 구간 -->
				<section style="min-height: 50vh;">
					<div class="container px-4 px-lg-5 mt-5">

						<!-- 생성된 코칭 카탈로그 표시 -->
						<div
							class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

							<!-- 코칭 리스트 전체 조회 결과값 불러오기 -->
							<c:forEach var="coachingList" items="${coachingList}">

								<div class="col mb-5"
									style="padding-left: 30px; padding-right: 30px;">
									<div class="card h-100"
										style="width: 110%; border: 1px solid black;">

										<!-- 간판 이미지 -->
										<img class="card-img-top" alt="" height=200
											onerror="this.src='resources/img/onerror.png'" src="..." />

										<!-- 리스트 표시 정보 -->
										<div class="card-body" style="background-color: #FFCCCC;">
											<div class="text-center">

												<!-- 리더 -->
												<b style="font-size: 15px; float: left; color: grey;">${coachingList.coach}</b>

												<!-- 후기 개수 (없을시 0개) -->
												<a href="#">
													<div style="font-size: 13px; float: right;">
														<b>0개의 후기</b>
													</div> <!-- 평점 평균 (없을시 0.0) -->
													<div style="font-size: 13px; float: right;">
														<b
															style="text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black; color: yellow;">
															★&nbsp;</b> <b>0.0</b>&nbsp;|&nbsp;
													</div>
												</a> <br> <br>

												<!-- 제목 -->
												<b style="font-size: 15px; color: black;">${coachingList.cTitle}</b><br>
												<br>

												<!-- 기본 요금 -->
												<b style="font-size: 15px; color: #333333; float: right;">${coachingList.basicPrice}
													원</b>
											</div>
										</div>

										<!-- coachInfo 이동 -->
										<div class="card-footer border-top-0"
											style="background-color: #FFCCCC;">
											<div class="text-center">
												<a class="btn btn-outline-dark mt-auto" href="#">Get in</a>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</section>

			</div>

			<!-- 하단바 -->
			<jsp:include page="footer.jsp"></jsp:include>

		</div>
	</div>

	<!-- 맨위로 -->
	<a class="scroll-to-top rounded" href="#page-top">
		<i class="fas fa-angle-up"></i>
	</a>

	<!-- 로그아웃 확인 멘트
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="#">Logout</a>
				</div>
			</div>
		</div>
	</div> -->

	<!-- JS 참조 -->
	<script src="resources/vendor/jquery/jquery.min.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<script src="resources/js/sb-admin-2.min.js"></script>
	<script src="resources/vendor/chart.js/Chart.min.js"></script>
	<script src="resources/js/demo/chart-area-demo.js"></script>
	<script src="resources/js/demo/chart-pie-demo.js"></script>

</body>
</html>