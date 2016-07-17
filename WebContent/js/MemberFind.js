// 공백 체크
function checkInputEmpty() {
	
	var inputEmail = document.frm_memberFind.email;
	
	if ( inputEmail.value == "이메일 입력" ) {
		
		alert("이메일을 입력해주세요.");
		inputEmail.focus();
		
		return false;
		
	} else {
		return true;
	}
}

// [입력칸]
// 입력칸에 기본 글자가 설정된 상태에서 클릭 시 공백으로 설정
function fieldFocus(field, str){
	if ( field.value == str ) field.value = '';
}

// 입력칸이 공백이면 기본 글자로 설정
function fieldBlur(field, str){
	if( field.value == '' )	field.value = str;
}

//Fade in dashboard box
$(document).ready(function(){
    $('.box').hide().fadeIn(1000);
});
