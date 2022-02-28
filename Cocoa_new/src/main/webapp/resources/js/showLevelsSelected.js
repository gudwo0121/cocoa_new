	
	$(document).ready(function() {

		// 현재 url 잘라서 담기
		var url = $(location).attr('pathname').split("/");

		// 현재 분야의 레벨 정보 담기
		var level1 = $('#level1').text();
		var level2 = $('#level2').text();
		var level3 = $('#level3').text();

		// url의 4번째, 즉 해당 레벨을 눌렀을 시 배경색 전환
		if (url[4] == level1) {
			$('#levelsSelected1').css('background-color', '#4e73df');
			$('#postBgColor1').css('background-color', '#4e73df');
			$('#postBgColor2').css('background-color', '#4e73df');
		} else if (url[4] == level2) {
			$('#levelsSelected2').css('background-color', '#1cc88a');
			$('#postBgColor1').css('background-color', '#1cc88a');
			$('#postBgColor2').css('background-color', '#1cc88a');
		} else if (url[4] == level3) {
			$('#levelsSelected3').css('background-color', '#f6c23e');
			$('#postBgColor1').css('background-color', '#f6c23e');
			$('#postBgColor2').css('background-color', '#f6c23e');
		}

	});