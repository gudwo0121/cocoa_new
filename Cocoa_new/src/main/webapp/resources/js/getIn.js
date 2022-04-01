
	// 코칭, 프로젝트 Get in 상세 조회 페이지 이동
	function getIn() {
	
		// 주제
		var main = $(location).attr('pathname').split("/");
		// 코칭 or 프로젝트 글 넘버
		// id 값을 설정할 땐 HTML 코드내부 모든 문자와 중복 절대 x
		var post = $('#post').text();
		
		// 페이지 이동
		location.href = "/cocoa/" + main[2] + "/post/" + post;
		
	}
