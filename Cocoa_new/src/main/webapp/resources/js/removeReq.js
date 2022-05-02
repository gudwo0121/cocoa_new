
$(document).ready(function() {

		$('#delRequest').click(function() {
		
			var _reqNO = $('#reqNO').val();
			var _req = $('#req').val();

			// 보낸 대기 요청 삭제
			$.ajax({
				type : "post",
				async : true,
				url : "/cocoa/delRequest",
				dataType : "json",
				data : {
					"reqNO" : _reqNO,
					"req" : _req,
				},

				success : function(data, textStatus) {
					alert("철회 완료!");
					location.href = "/cocoa/request/sent";
				},

				error : function(data) {
					alert("철회 실패, 다시 시도해주세요.");
				},
			});
		});
	});
	