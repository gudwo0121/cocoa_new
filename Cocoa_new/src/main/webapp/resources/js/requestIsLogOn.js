
	function isLogOn(){
		
		var isLogOn = $('#isLogOn').val();
		
		if(!isLogOn) {
			alert("로그인이 필요합니다.");
			location.href='/cocoa/goLogin';
		}
	}