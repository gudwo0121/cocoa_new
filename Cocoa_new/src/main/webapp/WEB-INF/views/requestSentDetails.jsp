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
<script type="text/javascript"
	src="${contextPath}/resources/js/removeReq.js"></script>
<script type="text/javascript"
	src="${contextPath}/resources/js/requestOpenLink.js"></script>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script>
	// js 파일 참조 방식으로 불러오기 실패
	// 코드 직접 참조
	// 창까지는 뜨지만 QR코드로 결제 후, 결제를 했다는 정보를 보낼 수가 없음
	// rsp도 실패로 간주됌
	function requestPay() {
		IMP.init("imp41685936");
		IMP.request_pay({
			pg : "kakaopay", // pg사
			pay_method : "html5_inicis", // 결제수단
			merchant_uid : "cocoa-001", // 결제 고유번호
			name : "${requestInfo.res}'s coaching", // 코치명
			amount : "${requestInfo.realPrice}", // 최종요금
		}, function(rsp) {
			if (rsp.success) {
				alert(rsp + "결제성공");
				/* jQuery.ajax({
					url : "/cocoa/", // 가맹점 서버
					method : "POST",
					headers : {
						"Content-Type" : "application/json"
					},
					data : {
						imp_uid : rsp.imp_uid,
						merchant_uid : rsp.merchant_uid
					//기타 필요한 데이터가 있으면 추가 전달
					}
				}).done(function(data) {
					// 가맹점 서버 결제 API 성공시 로직
					alert("성공");
				}) */
			} else {
				alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
			}
		});
	}
