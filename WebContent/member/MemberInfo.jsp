<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="DTO.MemberDTO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보</title>

<link rel="stylesheet" type="text/css" href="css/MemberInfo.css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
<script src="js/MemberInfo.js" type="text/javascript"></script>

</head>
<body>

<%

MemberDTO memdto = (MemberDTO) session.getAttribute("loginUser");

String id = memdto.getId();
String nick = memdto.getNick();
int age = memdto.getAge();
String email = memdto.getEmail();

%>

<!-- 안내 메시지 -->
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
<!-- //안내 메시지 -->

<div class="box">

<h1>회원 정보</h1>

<form name="frm_MemberInfo" action="memberUpdate_do">

<table align="center">
<col width="160px"/><col width="240px"/>
<tr>
	<th>아이디</th>
	<td class="memdata"><%= id %></td>
</tr>
<tr>
	<th>별명</th>
	<td class="memdata"><%= nick %></td>
</tr>
<tr>
	<th>나이</th>
	<td class="memdata"><%= age %></td>
</tr>
<tr>
	<th>이메일</th>
	<td class="memdata"><%= email %></td>
</tr>
</table>

<input type="button" class="btn_back" value="돌아가기" onclick="location.href='boardControll?command=board_list'"/>
<input type="submit" class="btn_update_member" value="회원정보 수정"/>

<table align="center" width="100%">
<tr>
	<td>
	<input type="button" class="btn_delete_member" value="회원 탈퇴" onclick="reconfirmMemberDelete()"/>
	</td>
</tr>
</table>

</form>

</div>

</body>
</html>