// 공백 체크
function checkInputEmpty() {
	
	var inputID = document.frm_join.id;
	var inputPW = document.frm_join.pw;
	var inputPWConfirm = document.frm_join.pw_confirm;
	var inputNick = document.frm_join.nick;
	var inputAge = document.frm_join.age;
	
	if ( inputID.value == "" || inputID.value == "아이디 입력" ) {
		
		alert("아이디를 입력해주세요.");
		inputID.focus();
		
		return false;
		
	} else if ( inputPW.value == "" || inputPW.value == "암호 입력" ) {
		
		alert("암호를 입력해주세요.");
		inputPW.focus();
		
		return false;
		
	} else if ( inputPWConfirm.value == "" || inputPW.value == "암호 입력" ) {
		
		alert("확인용 암호를 입력해주세요.");
		inputPW.focus();
		
		return false;
		
	} else if ( inputPW.value != inputPWConfirm.value ) {
		
		alert("암호가 서로 다릅니다.");
		inputPW.focus();
		
		return false;
		
	} else if ( inputNick.value == "" || inputNick.value == "별명 입력" ) {
		
		alert("별명을 입력해주세요.");
		inputNick.focus();
		
		return false;
		
	} else if ( inputAge.value == "" || inputAge.value == "나이 입력" ) {
		
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