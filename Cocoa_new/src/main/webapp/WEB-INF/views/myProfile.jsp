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

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">My Info</h1>
					</div>

					<!-- 본인 프로필 조회 및 수정 -->
					<form name="proFileInfo" method="post"
						action="${contextPath}/modProfile" enctype="multipart/form-data">
						<section class="py-5" style="margin: 0 auto;">
							<div class="row" style="flex-wrap: unset;">


								<div class="col-sm-3 px-3">

									<!-- 좌측 프로필 : proImg / name -->
									<div style="flex-wrap: unset;">
										<div
											style="text-align: center; width: 100%; height: 60%; border: 2px solid; background-color: #FFCCCC;">

											<!-- 프로필 이미지 -->
											<br> <br> <input type="hidden"
												name="originalFileName" value="${profileId.proImg }" /> <img
												id="preview"
												src="..."
												style="border: 1px solid;" width="70%" height="120px"
												onerror="this.src='resources/img/onerror.png'" /> <br>
											<br> <label class="btn btn-outline-dark" for="proImg"
												id="proImgMod">대표 이미지 변경 </label> <input type="file"
												id="proImg" name="proImg" onchange="readURL(this);"
												style="display: none;" /><br>

											<!-- 후기 조회 이동 -->
											<br> <span style="text-align: center;"><a
												href="/cocoa/view_reviewInfo?target=${profileId.id}"> <input
													type="button" name="view_reviewInfo" value="후기보기"
													class="btn btn-third-dark"
													style="font-size: 15px; border-radius: 12px; width: 50%;">
											</a> </span><br> <br>

											<!-- name -->
											<input type="hidden" name="id" value="${profileId.id}" /> <input
												type="text" name="nickName" value="${profileId.name}"
												disabled
												style="text-align: center; border: 0; font-weight: 700; background-color: #FFCCCC; color: black; width: 50%;"><br>
											<br>
										</div>
									</div>
								</div>

								<!-- 우측 내용 : pContents -->
								<div
									style="border: 1px solid; background-color: #FFCC99; color: black; border-radius: 5px; padding: 15px;"
									class="col-sm-9 px-5">
									<div class="proFile">

										<!-- pContents 입력 -->
										<!-- textarea 닫아주는거 붙여써야함 -->
										<br> <b>소개 및 경력 :</b> <br> <br>
										<textarea name="proContents" rows="20" cols="20"
											id="proContents" disabled placeholder="공백 포함 2000자 이내로 입력"
											style="border: 1px solid; background-color: #FFCC99; width: 100%; color: black; resize: none;">${profileId.proContents}</textarea>
										<hr>
									</div>

									<!-- 작성(submit) + 취소(버튼) -->
									<div class="card-body" style="text-align: center">
										<input type="submit" id="mod_profile"
											class="btn btn-outline-dark" value="확 인" /> <a
											href="/cocoa/" id="mod_start" class="btn btn-outline-dark">프로필
											수정</a> &nbsp; <input type="button" id="cancel" value="취 소"
											onclick="history.go(0)" class="btn btn-outline-dark">
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