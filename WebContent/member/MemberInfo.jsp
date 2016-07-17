<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="DTO.MemberDTO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보</title>

<link rel="stylesheet" type="text/css" href="css/MemberInfo.css">

<script src="js/MemberInfo.js" type="text/javascript"></script>

</head>
<body>

<%

MemberDTO memdto = (MemberDTO) session.getAttribute("loginUser");

String id = memdto.getId();
String nick = memdto.getNick();
int age = memdto.getAge();

%>

<!-- 안내 메시지 -->
<%
	String sysMsg = (String) request.getAttribute("msg_update_member");
	
	if ( sysMsg != null ) {
%>
		<script type="text/javascript">
		alert("<%= sysMsg %>");
		</script>
<%		
	}
%>
<!-- //안내 메시지 -->

<div class="box">

<h1>회원 정보</h1>

<form name="frm_MemberInfo" action="memberUpdate_do">

<table align="center">
<col width="150px"/><col width="250px"/>
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
</table>

<input type="button" class="btn_back" value="돌아가기" onclick="location.href='bbsList_do'"/>
<input type="submit" class="btn_update_member" value="회원정보 수정"/>

<table align="center" width="100%">
<tr>
	<td>
	<input type="button" class="btn_leave_member" value="회원 탈퇴" onclick="reconfirmMemberLeave()"/>
	</td>
</tr>
</table>

</form>

</div>

</body>
</html>