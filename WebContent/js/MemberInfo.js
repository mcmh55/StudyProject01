// 회원 탈퇴 되묻기
function reconfirmMemberDelete() {
	
	var result = confirm("정말 회원 탈퇴를 하시겠습니까?");
	
	if ( result == true ) {
		location.href="memberDelete_do";
	} else {
		return;
	}
	
}

//Fade in dashboard box
$(document).ready(function(){
    $('.box').hide().fadeIn(1000);
});
