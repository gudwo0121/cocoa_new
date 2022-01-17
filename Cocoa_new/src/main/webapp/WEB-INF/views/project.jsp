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
<script type="text/javascript">
	// 비동기 페이지 이동
	function movePage(url) {
		var page = {
			url : url,
			async : true,
			type : "POST",
			dataType : "html",
			cache : false
		};

		$.ajax(page).done(function(data) {
			// Contents 영역 삭제
			$('#main').children().remove();
			// Contents 영역 교체
			$('#main').html(data);
		});
	}
</script>
</head>
<body>

	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h3 mb-0 text-gray-800">Project Page</h1>
			<label
				class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"
				onclick="movePage('/cocoa/projectWrite')">프로젝트 모집</label>
		</div>

		<!-- 난이도 -->
		<div class="row">

			<!-- (난이도) 초급 -->
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-left-primary shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div class="h5 mb-0 font-weight-bold text-gray-800">초급</div>
							</div>
							<div class="col-auto">
								<i class="fas fa-calendar fa-2x text-gray-300"></i>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- (난이도) 중급 -->
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-left-success shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div class="h5 mb-0 font-weight-bold text-gray-800">중급</div>
							</div>
							<div class="col-auto">
								<i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- (난이도) 고급 -->
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-left-success shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div class="h5 mb-0 font-weight-bold text-gray-800">고급</div>
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

	<!-- 프로젝트 카탈로그 구간 -->
	<section style="min-height: 50vh;">
		<div class="container px-4 px-lg-5 mt-5">

			<!-- 생성된 코칭 카탈로그 표시 -->
			<div
				class="row gx-5 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

				<!-- 프로젝트 리스트 전체 조회 결과값 불러오기 -->
				<c:forEach var="projectList" items="${projectList}">

					<!-- 간판 이미지 -->
					<img class="card-img-top" alt="" height=200
						onerror=" this.src='resources/img/onerror.png'" src="..." />

					<!-- 리스트 표시 정보 -->
					<div class="card-body" style="background-color: #FFCCCC;">
						<div class="text-center">

							<!-- 리더 -->
							<b style="font-size: 15px; float: left; color: grey;">${proprojectListject.leader}</b>

							<!-- 후기 개수 (없을시 0개) -->
							<a href="/cocoa/view_reviewInfo?target=${projectList.leader}">
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
							<b style="font-size: 15px; color: black;">${projectList.pTitle}</b><br>
							<br>

							<!-- 인원수 -->
							<b style="font-size: 15px; color: #333333; float: right;">${projectList.memberCount}명
								모집중</b>
						</div>
					</div>

					<!-- projectInfo 이동 -->
					<div class="card-footer pt-2 border-top-0"
						style="background-color: #FFCCCC;">
						<div class="text-center">
							<a class="btn btn-outline-dark mt-auto" href="#">Get in</a>
						</div>
					</div>
			</div>
		</div>
		</c:forEach>
		</div>
	</section>

</body>
</html>