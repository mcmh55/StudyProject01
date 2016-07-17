<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>계정 찾기</title>

<link rel="stylesheet" type="text/css" href="css/MemberFind.css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
<script src="js/MemberFind.js" type="text/javascript"></script>

</head>
<body>

<form action="memberFind_do" method="post" name="frm_memberFind">
<!-- 백그라운드 영역 -->
<div class="box">

<h1>계정 찾기</h1>

<!-- 입력 -->
<input type="text" name="email" class="input" value="이메일 입력" 
			onFocus="fieldFocus(this, '이메일 입력');" onblur="fieldBlur(this, '이메일 입력');"/>
<!-- //입력 -->

<!-- 안내 메시지 -->
<div id="msg">
<%
	String msg_input = (String) request.getAttribute("msg");
	
	if ( msg_input != null ) {
%>
		<br/>
<%		
		out.print(msg_input);
	}
%>
</div>
<!-- //안내 메시지 -->

<!-- 버튼 -->
<div>
<input type="button" class="btn_back" value="돌아가기" onclick="location.href='login_do'"/>
</div>
<div>
<input type="submit" class="btn_send" value="전송" onclick="return checkInputEmpty()">
</div>
<!-- //버튼 -->

</div>
<!-- //백그라운드 영역 -->
  
</form>

</body>
</html>