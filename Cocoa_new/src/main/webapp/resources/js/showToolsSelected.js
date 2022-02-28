	
	$(document).ready(function() {

		// 현재 url 잘라서 담기
		var url = $(location).attr('pathname').split("/");

		// 현재 분야의 개발툴 정보 담기
		var tool1 = $('#tool1').text().replace(" ", "");
		var tool2 = $('#tool2').text().replace(" ", "");

		// url의 4번째, 즉 해당 개발툴을 눌렀을 시 배경색 전환 + post의 배경색도 함께 전환
		if (url[4] == tool1) {
			$('#toolSelected1').css('background-color', '#4e73df');
			$('#postBgColor1').css('background-color', '#4e73df');
			$('#postBgColor2').css('background-color', '#4e73df');
		} else if (url[4] == tool2) {
			$('#toolSelected2').css('background-color', '#1cc88a');
			$('#postBgColor1').css('background-color', '#1cc88a');
			$('#postBgColor2').css('background-color', '#1cc88a');
		}

	});