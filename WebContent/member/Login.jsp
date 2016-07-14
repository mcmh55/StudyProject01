<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.sql.Connection" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

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

<%!	// 선언부는 첫 방문자에 의해서 단 한번 수행
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "hr";
	String pw = "hr";
	String sql = "SELECT * FROM TEST_MEMBER";
%>

<form action="login_do" method="post" name="frm_login">
<!-- 로그인 영역 -->
<div class="box">

<h1>환영합니다!</h1>

<!-- 입력 -->
<input type="text" name="input_login_id" class="input" value="아이디 입력" 
			onFocus="fieldFocus(this, '아이디 입력');" onblur="fieldBlur(this, '아이디 입력');"/>
			
<input type="password" name="input_login_pw" class="input" value="password"
			onFocus="fieldFocus(this, 'password');" onblur="fieldBlur(this, 'password');"/>
<!-- //입력 -->
<br><br>

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
<input type="button" class="btn_join" value="회원가입" onclick="location.href='join_do'"/>
</div>
<div>
<input type="submit" id="btn_login" class="btn_login" value="로그인" onclick="return checkInputEmptyLogin()">
</div>
<!-- //버튼 -->

</div>
<!-- //로그인 영역 -->
  
</form>

<p>아이디 또는 비밀번호를 잊어버리셨나요?</p>

<!-- 테스트용 테이블 -->

<table width="400" border="1">
<tr>
	<th>아이디</th>
	<th>암호</th>
	<th>별명</th>
	<th>나이</th>
</tr>
	
<%
	try {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url, id, pw);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		while ( rs.next() ) {
			
			out.println("<tr>");
			out.println("<td>" + rs.getString("ID") + "</td>");
			out.println("<td>" + rs.getString("PW") + "</td>");
			out.println("<td>" + rs.getString("NICK") + "</td>");
			out.println("<td>" + rs.getInt("AGE") + "</td>");
			out.println("</tr>");
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		
		try {
			
			if ( rs != null ) rs.close();
			if ( stmt != null ) stmt.close();
			if ( conn != null ) conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
%>

</table>

</body>
</html>