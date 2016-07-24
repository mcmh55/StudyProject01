<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 작성</title>

<link rel="stylesheet" href="css/Board.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/BoardWrite.js"></script>

</head>
<body>

<!-- 로그인 유저 정보 -->
<c:set var="loginUser" value="${ sessionScope.loginUser }"></c:set>
<!-- //로그인 유저 정보 -->

<div id="wrap" class="box">

	<form name="frm_write" method="post" action="boardControll">
	
	<input type="hidden" name="command" value="board_write"/>
	<input type="hidden" name="id" value="${ loginUser.id }"/>
	
	<table class="tbl_write">
	<col width="100px"/><col width="500px"/>
	<tr>
		<th>작성자</th>
		<td>${ loginUser.id }</td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="password" name="pw" size="4" maxlength="4"/> <font color="red" size="2">*필수 입력</font></td>
	</tr>
	<tr>
		<th>파일 첨부</th>
		<td><input type="file" name="filename"/></td>
	</tr>
	<tr>
		<th>제목</th>
		<td><input type="text" name="title" size="80" maxlength="50"/></td>
	</tr>
	<tr>
		<td colspan="2" class="txtarea">
		<textarea rows="30" id="content" name="content" onkeyup="checkLength(this)"></textarea>
		</td>
	</tr>
	</table>

	<div class="foot">
		<div class="area_bottom_btn">
			<input type="button" class="btn_cancel" value="취소" onclick="location.href='boardControll?command=board_list'"/>
			<input type="submit" class="btn_write" value="확인" onclick="return boardCheck()"/>
		</div>
	</div>
	
	</form>

</div>

</body>
</html>