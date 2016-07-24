// 공백 체크
function boardCheck() {
	
	var pw = document.frm_write.pw;
	var title = document.frm_write.title;
	var content = document.frm_write.content;
	
	if ( pw.value.length == 0 ) {
		
		alert("비밀번호를 입력해주세요.");
		pw.focus();
		
		return false;
		
	} else if ( isNaN(pw.value) ) {
		
		alert("비밀번호는 숫자만 가능합니다.");
		pw.focus();
		
		return false;
		
	} else if ( title.value.length == 0 ) {
		
		alert("제목을 입력해주세요.");
		title.focus();
		
		return false;
		
	} else if ( content.value.length == 0 ) {
		
		alert("내용을 입력해주세요.");
		content.focus();
		
		return false;
		
	}
	
	return true;
}

// 내용 글자 수 제한

/*
 * [유니코드 범위]
 * 숫자 0~9	=> 48~57
 * 영문 대문자	=> 65~90
 *    소문자	=> 97~122
 * 한글 가~힣	=> 45032~55203
 *     자음	=> 12593~12622
 *     모음	=> 12623~12643
*/

function checkLength(content) {
    var maxByte = 5000;		// 최대 바이트(글자 개수)
    var contentVal = content.value;
 
    var textByte = 0, textLen = 0;
    var changeText = "";
 
    for ( var i = 0; i < contentVal.length; i++ ) {
    	
    	textByte++;			// DB DataType이 NCLOB이기 때문에 모든 문자는 1Byte
    	
		if ( textByte <= maxByte ) {
			textLen = i + 1;
		}
    }
 
    if ( textByte > maxByte ) {
    	
    	alert(maxByte + "자까지 입력 가능합니다.");
        changeText = contentVal.substr(0, textLen);
        content.value = changeText;
        
    }
}
