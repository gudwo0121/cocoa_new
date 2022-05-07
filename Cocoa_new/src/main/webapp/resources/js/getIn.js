
	// 코칭, 프로젝트 Get in 상세 조회 페이지 이동
	function getIn(post) {
	
		// 주제
		var main = $(location).attr('pathname').split("/");
		
		// 페이지 이동
		location.href = "/cocoa/" + main[2] + "/post/" + post;
		
	}