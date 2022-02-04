<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Cocoa</title>
<link href="resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href="resources/css/sb-admin-2.min.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
</head>
<body id="page-top">

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
					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">코칭 등록</h1>
					</div>

					<!-- 코칭 글 작성 -->
					<form name="coachWriteForm" method="post" id="coachWrite"
						action="${contextPath}/coachWrite" enctype="multipart/form-data">
						<section class="py-5">
							<div class="container main-secction">
								<div class="row" style="flex-wrap: unset;">

									<!-- 좌측 프로필 : coach~proImg / coach~name -->
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

												<!-- coach -->
												<input type="text" name="coach" value="${member.id}"
													readonly
													style="text-align: center; border: 0; font-weight: 700; background-color: #FFCCCC; width: 70%;"><br>
												<br>
											</div>
										</div>
									</div>

									<!-- 우측 내용 : cImg / cTitle / basicPrice / cContents -->
									<div class="card px-3"
										style="width: 50rem; border: 1px solid; background-color: #FFCC99">

										<!-- cImg -->
										<div align="center">
											<br> <img id="preview" src="resources/img/onerror.png"
												width=100% height=300 style="border: 1px solid;" /><br>
											<br> <label class="btn btn-outline-dark" for="cImg">
												대표 이미지 변경 </label><input type="file" id="cImg" name="cImg"
												onchange="readURL(this);" style="display: none;" />
										</div>

										<!-- cTitle / basicPrice / cContents 입력 -->
										<div class="coach">

											<!-- cTitle 입력 -->
											<hr>
											<input name="cTitle" type="text" placeholder="제목을 입력하세요."
												id="cTitle"
												style="border: 1; text-align: center; width: 100%;">
											<hr>

											<!-- basicPrice 입력 -->
											요금 : <input name="basicPrice" type="number" id="basicPrice"
												placeholder="요금을 입력하세요." style="border: 1; width: 30%;">&nbsp;<b>원</b>
											<hr>

											<!-- cField 선택 -->
											영역 : <select style="text-align: center; width: 30%;"
												id="cField" name="cField" onchange="categoryChange(this)">
												<option id="empty" value="empty">-- 선택 --</option>
												<option id="cField1" value="cField1">Web</option>
												<option id="cField2" value="cField2">Mobile App</option>
												<option id="cField3" value="cField3">Embedded</option>
											</select>
											<hr>

											<!-- tool 선택 -->
											개발툴 : <select style="text-align: center; width: 30%;"
												name="tool" id="tool">
												<option id="empty" value="empty">-- 선택 --</option>

												<option id="tool1" value="tool1" hidden>Spring</option>
												<option id="tool2" value="tool2" hidden>Django</option>

												<option id="tool3" value="tool3" hidden>Android
													Studio</option>
												<option id="tool4" value="tool4" hidden>Xcode</option>

												<option id="tool5" value="tool5" hidden>Arduino</option>
												<option id="tool6" value="tool6" hidden>Raspberry
													Pi</option>
											</select>
											<hr>

											<!-- cContents 입력 -->
											<!-- textarea 닫아주는거 붙여써야함 -->
											세부 내용 : <br>
											<textarea name="cContents" rows="10" cols="20" id="cContents"
												placeholder="요금 측정 기준 및 본인 PR을 해주세요. (공백 포함 2000자 이내)"
												style="border: 1; width: 100%; resize: none;"></textarea>
											<hr>
										</div>

										<!-- 작성(submit) + 취소(버튼) -->
										<div class="card-body" style="text-align: center">
											<input type="submit" class="btn btn-outline-dark"
												onClick="" value="등록" /> &nbsp; <a
												href="/cocoa/view_coachCate" class="btn btn-outline-dark">취소</a>
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
	<a class="scroll-to-top rounded" href="#page-top">
		<i class="fas fa-angle-up"></i>
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