// 회원 탈퇴 되묻기
function reconfirmMemberLeave() {
	
	var result = confirm("정말 회원 탈퇴를 하시겠습니까?");
	
	if ( result == true ) {
		location.href="memberLeave_do";
	} else {
		return;
	}
	
}