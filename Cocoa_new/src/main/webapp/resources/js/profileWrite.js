		
		// 이미지 미리보기
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
/*		function nullCheck() {
			var _cTitle = $("#cTitle").val();
			var _basicPrice = $("#basicPrice").val();
			var _cField = $("#cField").val();
			var _tool = $("#tool").val();
			var _cContents = $("#cContents").val();
	
			if (_cTitle == "" || _basicPrice == "" || _cField == "empty"
					|| _tool == "tool" || _cContents == "") {
				alert("빈칸없이 입력하세요");
				$('#profileWrite').attr('onSubmit', "return false;");
			} else {
				$('#profileWrite').removeAttr('onSubmit');
			}
		}
*/
		