<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
</head>
<body id="page-top" style="min-width: 1000px; max-width: 1920px;">

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
						<h1 class="h3 mb-0 text-dark">Got Request</h1>
					</div>

					<!-- 보낸 요청 리스트 -->
					<div
						style="text-align: center; padding: 50px; padding-left: 130px;">
						<div class="table-responsive px-3" style="border: 1px solid grey;">
							<table class="table table-striped table-sm">
								<thead>
									<tr>
										<th scope="col">요청자</th>
										<th scope="col">요청 제목</th>
										<th scope="col">요청 날짜</th>
										<th scope="col">요청 상태</th>
									</tr>
								</thead>
								<tbody>
									<!-- 리스트 한 줄 -->
									<c:forEach var="requestList" items="${requestList}">
										<tr>
											<td>${requestList.req}</td>
											<!-- gotReqWait로 이동 -->
											<td style="text-align: left;"><a
												style="text-decoration-line: none; color: dark; font-weight: 700;"
												href="#">${requestList.rTitle}</a></td>
											<fmt:parseDate var="dateFmt" pattern="yyyy-MM-dd"
												value="${requestList.rDate}" />
											<fmt:formatDate var="dateTempParse" pattern="yyyy-MM-dd"
												value="${dateFmt}" />
											<td>${dateTempParse}</td>
											<td><c:choose>
													<c:when test="${requestList.status == 'status1'}">대기</c:when>
													<c:when test="${requestList.status == 'status2'}">수락</c:when>
													<c:when test="${requestList.status == 'status3'}">거절</c:when>
													<c:when test="${requestList.status == 'status4'}">완료</c:when>
												</c:choose></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>

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