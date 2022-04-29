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
						<h1 class="h3 mb-0 text-gray-800">Send Request</h1>
					</div>

					<!-- 코칭 요청 -->					
					<form method="post" action="/cocoa/requestWrite"
						enctype="multipart/form-data">

						<!-- 정보 입력란 -->
						<div class="card shadow mb-4"
							style="margin: 0 auto; width: 700px;">

							<!-- 소제목 -->
							<div class="card-header">
								<h6 class="m-0 font-weight-bold text-primary">코칭 요청</h6>
							</div>

							<!-- 제목 + 내용 + 첨부파일 -->
							<div class="cpWrite">

								<!-- 제목 -->
								제목 : <input name="rTitle" type="text" id="rTitle"
									style="border: 1px solid; width: 88%; margin-left: 25px; margin-top: 20px;">
								<hr>

								<!-- 요청 내용 -->
								요청 내용 :
								<textarea name="rContents" rows="10" id="rContents"
									maxlength="2000"
									placeholder="Tip. 현재 개발환경 포함 (필수)&#13;&#10;Tip. 구체적인 에러 및 문제점 상황설명 (필수)&#13;&#10;Tip. 첨부파일 추가설명 (선택)"
									style="border: 1px solid; width: 100%; resize: none; margin-top: 10px;"></textarea>
								<hr>

								<!-- 첨부파일 -->
								<input type="file" id="rImg" name="rImg"
									onchange="readURL(this);">
								<div align="center"
									style="margin-top: 20px; margin-bottom: 20px;">
									<label style="width: 100%;"><img id="preview" src="..."
										style="border: 1px solid; filter: brightness(100%);"
										width="100%" height="300vh"
										onerror="this.src='${contextPath}/resources/img/onerror.png'">
									</label>
								</div>
								<hr>
								
								<!-- 코칭 요청 = res 삽입에 필요 -->
								<input type="hidden" name="res" value="${res}">
								<!-- 되돌아오는 경로에 필요 -->
								<input type="hidden" name="coachNO" value="${coachNO}">

								<!-- 요청 + 취소 -->
								<div style="text-align: center; padding-bottom: 15px;">
									<input type="submit" class="btn btn-outline-dark" value="요 청">
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