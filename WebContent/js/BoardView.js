// 수정, 삭제 권한

function checkAuthor(loginId, boardId) {
	
	if ( loginId == boardId ) {
		return true;
	} else {
		
		alert("권한이 없습니다.");
		
		return false;
	}
}

// 댓글 저장
$(function(){
	
	$("#btn_write_comment").click(function(){
		$("#command").val("board_comment");
	});
	
	$("#btn_update_form").click(function(){
		$("#command").val("board_update_form");
	});
	
});
