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
<title>Cocoa</title>
<link
	href="${contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link href="${contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/resources/js/coachingWrite.js"></script>
</head>
<body id="page-top" style="min-width: 1000px; max-width: 1920px;">

	<!-- 비정상 접근 차단 -->
	<jsp:include page="notTheWay.jsp"></jsp:include>

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
						<h1 class="h3 mb-0 text-gray-800">Coaching Post</h1>
					</div>

					<!-- 코칭 등록 -->
					<form method="post" action="/cocoa/coachingWrite"
						enctype="multipart/form-data">

						<!-- 정보 입력란 -->
						<div class="card shadow mb-4"
							style="margin: 0 auto; width: 700px;">

							<!-- 소제목 -->
							<div class="card-header">
								<h6 class="m-0 font-weight-bold text-primary">코칭 등록</h6>
							</div>

							<!-- 썸네일 -->
							<div align="center">
								<br> <label for="cImg" style="cursor: pointer;"><img
									id="preview" src="..." style="border: 1px solid;" width="100%"
									height="200vh"
									onerror="this.src='${contextPath}/resources/img/onerror.png'">
								</label> <input type="file" id="cImg" name="cImg"
									onchange="readURL(this);" style="display: none;">
							</div>

							<!-- 제목 + 요금 + 분야 + 개발툴 + 내용 -->
							<div class="cpWrite">
								<hr>

								<!-- 제목 -->
								제목 : <input name="cTitle" type="text" id="cTitle"
									style="border: 1px solid; width: 88%; margin-left: 25px;">
								<hr>

								<!-- 요금 -->
								요금 : <input name="basicPrice" type="number" id="basicPrice"
									min="0" max="100000000" placeholder="0"
									style="border: 1px solid; width: 150px; margin-left: 25px;">&nbsp;원
								<hr>

								<!-- 분야 -->
								분야 : <select
									style="text-align: center; width: 150px; margin-left: 25px; border: 1px solid;"
									id="cField" name="cField" onchange="categoryChange(this)">
									<option id="empty" value="empty" style="display: none;">--
										선택 --</option>
									<option id="cField1" value="cField1">Web</option>
									<option id="cField2" value="cField2">Mobile App</option>
									<option id="cField3" value="cField3">Embedded</option>
								</select>
								<hr>

								<!-- 개발툴 -->
								개발툴 : <select
									style="text-align: center; width: 150px; margin-left: 10px; border: 1px solid;"
									name="tool" id="tool">
									<option id="empty" value="empty" style="display: none;">--
										선택 --</option>

									<option id="tool1" value="tool1" hidden>Spring</option>
									<option id="tool2" value="tool2" hidden>Django</option>

									<option id="tool3" value="tool3" hidden>Android Studio</option>
									<option id="tool4" value="tool4" hidden>Xcode</option>

									<option id="tool5" value="tool5" hidden>Arduino</option>
									<option id="tool6" value="tool6" hidden>Raspberry Pi</option>
								</select>
								<hr>

								<!-- 상세 내용 -->
								상세 내용 : <br> <br>
								<textarea name="cContents" rows="10" id="cContents"
									maxlength="2000"
									placeholder="Tip. 코칭 가능시간대 포함 (필수)&#13;&#10;Tip. 요금환불 관련사항 포함 (필수)&#13;&#10;Tip. 경력 및 프로젝트 기입 (선택)"
									style="border: 1px solid; width: 100%; resize: none;"></textarea>
								<hr>

								<!-- 등록 + 취소 -->
								<div style="text-align: center; padding-bottom: 15px;">
									<input type="submit" class="btn btn-outline-dark" value="등 록">
									&nbsp; <input type="button" class="btn btn-outline-dark"
										onclick="history.go(-1)" value="취 소">
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>

			<!-- 하단바 -->
			<jsp:include page="footer.jsp"></jsp:include>

		</div>
	</div>

	<!-- 맨위로 -->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
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
	<script src="${contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="${contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="${contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<script src="${contextPath}/resources/js/sb-admin-2.min.js"></script>
	<script src="${contextPath}/resources/vendor/chart.js/Chart.min.js"></script>
	<script src="${contextPath}/resources/js/demo/chart-area-demo.js"></script>
	<script src="${contextPath}/resources/js/demo/chart-pie-demo.js"></script>
</body>
</html>