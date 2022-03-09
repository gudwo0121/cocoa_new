	
	// 전화번호 하이픈 자동 생성
	$(document).on("keyup", "#phone", function() {
		$(this).val(
				$(this).val().replace(
						/[^0-9]/g, "").replace(
								/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/, "$1-$2-$3").replace(
										"--", "-"));
	});