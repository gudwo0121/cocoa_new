	
	// 코칭, 프로젝트 2계층 조건 조회 실행
	function conditionRead(secondSelected) {
	
		// 주제
		var main = $(location).attr('pathname').split("/");
		// 분야
		var field = $('#field').text();
		// 개발툴 or 레벨
		var second = $(secondSelected).text().replace(" ", "");
						
		// 페이지 이동
		location.href = "/cocoa/"+ main[2] + "/" + field + "/" + second;
		
	}
