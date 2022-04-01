	
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

	$(document).ready(function() {
		
		// 지도 검색 (조회)
		$.ajax({
		
			type : "GET",
			url : "/cocoa/searchMap",
			contentType : "application/json",
			data : {"addr" : $("#addr").val()},
			
			success : function(data, textStatus) {
			
				// 반환 결과를 파싱 후 저장
				resultText = JSON.parse(data);
				
				// 좌표 찍기 = Pin 처리
				var lang1 = resultText.addresses[0].x;
				var lat1 = resultText.addresses[0].y;
				var mapOptions = {
					center : new naver.maps.LatLng(lat1, lang1),
					zoom : 15
				};
				var map = new naver.maps.Map('map', mapOptions);
				var marker = new naver.maps.Marker({
					position : new naver.maps.LatLng(lat1, lang1),
					map : map
				});
			},
			
			error : function(data, textStatus) {
				alert("지도를 불러오지 못했습니다. 다시 시도해주세요.");
			},
			
			complete : function(data, textStatus) {
			}
			
		});
		
		// 지도 검색 (수정)
		$('#search').click(function() {
			// 지도 표시 유지
			event.preventDefault();
			
			$.ajax({
			
				type : "GET",
				url : "/cocoa/searchMap",
				contentType : "application/json",
				data : {"addr" : $("#addr").val()},
				
				success : function(data, textStatus) {
				
					// 반환 결과를 파싱 후 저장
					resultText = JSON.parse(data);
					
					// 좌표 찍기 = Pin 처리
					var lang1 = resultText.addresses[0].x;
					var lat1 = resultText.addresses[0].y;
					var mapOptions = {
						center : new naver.maps.LatLng(lat1, lang1),
						zoom : 15
					};
					var map = new naver.maps.Map('map', mapOptions);
					var marker = new naver.maps.Marker({
						position : new naver.maps.LatLng(lat1, lang1),
						map : map
					});
				},
				
				error : function(data, textStatus) {
					alert("검색 불가! 다시 시도해주세요.");
				},
				
				complete : function(data, textStatus) {
				}
				
			});
		});
	});