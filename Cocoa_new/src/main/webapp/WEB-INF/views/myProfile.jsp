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
<link href="resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link href="resources/css/sb-admin-2.min.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="resources/js/profileWrite.js"></script>
</head>
<body id="page-top">

	<div id="wrapper">

		<!-- 사이드바 -->
		<jsp:include page="profileSideBar.jsp"></jsp:include>

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">

				<!-- 상단바 -->
				<jsp:include page="header.jsp"></jsp:include>

				<!-- 메인 콘텐츠 -->
				<div class="container-fluid">

					<!-- 헤드라인 -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Profile</h1>
					</div>

					<!-- 본인 프로필 조회 및 수정 -->
					<form name="profile" method="post"
						action="${contextPath}/profileWrite" enctype="multipart/form-data">
						<section class="py-5" style="margin: 0 auto;">
							<div class="row" style="flex-wrap: unset;">

								<div class="card shadow mb-4">

									<!-- 좌측 프로필 : proImg / name -->
									<div style="flex-wrap: unset;">

										<!-- 프로필 이미지 -->
										<input type="hidden"
											name="originalFileName" value="${profileId.proImg}">
										<label class="btn btn-outline-dark" for="proImg"
											id="proImgMod">
											<img id="preview" src="..." onerror="this.src='resources/img/onerror.png'">
										</label>
										<input type="file" id="proImg" name="proImg" onchange="readURL(this);" style="display: none;">

										<!-- name -->
										이름춘

									</div>
								</div>

								<!-- 우측 내용 : pContents -->
								<div class="col-sm-9 px-5">
									<div class="card shadow mb-4">

										<div class="card-header py-3">
											<h6 class="m-0 font-weight-bold text-primary">소개 및 경력</h6>
										</div>

										<div class="card-body">
											<textarea name="proContents" id="proContents"
												placeholder="자신을 어필해주세요!"
												style="border: none; resize: none;">${profileId.proContents}</textarea>
										</div>

										<!-- 작성(submit) + 취소(버튼) -->
										<div class="card-body" style="text-align: center">
											<input type="submit" id="mod_profile"
												class="btn btn-outline-dark" value="확 인" /> &nbsp; <a
												href="/cocoa/" id="mod_start" class="btn btn-outline-dark">프로필
												수정</a> &nbsp; <input type="button" id="cancel" value="취 소"
												onclick="history.go(0)" class="btn btn-outline-dark">
										</div>
									</div>

								</div>
							</div>
						</section>
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
	<script src="resources/vendor/jquery/jquery.min.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<script src="resources/js/sb-admin-2.min.js"></script>
	<script src="resources/vendor/chart.js/Chart.min.js"></script>
	<script src="resources/js/demo/chart-area-demo.js"></script>
	<script src="resources/js/demo/chart-pie-demo.js"></script>

</body>
</html>