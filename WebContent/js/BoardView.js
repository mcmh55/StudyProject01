// 수정, 삭제 권한 체크
function checkAuthor(loginId, boardId) {
	
	if ( loginId == boardId ) {
		return true;
	} else {
		
		alert("권한이 없습니다.");
		
		return false;
	}
}