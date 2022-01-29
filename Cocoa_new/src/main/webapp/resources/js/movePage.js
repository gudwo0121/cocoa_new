	
	// 비동기 페이지 이동
	function movePage(url){
	
	    $.ajax({
	    		url : url,
	    		data : {page: 1},
	    		type : "GET",
	    		success : (result) => {
				$("#main").html(result);
			}
		});
		
		history.pushState({
			data : "/cocoa/goCoachingList",
			page : 1
		}, null, "/cocoa/goCoachingList?page=1"); 
	};
	
	$(window).on('popstate', function (event) {
		
		const data = event.originalEvent.state;
		
		$.ajax({
			url : data.data,
			data : {page : data.page, type : data.type, keyword : data.keyword},
			type : "GET",
			success : (result) => {
				$("#main").html(result);
			}
		})
	});
		
		
		