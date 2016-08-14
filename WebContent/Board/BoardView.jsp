<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	
		<input type="hidden" id="command" name="command" value="board_update_form"/>
		<input type="hidden" name="seq" value="${ board.seq }"/>
		<input type="hidden" name="id" value="${ board.id }"/>
		<input type="hidden" name="pw" value="${ board.pw }"/>
		<input type="hidden" name="title" value="${ board.title }"/>
		<input type="hidden" name="content" value="${ board.content }"/>
		<input type="hidden" name="filename" value="${ board.fileName }"/>
		
		<table class="tbl_view">
		<col width="100px"/><col width="500px"/>
		<tr>
			<th>작성자</th>
			<td>${ board.id }</td>
		</tr>
		<tr>
			<th>첨부 파일</th>
			<td>
			<c:if test="${ board.fileName != '' }">
				<a href="">${ board.fileName }</a>
			</c:if>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${ board.title }</td>
		</tr>
		<tr>
			<td colspan="2" style="padding: 10px;">${ board.content }</td>
		</tr>
		</table>
		
		<!-- 댓글 -->
		
		<table class="tbl_comment">
		<col width="90%"/><col width="5px"/><col width="5px"/>
		
		<c:set var="comment_count" value="0"/>
		<% pageContext.setAttribute("newLine", "\n"); %>	<!-- c:set으로 처리하면 에러 -->
		
		<c:forEach var="comment" items="${ commentList }">
			<input type="hidden" id="comment_seq${ comment_count + 1 }" value="${ comment.seq }"/>
			<tr id="comment_row_1_${ comment_count + 1 }">
				<td class="comment_info">
				
					${ comment.id }&nbsp;&nbsp;
					
					<font class="comment_writeDate">
						<fmt:formatDate value="${ comment.writeDate }" pattern="yyyy.MM.dd HH:mm"/>
					</font>&nbsp;&nbsp;
					
					답글
					
				</td>
				<td class="comment_controll">
				
					<a id="comment_update${ comment_count + 1 }" class="comment_update">수정</a>
					<font style="font-size: 9pt;">|</font>
					<a id="comment_delete${ comment_count + 1 }" class="comment_delete"
					onclick="deleteComment(this)">삭제</a>
					
				</td>
			</tr>
			<tr id="comment_row_2_${ comment_count + 1 }">
				<td colspan="3" class="comment_content">
				
					<pre id="comment_content${ comment_count + 1 }" style="line-height: 20px;">${ comment.content }</pre>
					
					<textarea id="textarea_comment_update${ comment_count + 1 }" 
					class="textarea_comment_update" name="comment">${ fn:replace(comment.content, "<br/>", newLine) }</textarea>
					
					<input type="button" id="btn_comment_update${ comment_count + 1 }" 
					class="btn_white_square" value="수정" onclick="updateComment(this)"  
					style="margin-bottom: 5px; margin-left: 5px;"/>
					
				</td>
				<c:set var="comment_count" value="${ comment_count + 1 }"/>
			</tr>
		</c:forEach>
		
		<tr>
			<td colspan="3" style="border-bottom: none;">
				
				<input type="hidden" id="comment_count" value="${ comment_count }"/>
				
				<textarea id="textarea_comment_write" class="textarea_comment_write" name="comment"></textarea>
				
				<input type="button" id="btn_comment_write" class="btn_white_square" value="등록"
				onclick="writeComment('${ loginUser.id }', '${ board.seq }', document.location.href)"/>
				
			</td>
		</tr>
		</table>
	
		<div class="foot">
			<div class="area_bottom_btn">
			
				<input type="button" id="btn_reply" class="btn_blue" value="답글" 
				onclick="location.href='boardControll?command=board_reply_form&seq=${ board.seq }'"/>
				
				<input type="submit" id="btn_update_form" class="btn_blue" value="수정" 
				onclick="return checkAuthor('${ loginUser.id }', '${ board.id }')"/>
				
				<input type="button" id="btn_delete" class="btn_blue" value="삭제" 
				onclick="location.href='boardControll?command=board_delete&seq=${ board.seq }'"/>
				
				<input type="button" id="btn_list" class="btn_green" value="목록" 
				onclick="location.href='boardControll?command=board_list'"/>
				
			</div>
		</div>
	
	</form>
	
</div>

<script type="text/javascript">
/* 댓글 */
$(function(){
	
	var comment_count = ${ comment_count };
	
	// 모든 댓글 수정의 'textarea'의 높이를 입력 된 값에 맞춰 변경
	for ( var i = 1; i <= comment_count; i++ ) {
		
		var textareaMinHeight = $("#textarea_comment_update" + i).css("min-height");
		var textareaMaxHeight = $(".textarea_comment_update").css("max-height");
		var textareaHeight = $("#textarea_comment_update" + i).prop("scrollHeight");
		
		textareaMinHeight = textareaMinHeight.substring(0, textareaMinHeight.length-2);
		textareaMaxHeight = textareaMaxHeight.substring(0, textareaMaxHeight.length-2);
		
		textareaMinHeight = textareaMinHeight + 0;
		textareaHeight = textareaHeight - 20;
		
		if ( textareaHeight > textareaMaxHeight ) {
			textareaHeight = textareaMaxHeight;
		}
		
		textareaHeight = textareaHeight + "px";
		$("#textarea_comment_update" + i).css("height", textareaHeight);
	}
	
	// 모든 댓글 수정의 'textarea, 버튼' 숨김
	for ( var i = 1; i <= comment_count; i++ ) {
		
		$("#textarea_comment_update" + i).hide();
		$("#btn_comment_update" + i).hide();
	}
	
	
	// 수정 클릭한 댓글만 수정 모드로 변환
	$(".comment_update").click(function() {
		
		var cmtId = $(this).attr("id");
		var cmtIdLeng = cmtId.length;
		
		cmtNum = cmtId.substr(cmtIdLeng-1, cmtIdLeng);
		
		$("#comment_content" + cmtNum).hide();
		
		$("#textarea_comment_update" + cmtNum).show();
		$("#btn_comment_update" + cmtNum).show();
		
		for ( var i = 1; i <= comment_count; i++ ) {
			if ( i != cmtNum ) {
				
				$("#comment_content" + i).show();
				
				$("#textarea_comment_update" + i).hide();
				$("#btn_comment_update" + i).hide();
			}
		}
	});
});
/* // 댓글 */ 
</script>

</body>
</html>