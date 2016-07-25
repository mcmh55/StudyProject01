<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 보기</title>

<link rel="stylesheet" href="css/Board.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/BoardView.js"></script>

</head>
<body>

<c:set var="loginUser" value="${ sessionScope.loginUser }"/>
<c:set var="board" value="${ requestScope.boardView }"/>
<div class="box">

	<form name="frm_board_view" action="boardControll" method="post">
	
		<input type="hidden" name="command" value="board_update_form"/>
		<input type="hidden" name="seq" value="${ board.seq }"/>
		<input type="hidden" name="id" value="${ board.id }"/>
		<input type="hidden" name="pw" value="${ board.pw }"/>
		<input type="hidden" name="title" value="${ board.title }"/>
		<input type="hidden" name="content" value="${ board.content }"/>
		<input type="hidden" name="filename" value="${ board.filename }"/>
		
		<table class="tbl_view">
		<col width="100px"/><col width="500px"/>
		<tr>
			<th>작성자</th>
			<td>${ board.id }</td>
		</tr>
		<tr>
			<th>첨부 파일</th>
			<td>
			<c:if test="${ board.filename != '' }">
				<a href="">${ board.filename }</a>
			</c:if>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${ board.title }</td>
		</tr>
		<tr>
			<td colspan="2">
			${ board.content }
			</td>
		</tr>
		</table>
	
		<div class="foot">
			<div class="area_bottom_btn">
				<input type="button" id="btn_reply" class="btn_blue" value="답글" onclick=""/>
				
				<input type="submit" id="btn_update_form" class="btn_blue" value="수정" 
				onclick="return checkAuthor('${ loginUser.id }', '${ board.id }')"/>
				
				<input type="button" id="btn_delete" class="btn_blue" value="삭제" onclick=""/>
				
				<input type="button" id="btn_list" class="btn_green" value="목록" 
				onclick="location.href='boardControll?command=board_list'"/>
			</div>
		</div>
	
	</form>
	
</div>

</body>
</html>