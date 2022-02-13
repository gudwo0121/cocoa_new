	
	// 미리보기
	function readURL(input) {

		if (input.files && input.files[0]) {

			var reader = new FileReader();
			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}

	// 유효성 검증
	function nullCheck() {
		var _kakao = $("#kakao").val();
		var _pTitle = $("#pTitle").val();
		var _memberCount = $("#memberCount").val();
		var _pField = $("#pField").val();
		var _level = $("#level").val();
		var _pContents = $("#pContents").val();
		
		if (_kakao == "") {
			alert("카카오 오픈채팅 링크를 입력하세요");
			$('#projectWrite').attr('onSubmit', "return false;");
		} else if (_pTitle == "") {
			alert("제목을 입력하세요");
			$('#projectWrite').attr('onSubmit', "return false;");
		} else if (_memberCount == "") {
			alert("인원을 입력하세요");
			$('#projectWrite').attr('onSubmit', "return false;");
		} else if (_pField == "-- 선택 --") {
			alert("영역을 선택해주세요");
			$('#projectWrite').attr('onSubmit', "return false;");
		} else if (level == "-- 선택 --") {
			alert("수준을 선택해주세요");
			$('#projectWrite').attr('onSubmit', "return false;");
		} else if (_pContents == "") {
			alert("내용을 입력하세요");
			$('#projectWrite').attr('onSubmit', "return false;");
		} else {
			$('#projectWrite').removeAttr('onSubmit');
		}
	}
