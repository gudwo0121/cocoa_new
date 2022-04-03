
	function openLink(){
		
		var isLogOn = $('#isLogOn').val();
		var link = $('#linkInfo').val();
		
		if(isLogOn){
			window.open(link);
		} else {
			location.href='/cocoa/goLogin'
		}
		
	}