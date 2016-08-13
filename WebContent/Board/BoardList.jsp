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
<script src="js/Board_List.js"></script>

</head>
<body>

<!-- 날짜 정보 -->
<c:set var="objDate" value="<%= new Date() %>"/>
<c:set var="dateFormat" value="<%= new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss.0\") %>"/>
<c:set var="curDate" value="${ dateFormat.format(objDate) }"/>
<!-- //날짜 정보 -->

<!-- 여백 -->
<c:set var="tap" value="&nbsp;&nbsp;&nbsp;&nbsp;"/>

<!-- 백그라운드 영역 -->
<div class="box">

	<h1>게시판</h1>
	
	<table class="tbl_boardList">
	<col width="50"/><col width="300"/><col width="70"/><col width="50"/><col width="50"/>
	
	<tr>
		<th>No.</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th>
	<c:forEach var="board" items="${ boardList }">
		<tr class="board_info">
			<c:choose>
				<c:when test="${ board.depth == 0 }">
					<td class="seq">${ board.seq }</td>
				</c:when>
				<c:otherwise>
					<td></td>
				</c:otherwise>
			</c:choose>
			
			<%-- <td class="seq">${ board.seq }</td> --%>
			<td class="title">
				<c:if test="${ board.depth > 0 }">
					<c:forEach begin="1" end="${ board.depth }">
						&nbsp;
					</c:forEach>
					<img src="images/icon_reply.gif"/>
					<c:if test="${ board.parentDel == 1 }">
						<font color="#6A65BB" style="font-size:10pt">[원글이 삭제된 답글]</font>
					</c:if>
				</c:if>
				<a href="boardControll?command=board_view&seq=${ board.seq }">${ board.title }</a>
			</td>
			<td>${ board.id }</td>
			<!-- 작성 날짜와 현재 날짜를 '연.월.일'만 비교하여 날짜가 같으면 '분:초'로 표시 / 다르면 '연.월.일'로 표시 -->
			<c:choose>
				<c:when test="${ board.writeDate.toString().substring(0,10) == curDate.substring(0,10) }">
					<td class="write_date">
						<fmt:formatDate value="${ board.writeDate }" pattern="HH:mm"/>
					</td>
				</c:when>
				<c:otherwise>
					<td class="write_date">
						<fmt:formatDate value="${ board.writeDate }" pattern="yyyy.MM.dd"/>
					</td>
				</c:otherwise>
			</c:choose>
			
			<td class="read_count">${ board.readCount }</td>
		</tr>
	</c:forEach>
	
	</table>
	
	<div class="area_page_num">
		1 2 3 4 5
	</div>
	
	<div class="area_search">
		<form action="boardControll">
		
			<input type="hidden" name="command" value="board_search"/>
			<input type="hidden" id="search_type" name="search_type"/>
			<input type="hidden" id="search_text" name="search_text"/>
		
			<select id="search_type_list" name="search_type_list">
				<option label="제목+내용" value="1"/>
				<option label="제목" value="2"/>
				<option label="글 작성자" value="3"/>
			</select>
			<input type="text" id="input_search_text"/>
			<input type="submit" id="btn_search" class="btn_blue" value="검색"
			onclick="return checkEmpty()"/>
		</form>
	</div>
	
	<div align="right">
		<input type="button" class="btn_board_write" value="글쓰기" 
		onclick="location.href='boardControll?command=board_write_form'"/>
	</div>
	
	<input type="button" class="btn_logout" value="로그아웃" onclick="location.href='logout_do'"/>
	<input type="button" class="btn_member_info" value="내 정보" onclick="location.href='memberInfo_do'"/>

</div>
<!-- //백그라운드 영역 -->

</body>
</html>