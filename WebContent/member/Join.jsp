<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" href="css/Join.css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
<script src="js/Join.js" type="text/javascript"></script>

</head>
<body>

<div class="box">
 
<form method="post" name="frm_join" action="join_do">

<h1>회원 가입</h1>

<table align="center">
<col width="150px"/><col width="250px"/>
<tr>
	<th>아이디</th>
	<td>
	<input type="text" name="id" value="아이디 입력"
			onFocus="fieldFocus(this, '아이디 입력');" onblur="fieldBlur(this, '아이디 입력');"/>
	</td>
</tr>
<tr>
	<th>비밀번호</th>
	<td>
	<input type="password" name="pw" value="비밀번호 입력"
			onFocus="fieldFocus(this, '비밀번호 입력');" onblur="fieldBlur(this, '비밀번호 입력');"/>
	</td>
</tr>
<tr>
	<th>비밀번호 확인</th>
	<td>
	<input type="password" name="pw_confirm" value="비밀번호 입력"
			onFocus="fieldFocus(this, '비밀번호 입력');" onblur="fieldBlur(this, '비밀번호 입력');"/>
	</td>
</tr>
<tr>
	<th>별명</th>
	<td>
	<input type="text" name="nick" value="별명 입력"
			onFocus="fieldFocus(this, '별명 입력');" onblur="fieldBlur(this, '별명 입력');" />
	</td>
</tr>
<tr>
	<th>나이</th>
	<td>
	<input type="text" name="age" value="나이 입력"
			onFocus="fieldFocus(this, '나이 입력');" onblur="fieldBlur(this, '나이 입력');"/>
	</td>
</tr>
<tr>
	<th>이메일</th>
	<td>
	<input type="text" name="email" value="이메일 입력"
			onFocus="fieldFocus(this, '이메일 입력');" onblur="fieldBlur(this, '이메일 입력');"/>
	</td>
</tr>
</table>

<!-- 안내 메시지 -->
<div id="msg">
<%
	String sysMsg = (String) request.getAttribute("msg");
	
	if ( sysMsg != null ) {
%>
		<br/>
<%		
		out.print(sysMsg);
	}
%>
</div>
<!-- //안내 메시지 -->

<input type="button" id="btn_cancel" value="취소" onclick="location.href='login_do'"/>
<input type="submit" id="btn_confirm" value="가입" onclick="return checkInputEmpty()"/>

</form>

</div>

</body>
</html>