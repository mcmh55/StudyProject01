// 공백 체크
function checkInputEmptyLogin() {
	
	var inputID = document.frm_login.input_login_id;
	var inputPW = document.frm_login.input_login_pw;
	
	if ( inputID.value == "아이디 입력" ) {
		
		alert("아이디를 입력해주세요.");
		inputID.focus();
		
		return false;
		
	} else if ( inputPW.value == "password" ) {
		
		alert("암호를 입력해주세요.");
		inputPW.focus();
		
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

// [버튼 처리]
//Fade in dashboard box
$(document).ready(function(){
    $('.box').hide().fadeIn(1000);
});

//Stop click event
$('a').click(function(event){
    event.preventDefault(); 
});