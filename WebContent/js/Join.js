// 공백 체크
function checkInputEmpty() {
	
	var inputID = document.frm_join.id;
	var inputPW = document.frm_join.pw;
	var inputPWConfirm = document.frm_join.pw_confirm;
	var inputNick = document.frm_join.nick;
	var inputAge = document.frm_join.age;
	var inputEmail = document.frm_join.email;
	
	if ( inputID.value == "" || inputID.value == "아이디 입력" ) {
		
		alert("아이디를 입력해주세요.");
		inputID.focus();
		
		return false;
		
	} else if ( inputPW.value == "" || inputPW.value == "비밀번호 입력" ) {
		
		alert("비밀번호를 입력해주세요.");
		inputPW.focus();
		
		return false;
		
	} else if ( inputPWConfirm.value == "" || inputPW.value == "비밀번호 입력" ) {
		
		alert("확인용 비밀번호를 입력해주세요.");
		inputPW.focus();
		
		return false;
		
	} else if ( inputPW.value != inputPWConfirm.value ) {
		
		alert("비밀번호가 서로 다릅니다.");
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
		
	} else if ( inputAge.value > 120 ) {
		
		alert("나이를 정확히 입력해주세요.");
		unputAge.focus();
		
		return false;
		
	} else if ( inputEmail.value == "" || inputEmail.value == "이메일 입력" ) {
		
		alert("이메일을 입력해주세요.");
		inputEmail.focus();
		
		return false;
		
	} else {
		return true;
	}
}

//[입력칸]
//입력칸에 기본 글자가 설정된 상태에서 클릭 시 공백으로 설정
function fieldFocus(field, str){
	if ( field.value == str ) field.value = '';
}

//입력칸이 공백이면 기본 글자로 설정
function fieldBlur(field, str){
	if( field.value == '' )	field.value = str;
}

//Fade in dashboard box
$(document).ready(function(){
    $('.box').hide().fadeIn(1000);
});
