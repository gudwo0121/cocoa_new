$(document).ready(function() {
	
	// url 잘라서 분야만 담기
	var field = $(location).attr('pathname').split("/");
	
	// 3번째 index가 분야 = url이 더 길어져도 분야는 고정적으로 입력
	if(field[3] == "web"){
		$('#tool1').text('Spring Framework');
		$('#tool2').text('Django');
	} else if(field[3] == "mobile"){
		$('#tool1').text('Andriod Studio');
		$('#tool2').text('Xcode');
	} else if(field[3] == "embedded"){
		$('#tool1').text('Arduino');
		$('#tool2').text('Raspberry Pi');
	}

});