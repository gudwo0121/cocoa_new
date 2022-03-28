
	// Get in 상세 조회에서 분야, 개발툴 글씨로 변환
	// ex) cField1 = Web
	$(document).ready(function() {

		// 비교를 위한 변환전 값 담기
		var cField = $('#cField').text();
		var tool = $('#tool').text();

		// 분야 변환
		if (cField == "cField1") {
			$('#cField').text("Web");
		} else if (cField == ("cField2")) {
			$('#cField').text("Mobile");
		} else if (cField == "cField3") {
			$('#cField').text("Embedded");
		}

		// 개발툴 변환
		if (tool == "tool1") {
			$('#tool').text("Spring Framework");
		} else if (tool == "tool2") {
			$('#tool').text("Django");
		} else if (tool == "tool3") {
			$('#tool').text("Android Studio");
		} else if (tool == "tool4") {
			$('#tool').text("Xcode");
		} else if (tool == "tool5") {
			$('#tool').text("Arduino");
		} else if (tool == "tool6") {
			$('#tool').text("Raspberry Pi");
		}

	});
