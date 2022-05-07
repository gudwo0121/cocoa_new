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
	src="${contextPath}/resources/js/projectWrite.js"></script>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=jp1by0tzk9"></script>
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
						<h1 class="h3 mb-0 text-gray-800">Project Post</h1>
					</div>

					<!-- 프로젝트 모집 작성 -->
					<form method="post" action="/cocoa/projectWrite"
						enctype="multipart/form-data">

						<!-- 정보 입력란 -->
						<div class="card shadow mb-4"
							style="margin: 0 auto; width: 700px;">

							<!-- 소제목 -->
							<div class="card-header">
								<h6 class="m-0 font-weight-bold text-primary">프로젝트 모집</h6>
							</div>

							<!-- 썸네일 -->
							<div align="center">
								<br> <label for="pImg" style="cursor: pointer;"><img
									id="preview" src="..." style="border: 1px solid;" width="100%"
									height="200vh"
									onerror="this.src='${contextPath}/resources/img/onerror.png'">
								</label> <input type="file" id="pImg" name="pImg"
									onchange="readURL(this);" style="display: none;">
							</div>

							<!-- 제목 + 인원 + 분야 + 난이도 + 내용 + 링크 + 장소 -->
							<div class="cpWrite">
								<hr>

								<!-- 제목 -->
								제목 : <input name="pTitle" type="text" id="pTitle"
									style="border: 1px solid; width: 88%; margin-left: 25px;">
								<hr>

								<!-- 인원 -->
								인원 : <input name="memberCount" type="number" id="basicPrice"
									min="0" max="99" placeholder="0"
									style="border: 1px solid; width: 150px; margin-left: 25px;">&nbsp;명
								<hr>

								<!-- 분야 -->
								분야 : <select
									style="text-align: center; width: 150px; margin-left: 25px; border: 1px solid;"
									id="pField" name="pField">
									<option id="empty" style="display: none;">-- 선택 --</option>
									<option id="pField1" value="pField1">Web</option>
									<option id="pField2" value="pField2">Mobile App</option>
									<option id="pField3" value="pField3">Embedded</option>
								</select>
								<hr>

								<!-- 난이도 -->
								난이도 : <select
									style="text-align: center; width: 150px; margin-left: 10px; border: 1px solid;"
									id="level" name="level">
									<option id="empty" style="display: none;">-- 선택 --</option>
									<option id="level1" value="level1">Basic</option>
									<option id="level2" value="level2">Intermediate</option>
									<option id="level3" value="level3">Advanced</option>
								</select>
								<hr>

								<!-- 내용 -->
								상세 내용 : <br> <br>
								<textarea name="pContents" rows="10" id="pContents"
									maxlength="2000"
									placeholder="Tip. 프로젝트 개요 포함 (필수)&#13;&#10;Tip. 개발환경 및 필수역량 포함 (필수)&#13;&#10;Tip. 프로젝트 진행계획 포함 (필수)"
									style="border: 1px solid; width: 100%; resize: none;"></textarea>
								<hr>

								<!-- 링크 -->
								채팅 링크 : <input name="link" type="text" id="link"
									placeholder="Tip. 소통 가능한 채팅방 링크 입력"
									style="border: 1px solid; width: 300px; margin-left: 10px;">
								<hr>

								<!-- 장소 -->
								모임 장소 : <input type="text" name="map" id="addr" size="35"
									style="border: 1px solid; width: 300px; margin-left: 10px; margin-right: 5px;"
									placeholder="Tip. 지번 or 도로명 주소 입력"> <input
									type="button" name="search" id="search" value="검색"> <br>
								<br>
								<div id="map"></div>
								<hr>

							</div>

							<!-- 작성 + 취소 -->
							<div style="text-align: center; padding-bottom: 15px;">
								<input type="submit" class="btn btn-outline-dark" value="등 록">
								&nbsp; <input type="button" class="btn btn-outline-dark"
									onclick="history.go(-1)" value="취 소">
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