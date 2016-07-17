// 공백 체크
function checkInputEmpty() {
	
	var inputPW = document.frm_member_update.pw;
	var inputPWConfirm = document.frm_member_update.pw_confirm;
	var inputNick = document.frm_member_update.nick;
	var inputAge = document.frm_member_update.age;
	var inputEmail = document.frm_member_update.email;
	
	if ( inputPW.value != inputPWConfirm.value ) {
			
		alert("비밀번호가 서로 다릅니다.");
		inputPW.focus();
		
		return false;
			
	} else if ( inputNick.value == "" ) {
		
		alert("별명을 입력해주세요.");
		inputNick.focus();
		
		return false;
		
	} else if ( inputAge.value == "" ) {
		
		alert("나이를 입력해주세요.");
		inputAge.focus();
		
		return false;
		
	} else if ( isNaN(inputAge.value) ) {
		
		alert("나이에 숫자를 입력해주세요.");
		inputAge.focus();
		
		return false;
		
	} else {
		return true;
	}
}