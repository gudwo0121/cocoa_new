
	function openLink(){
		
		var isLogOn = $('#isLogOn').val();
		var link = $('#linkInfo').val();
		
		if(isLogOn){
			window.open(link);
		} else {
			alert("로그인이 필요합니다.");
			location.href='/cocoa/goLogin'
		}
		
	}