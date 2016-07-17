<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.Connection" %>

<%@ page import = "DB.DBControll" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>

<link rel="stylesheet" type="text/css" href="css/Login.css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
<script src="js/Login.js" type="text/javascript"></script>

</head>
<body>

<%

String msgInfo = (String) request.getAttribute("msg_alarm");

if ( msgInfo != null ) {
%>
	<script type="text/javascript">
	alert("<%= msgInfo %>");
	</script>
<%	
}
%>

<form action="login_do" method="post" name="frm_login">
<!-- 로그인 영역 -->
<div class="box">

<h1>환영합니다!</h1>

<!-- 입력 -->
<input type="text" name="id" class="input" value="아이디 입력" 
			onFocus="fieldFocus(this, '아이디 입력');" onblur="fieldBlur(this, '아이디 입력');"/>
			
<input type="password" name="pw" class="input" value="password"
			onFocus="fieldFocus(this, 'password');" onblur="fieldBlur(this, 'password');"/>
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
<input type="button" class="btn_join" value="회원가입" onclick="location.href='join_do'"/>
</div>
<div>
<input type="submit" id="btn_login" class="btn_login" value="로그인" onclick="return checkInputEmpty()">
</div>
<!-- //버튼 -->

</div>
<!-- //로그인 영역 -->
  
</form>

<p><a href="memberFind_do">아이디 또는 비밀번호를 잊어버리셨나요?</a></p>

<!-- 테스트용 테이블 -->

<table width="400" border="1">
<tr>
	<th>아이디</th>
	<th>암호</th>
	<th>별명</th>
	<th>나이</th>
	<th>이메일</th>
</tr>
	
<%

Connection conn = null;
PreparedStatement psmt = null;
ResultSet rs = null;

try {

	String sql = "SELECT * FROM MEMBER";
	
	conn = DBControll.getConnection();
	psmt = conn.prepareStatement(sql);
	rs = psmt.executeQuery();
	
	while ( rs.next() ) {
		
		out.println("<tr>");
		out.println("<td>" + rs.getString("ID") + "</td>");
		out.println("<td>" + rs.getString("PW") + "</td>");
		out.println("<td>" + rs.getString("NICK") + "</td>");
		out.println("<td>" + rs.getInt("AGE") + "</td>");
		out.println("<td>" + rs.getString("EMAIL") + "</td>");
		out.println("</tr>");
		
	}
} catch (Exception e) {
	e.printStackTrace();
} finally {
	DBControll.closeDatabase(conn, psmt, rs);
}

%>

</table>

<!-- //테스트용 테이블 -->

</body>
</html>