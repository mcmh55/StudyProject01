<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" href="css/Join.css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
<script src="js/Login.js" type="text/javascript"></script>
<script src="js/Join.js" type="text/javascript"></script>

</head>
<body>

<!-- 
<form name="frm_join">
 -->
 
<form method="post" name="frm_join" action="join_do">

<!-- 회원가입 영역 -->
<div class="box">

<h1>회원 가입</h1>

<!-- 입력 -->
<input type="text" name="input_join_id" value="아이디 입력" class="input" 
			onFocus="fieldFocus(this, '아이디 입력');" onblur="fieldBlur(this, '아이디 입력');"/>
			
<input type="password" name="input_join_pw" value="암호 입력" class="input" 
			onFocus="fieldFocus(this, '암호 입력');" onblur="fieldBlur(this, '암호 입력');"/>
			
<input type="text" name="input_join_nick" value="별명 입력" class="input"
			onFocus="fieldFocus(this, '별명 입력');" onblur="fieldBlur(this, '별명 입력');" />
			
<input type="text" name="input_join_age" value="나이 입력" class="input" 
			onFocus="fieldFocus(this, '나이 입력');" onblur="fieldBlur(this, '나이 입력');"/>
<!-- //입력 -->
<br/><br/>
<!-- 안내 메시지 -->
<div id="msg">
<%
	String sysMsg = (String) request.getAttribute("message");
	if ( sysMsg != null ) {
		out.print(sysMsg);
	}
%>
</div>
<!-- //안내 메시지 -->

<!-- 버튼 -->
<div>
<input type="button" id="btn_cancle" value="취소" onclick="location.href='login_do'"/>
</div>
<div>
<input type="submit" id="btn_confirm" value="가입" onclick="return checkInputEmptyJoin()"/>
</div>
<!-- //버튼 -->
</div>
<!-- //회원가입 영역 -->

</form>

</body>
</html>