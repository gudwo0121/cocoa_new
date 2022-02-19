$(document).ready(function() {
	
	// url에 따라 3개 분야별로 개발툴 다르게 표시
	var url = $(location).attr('pathname');

	if(url == '/cocoa/coaching/web'){
		$('#tool1').text('Spring Framework');
		$('#tool2').text('Django');
	} else if(url == '/cocoa/coaching/mobile'){
		$('#tool1').text('Andriod Studio');
		$('#tool2').text('Xcode');
	} else if(url == '/cocoa/coaching/embedded'){
		$('#tool1').text('Arduino');
		$('#tool2').text('Raspberry Pi');
	}
	
});