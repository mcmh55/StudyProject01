<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="DTO.MemberDTO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보 수정</title>

<link rel="stylesheet" type="text/css" href="css/MemberInfo.css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
<script src="js/MemberUpdate.js" type="text/javascript"></script>

</head>
<body>

<%

MemberDTO memdto = (MemberDTO) session.getAttribute("loginUser");

String id = memdto.getId();
String nick = memdto.getNick();
int age = memdto.getAge();
String email = memdto.getEmail();

%>

<div class="box">

<h1>회원 정보 수정</h1>

<form name="frm_member_update" action="memberUpdate_do" method="post">

<table align="center">
<col width="160px"/><col width="240px"/>
<tr>
	<th>아이디</th>
	<td class="memdata"><%= id %></td>
</tr>
<tr>
	<th>새로운 비밀번호</th>
	<td><input type="password" name="pw" value=""/></td>
</tr>
<tr>
	<th>새로운 비밀번호 확인</th>
	<td><input type="password" name="pw_confirm" value=""/></td>
</tr>
<tr>
	<th>별명</th>
	<td><input type="text" name="nick" value="<%= nick %>"/></td>
</tr>
<tr>
	<th>나이</th>
	<td><input type="text" name="age" value="<%= age %>"/></td>
</tr>
<tr>
	<th>이메일</th>
	<td><input type="text" name="email" value="<%= email %>"/></td>
</tr>
</table>

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

<input type="button" class="btn_cancel" value="취소" onclick="location.href='memberInfo_do'"/>
<input type="submit" class="btn_update" value="수정" onclick="return checkInputEmpty()"/>

</form>

</div>

</body>
</html>