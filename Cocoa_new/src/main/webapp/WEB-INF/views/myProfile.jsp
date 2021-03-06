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
	src="${contextPath}/resources/js/profileWrite.js"></script>
</head>
<body id="page-top" style="min-width: 1000px; max-width: 1920px;">

	<!-- 비정상 접근 차단 -->
	<jsp:include page="notTheWay.jsp"></jsp:include>

	<div id="wrapper">

		<!-- 사이드바 -->
		<jsp:include page="profileSideBar.jsp"></jsp:include>

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content" style="min-width: 1000px; max-width: 1920px;">

				<!-- 상단바 -->
				<jsp:include page="header.jsp"></jsp:include>

				<!-- 메인 콘텐츠 -->
				<div class="container-fluid">

					<!-- 헤드라인 -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-dark">Profile</h1>
					</div>

					<!-- 본인 프로필 조회 및 수정 -->
					<form method="post" action="/cocoa/modMyProfile"
						enctype="multipart/form-data">
						<div class="row" style="margin: 0 auto;">

							<!-- 프로필 이미지 + 이름 -->
							<div class="col-sm-4 px-4">
								<div class="card shadow mb-4"
									style="float: right; width: 200px;">
									<div class="col" style="text-align: center; padding: 20px;">

										<!-- 프로필 이미지 -->
										<input type="hidden" name="defaultImg"
											value="${profile.proImg}" /> <label for="proImg"
											style="cursor: pointer;"><img id="preview"
											src="${contextPath}/proImgLoad?id=${profile.id}&proImg=${profile.proImg}"
											style="border: 1px solid;" width="100%" height="160vh"
											onerror="this.src='${contextPath}/resources/img/onerror.png'">
										</label> <input type="file" id="proImg" name="proImg"
											onchange="readURL(this);" style="display: none;">

										<!-- 이름 -->
										<div class="text-dark" style="padding-top: 20px;">
											<h6 class="m-0 font-weight-bold text-primary">${profile.name}</h6>
											<!-- 이미지 다운로드시 파일명 변수로 쓰임 -->
											<input type="hidden" name="id" value="${profile.id}">
										</div>

									</div>
								</div>
							</div>

							<!-- 프로필 내용 -->
							<div class="col-sm-8 px-4">
								<div class="card shadow mb-4" style="float: left; width: 700px;">

									<!-- 소제목 -->
									<div class="card-header">
										<h6 class="m-0 font-weight-bold text-primary">소개 및 경력</h6>
									</div>

									<div class="card-body" style="height: 70vh;">
										<textarea name="proContents" id="proContents"
											placeholder="Tip. 전직장 혹은 현직장&#13;&#10;Tip. 진행했던 프로젝트&#13;&#10;Tip. 코칭 분야&#13;&#10;Tip. 그 외 어필할 수 있는 경력사항">${profile.proContents}</textarea>
									</div>

									<!-- 작성 + 취소 -->
									<div class="card-body" style="text-align: center;">
										<input type="submit" class="btn btn-outline-dark" value="작 성">&nbsp;&nbsp;<input
											type="button" class="btn btn-outline-dark" value="취 소"
											onclick="history.go(0)">
									</div>
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