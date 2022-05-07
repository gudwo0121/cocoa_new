<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cocoa</title>
</head>
<body>
	<!-- 비정상 접근 차단 -->
	<%
	String strReferer = request.getHeader("referer");

	if (strReferer == null) {
	%>
	<script type="text/javascript">
		alert("정상적인 접근이 아닙니다. 메인 화면으로 돌아갑니다.");
		location.href = "/cocoa/";
	</script>
	<%
	return;
	}
	%>
</body>
</html>