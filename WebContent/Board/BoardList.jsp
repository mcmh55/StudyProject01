<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ page import = "java.util.Date" %>
<%@ page import = "java.text.SimpleDateFormat" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>

<link rel="stylesheet" href="css/Board.css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
<script src="js/Login.js"></script>

</head>
<body>

<!-- 날짜 정보 -->
<c:set var="objDate" value="<%= new Date() %>"></c:set>
<c:set var="dateFormat" value="<%= new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss.0\") %>"/>
<c:set var="curdate" value="${ dateFormat.format(objDate) }"/>
<!-- //날짜 정보 -->

<!-- 백그라운드 영역 -->
<div class="box">

	<h1>게시판</h1>
	
	<table>
	<col width="50"/><col width="300"/><col width="70"/><col width="50"/><col width="50"/>
	
	<tr>
		<th>No.</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th>
	<c:forEach var="board" items="${ boardList }">
		<tr class="record">
			<td class="seq">${ board.seq }</td>
			<td class="title">
				<a href="boardControll?command=board_view&seq=${ board.seq }">${ board.title }</a>
			</td>
			<td>${ board.id }</td>
			<!-- 작성 날짜와 현재 날짜를 '연.월.일'만 비교하여 날짜가 같으면 '분:초'로 표시 / 다르면 '연.월.일'로 표시 -->
			<c:choose>
				<c:when test="${ board.writedate.toString().substring(0,10) == curdate.substring(0,10) }">
					<td class="writedate">
						<fmt:formatDate value="${ board.writedate }" pattern="HH:mm"/>
					</td>
				</c:when>
				<c:otherwise>
					<td class="writedate">
						<fmt:formatDate value="${ board.writedate }" pattern="yyyy.MM.dd"/>
					</td>
				</c:otherwise>
			</c:choose>
			
			<td class="readcount">${ board.readcount }</td>
		</tr>
	</c:forEach>
	
	</table>
	
	<div class="area_page_num">
		1 2 3 4 5
	</div>
	
	<div align="right">
		<input type="button" class="btn_board_write" value="글쓰기" 
		onclick="location.href='boardControll?command=board_write_form'"/>
	</div>
	
	<input type="button" class="btn_logout" value="로그아웃" onclick="location.href='logout_do'"/>
	<input type="button" class="btn_member_info" value="내 정보"/>

</div>
<!-- //백그라운드 영역 -->

</body>
</html>