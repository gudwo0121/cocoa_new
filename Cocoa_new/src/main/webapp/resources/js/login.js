
$(document).ready(function() {

		$('#login').click(function() {

			var _id = $("#inputID").val();
			var _pwd = $("#inputPassword").val();

			// 아이디, 비번 유효성 검증
			if (_id == "") {
				alert("아이디를 입력하세요");
				return;
			} else if (_pwd == "") {
				alert("비밀번호를 입력하세요");
				return;
			}

			// 로그인 검증
			$.ajax({
				type : "post",
				async : true,
				url : "/cocoa/login",
				dataType : "json",
				data : {
					"id" : _id,
					"pwd" : _pwd
				},

				success : function(data, textStatus) {
					alert("환영합니다!");
					location.href = "/cocoa/";
				},

				error : function(data) {
					alert("아이디나 비밀번호가 틀립니다.");
				},
			});
		});
	});
	