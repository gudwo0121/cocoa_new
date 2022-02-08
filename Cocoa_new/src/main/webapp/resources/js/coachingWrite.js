		
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
	
		// 영역 변경시 개발툴 초기화 셋팅
		function categoryChange(e) {
			var tool_cField1_value = [ "tool", "tool1", "tool2" ];
			var tool_cField2_value = [ "tool", "tool3", "tool4" ];
			var tool_cField3_value = [ "tool", "tool5", "tool6" ];
	
			var tool_cField1_out = [ "-- 선택 --", "Spring", "Django" ];
			var tool_cField2_out = [ "-- 선택 --", "Android Studio", "Xcode" ];
			var tool_cField3_out = [ "-- 선택 --", "Arduino", "Rasberry Pi" ];
			var target = document.getElementById("tool");
	
			if (e.value == "cField1") {
				var v = tool_cField1_value;
				var o = tool_cField1_out;
			} else if (e.value == "cField2") {
				var v = tool_cField2_value;
				var o = tool_cField2_out;
			} else if (e.value == "cField3") {
				var v = tool_cField3_value;
				var o = tool_cField3_out;
			}
	
			target.options.length = 0;
	
			for (x in v, o) {
				var opt = document.createElement("option");
				opt.value = v[x];
				opt.innerHTML = o[x];
				target.appendChild(opt);
			}
		}
	
		// 유효성 검증
		function nullCheck() {
			var _cTitle = $("#cTitle").val();
			var _basicPrice = $("#basicPrice").val();
			var _cField = $("#cField").val();
			var _tool = $("#tool").val();
			var _cContents = $("#cContents").val();
	
			if (_cTitle == "" || _basicPrice == "" || _cField == "empty"
					|| _tool == "tool" || _cContents == "") {
				alert("빈칸없이 입력하세요");
				$('#coachingWrite').attr('onSubmit', "return false;");
			} else {
				$('#coachingWrite').removeAttr('onSubmit');
			}
		}
		