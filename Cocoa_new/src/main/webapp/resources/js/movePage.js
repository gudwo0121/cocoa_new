	
	// 비동기 페이지 이동
	function movePage(url){
	    var page = {
 		        url : url,
	            async : true,
	            type : "POST",
	            dataType : "html",
	            cache : false
	    };
	    
	    $.ajax(page).done(function(data){
	        // 영역 삭제
	        $('#main').children().remove();
	        // 영역 교체
	        $('#main').html(data);
	    });
	}