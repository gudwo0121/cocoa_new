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
<link href="${contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link href="${contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
<script type="text/javascript" src="${contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/projectWrite.js"></script>
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

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">프로젝트 모집</h1>
					</div>

					<!-- 프로젝트 모집 작성 -->
					<form name="projectWrite" action="/cocoa/projectWrite"
						method="post" id="projectWrite" enctype="multipart/form-data">
						<section class="py-5">
							<div class="container main-secction">
								<div class="row" style="flex-wrap: unset;">

									<!-- 좌측 프로필 : leader~proImg / leader~name -->
									<div
										class="col-md-3 col-sm-3 col-xs-12 user-profil-part pull-left">
										<div class="row">
											<div
												class="col-md-12 col-md-12-sm-12 col-xs-12 user-image text-center"
												style="width: 80%; height: 100%; border: 2px solid; background-color: #FFCCCC;">

												<!-- 프로필 조회 이동 -->
												<br> <br> <img name="proImg" src="..."
													style="border: 1px solid;" width="50%" height="120px"
													onerror="this.src='resources/img/onerror.png'"><br>
												<br>

												<!-- leader -->
												<input type="text" name="leader" value="${member.id}"
													readonly
													style="text-align: center; border: 0; font-weight: 700; background-color: #FFCCCC; width: 70%;"><br>
												<br>

												<!-- kakao -->
												<b>카카오톡 오픈채팅 :</b><br> <br> <input type="text"
													name="kakao" placeholder="링크 입력" id="kakao"
													style="text-align: center; border: 1; background-color: #FFCCCC; width: 80%;"><br>
												<br>
											</div>
										</div>
									</div>

									<!-- 우측 내용 : pImg / pTitle / memberCount / pContents -->
									<div class="card px-3"
										style="width: 50rem; border: 1px solid; background-color: #FFCC99">

										<!-- pImg -->
										<div align="center">
											<br> <img id="preview" src="${contextPath}/resources/img/onerror.png"
												width=100% height=300 style="border: 1px solid;" /><br>
											<br> <label class="btn btn-outline-dark" for="pImg">
												대표 이미지 변경 </label><input type="file" id="pImg" name="pImg"
												onchange="readURL(this);" style="display: none;" />
										</div>

										<!-- pTitle / memberCount / pContents 입력 -->
										<div class="project">

											<!-- pTitle 입력 -->
											<hr>
											<input name="pTitle" type="text" placeholder="제목을 입력하세요."
												id="pTitle"
												style="border: 1; text-align: center; width: 100%;">
											<hr>

											<!-- memberCount 입력 -->
											인원 : <input name="memberCount" type="number" id="memberCount"
												placeholder="인원수를 입력하세요." style="border: 1; width: 30%;">&nbsp;<b>명</b>
											<hr>

											<!-- pField 선택 -->
											분야 : <select style="text-align: center; width: 30%;"
												id="pField" name="pField">
												<option id="empty">-- 선택 --</option>
												<option id="pField1" value="pField1">Web</option>
												<option id="pField2" value="pField2">Mobile App</option>
												<option id="pField3" value="pField3">Embedded</option>
											</select>
											<hr>

											<!-- level 선택 -->
											난이도 : <select style="text-align: center; width: 30%;"
												id="level" name="level">
												<option id="empty">-- 선택 --</option>
												<option id="level1" value="level1">Basic</option>
												<option id="level2" value="level2">Intermediate</option>
												<option id="level3" value="level3">Advanced</option>
											</select>
											<hr>

											<!-- pContents 입력 -->
											<!-- textarea 닫아주는거 붙여써야함 -->
											세부 내용 : <br> <br>
											<textarea name="pContents" rows="10" cols="20" id="pContents"
												placeholder="프로젝트 개요 및 포지션 별 자격요건을 써주세요. (공백포함 2000자 이내)"
												style="border: 1; width: 100%; resize: none;"></textarea>
											<hr>

											<!-- &nbsp;모임 장소 : <input type="text" name="map" id="addr"
												size="35" placeholder='장소를 입력해주세요.'> <input
												type="button" name="send" id="sendMark" value="검색"><br>
											<br>
											<div id="map" style="width: 100%; height: 400px;"></div>
											<hr> -->

										</div>

										<!-- 작성(submit) + 취소(버튼) -->
										<div class="card-body" style="text-align: center">
											<input type="submit" class="btn btn-outline-dark" value="등록"
												onclick="nullCheck()"> &nbsp; <a
												href="/cocoa/goProjectList" class="btn btn-outline-dark">취소</a>
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
	<script src="${contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	<script src="${contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<script src="${contextPath}/resources/js/sb-admin-2.min.js"></script>
	<script src="${contextPath}/resources/vendor/chart.js/Chart.min.js"></script>
	<script src="${contextPath}/resources/js/demo/chart-area-demo.js"></script>
	<script src="${contextPath}/resources/js/demo/chart-pie-demo.js"></script>

</body>
</html>