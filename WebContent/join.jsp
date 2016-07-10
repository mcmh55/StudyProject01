<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" href="css/style_join.css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
<script src="js/login.js"></script>
<script src="js/join.js"></script>

</head>
<body>

<!-- 
<form name="frm_join">
 -->
 
<form method="post" name="frm_join" action="join.do">

<!-- 회원가입 영역 -->
<div class="box">

<h1>회원 가입</h1>

<!-- 입력 -->

<input type="text" name="input_join_id" value="아이디 입력" onFocus="fieldFocus(this, '아이디 입력');" onblur="fieldBlur(this, '아이디 입력');" class="input" />
<input type="password" name="input_join_pw" value="암호 입력" onFocus="fieldFocus(this, '암호 입력');" onblur="fieldBlur(this, '암호 입력');" class="input" />
<input type="text" name="input_join_nick" value="별명 입력" onFocus="fieldFocus(this, '별명 입력');" onblur="fieldBlur(this, '별명 입력');" class="input" />
<input type="text" name="input_join_age" value="나이 입력" onFocus="fieldFocus(this, '나이 입력');" onblur="fieldBlur(this, '나이 입력');" class="input" />

<!-- //입력 -->

<!-- 버튼 -->
<div>
<button type="button" id="btn_cancle" onclick="location.href='login.jsp'">취소</button>
</div>
<div>
<input type="submit" id="btn_confirm" value="가입" onclick="return joinEmptyCheck()"/>
</div>
<!-- //버튼 -->
</div>
<!-- //회원가입 영역 -->

</form>

<br/>



</body>
</html>