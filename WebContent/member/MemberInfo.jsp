<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="DTO.MemberDTO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보</title>

<link rel="stylesheet" type="text/css" href="css/MemberInfo.css">

</head>
<body>

<%

MemberDTO memdto = (MemberDTO) session.getAttribute("loginUser");

String id = memdto.getId();
String nick = memdto.getNick();
int age = memdto.getAge();

%>

<div class="box">

<h1>회원 정보</h1>

<table align="center">
<col width="200px"/><col width="200px"/>
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

<form name="frm_MemberInfo" action="memberInfo_do" method="post">

<input type="button" class="btn_back" value="돌아가기" onclick="location.href='bbsList_do'"/>
<input type="submit" class="btn_update_member" value="회원정보 수정" onclick="location.href='updateMember_do'"/>

</form>

</div>

</body>
</html>