</script>
</head>
<body id="page-top" style="min-width: 1000px; max-width: 1920px;">

	<!-- 비정상 접근 차단 -->
	<jsp:include page="notTheWay.jsp"></jsp:include>

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
						<h1 class="h3 mb-0 text-gray-800">Request Details</h1>
					</div>

					<!-- 대기, 수락, 거절, 완료 -->
					<!-- 각 상태별 보여지는 화면 구분 -->
					<c:choose>
						<c:when test="${requestInfo.status == 'status1'}">
							<!-- 1. 대기 상태 (수정 / 철회 포함) -->
							<form method="post" action="/cocoa/modRequest"
								enctype="multipart/form-data">

								<!-- 정보 입력란 -->
								<div class="card shadow mb-4"
									style="margin: 0 auto; width: 700px;">

									<!-- 소제목 + 철회 -->
									<div class="card-header">
										<h6 class="m-0 font-weight-bold text-primary">${requestInfo.res}에게
											보낸 요청<input type="button" id="delRequest"
												style="float: right;" value="철 회">
										</h6>
									</div>

									<!-- 제목 + 내용 + 첨부파일 -->
									<div class="cpWrite">

										<!-- reqNO & req -->
										<input type="hidden" id="reqNO" name="reqNO"
											value="${requestInfo.reqNO}"> <input type="hidden"
											id="req" name="req" value="${requestInfo.req}">

										<!-- 제목 -->
										제목 : <input name="rTitle" type="text" id="rTitle"
											value="${requestInfo.rTitle}"
											style="border: 1px solid; width: 88%; margin-left: 25px; margin-top: 20px;">
										<hr>

										<!-- 요청 내용 -->
										요청 내용 :
										<textarea name="rContents" rows="10" id="rContents"
											maxlength="2000"
											placeholder="Tip. 현재 개발환경 포함 (필수)&#13;&#10;Tip. 구체적인 에러 및 문제점 상황설명 (필수)&#13;&#10;Tip. 첨부파일 추가설명 (선택)"
											style="border: 1px solid; width: 100%; resize: none; margin-top: 10px;">${requestInfo.rContents}</textarea>
										<hr>

										<!-- 첨부한 이미지 -->
										<input type="hidden" name="defaultImg"
											value="${requestInfo.rImg}" /> <label for="rImg"
											style="cursor: pointer;"><img id="preview"
											src="${contextPath}/rImgLoad?req=${requestInfo.req}&reqNO=${requestInfo.reqNO}&rImg=${requestInfo.rImg}"
											style="border: 1px solid;" width="400vw" height="300vh"
											onerror="this.src='${contextPath}/resources/img/onerror.png'">
										</label> <input type="file" id="rImg" name="rImg"
											onchange="readURL(this);" style="display: none;">
										<hr>

										<!-- 수정 + 취소 -->
										<div style="text-align: center; padding-bottom: 15px;">
											<input type="submit" class="btn btn-outline-dark" value="수 정">
											&nbsp; <input type="button" class="btn btn-outline-dark"
												onclick="location.href='/cocoa/request/sent'" value="취 소">
										</div>
									</div>
								</div>
							</form>
						</c:when>

						<c:when test="${requestInfo.status == 'status2'}">
							<!-- 2. 수락 상태 (수정 불가 / 철회 가능) -->

							<!-- 정보 입력란 -->
							<div class="card shadow mb-4"
								style="margin: 0 auto; width: 700px;">

								<!-- 소제목 + 철회 -->
								<div class="card-header">
									<h6 class="m-0 font-weight-bold text-primary">${requestInfo.res}에게
										보낸 요청<input type="button" id="delRequest"
											style="float: right;" value="철 회">
									</h6>
								</div>

								<!-- 제목 + 내용 + 첨부파일 -->
								<div class="cpWrite">

									<!-- reqNO & req -->
									<input type="hidden" id="reqNO" name="reqNO"
										value="${requestInfo.reqNO}"> <input type="hidden"
										id="req" name="req" value="${requestInfo.req}"> <br>

									<!-- 제목 -->
									제목 : <span style="width: 88%; margin-left: 25px;">${requestInfo.rTitle}</span>
									<hr>

									<!-- 요청 내용 -->
									요청 내용 :
									<textarea name="rContents" rows="10" id="rContents" disabled>${requestInfo.rContents}</textarea>
									<hr>

									<!-- 첨부한 이미지 -->
									<img id="preview"
										src="${contextPath}/rImgLoad?req=${requestInfo.req}&reqNO=${requestInfo.reqNO}&rImg=${requestInfo.rImg}"
										style="border: 1px solid; cursor: pointer;" width="100%"
										height="300vh"
										onclick="location.href='/cocoa/rImgLoad?req=${requestInfo.req}&reqNO=${requestInfo.reqNO}&rImg=${requestInfo.rImg}'"
										onerror="this.src='${contextPath}/resources/img/onerror.png'">
									<hr>

									<!-- 연결수단 -->
									연결수단 : <span
										style="width: 88%; margin-left: 25px; cursor: pointer;"
										id="contactLink" onclick="openLink()">${requestInfo.contact}</span>
									<hr>

									<!-- 최종요금 -->
									최종요금 : <span style="width: 88%; margin-left: 25px;">${requestInfo.realPrice}</span>&nbsp;원
									<hr>

									<!-- 공지사항 -->
									공지사항 : <span style="width: 88%; margin-left: 25px;">${requestInfo.notice}</span>
									<hr>

									<!-- 결제 + 목록으로 -->
									<div style="text-align: center; padding-bottom: 15px;">
										<input type="button" class="btn btn-outline-dark"
											onclick="requestPay()" value="결 제"> &nbsp; <input
											type="button" class="btn btn-outline-dark"
											onclick="location.href='/cocoa/request/sent'" value="목록으로">
									</div>
								</div>
							</div>
						</c:when>

						<c:when test="${requestInfo.status == 'status3'}">
							<!-- 3. 거절 상태 -->

							<!-- 정보 입력란 -->
							<div class="card shadow mb-4"
								style="margin: 0 auto; width: 700px;">

								<!-- 소제목 + 철회 -->
								<div class="card-header">
									<h6 class="m-0 font-weight-bold text-primary">${requestInfo.res}에게
										보낸 요청<input type="button" id="delRequest"
											style="float: right;" value="철 회">
									</h6>
								</div>

								<!-- 제목 + 내용 + 첨부파일 -->
								<div class="cpWrite">

									<!-- reqNO & req -->
									<input type="hidden" id="reqNO" name="reqNO"
										value="${requestInfo.reqNO}"> <input type="hidden"
										id="req" name="req" value="${requestInfo.req}"> <br>

									<!-- 제목 -->
									제목 : <span style="width: 88%; margin-left: 25px;">${requestInfo.rTitle}</span>
									<hr>

									<!-- 요청 내용 -->
									요청 내용 :
									<textarea name="rContents" rows="10" id="rContents" disabled>${requestInfo.rContents}</textarea>
									<hr>

									<!-- 첨부한 이미지 -->
									<img id="preview"
										src="${contextPath}/rImgLoad?req=${requestInfo.req}&reqNO=${requestInfo.reqNO}&rImg=${requestInfo.rImg}"
										style="border: 1px solid; cursor: pointer;" width="100%"
										height="300vh"
										onclick="location.href='/cocoa/rImgLoad?req=${requestInfo.req}&reqNO=${requestInfo.reqNO}&rImg=${requestInfo.rImg}'"
										onerror="this.src='${contextPath}/resources/img/onerror.png'">
									<hr>

									<!-- 거절사유 -->
									거절사유 : <span style="width: 88%; margin-left: 25px;">${requestInfo.notice}</span>
									<hr>

									<!-- 목록으로 -->
									<div style="text-align: center; padding-bottom: 15px;">
										<input type="button" class="btn btn-outline-dark"
											onclick="location.href='/cocoa/request/sent'" value="목록으로">
									</div>
								</div>
							</div>
						</c:when>

						<%-- <!-- 4. 완료 상태 -->
						<c:when test="${requestInfo.status == 'status4'}">
						</c:when> --%>

					</c:choose>
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