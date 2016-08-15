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
		
		<c:set var="commentCount" value="0"/>
		<% pageContext.setAttribute("newLine", "\n"); %>	<!-- c:set으로 처리하면 에러 -->
		
		<c:forEach var="comment" items="${ commentList }">
			<input type="hidden" id="comment_seq${ commentCount + 1 }" value="${ comment.seq }"/>
			<tr id="comment_row_1_${ commentCount + 1 }">
				<td class="comment_info">
				
					${ comment.id }&nbsp;&nbsp;
					
					<font class="comment_writeDate">
						<fmt:formatDate value="${ comment.writeDate }" pattern="yyyy.MM.dd HH:mm"/>
					</font>&nbsp;&nbsp;
					
					답글
					
				</td>
				<td id="comment_controll_1_${ commentCount + 1 }" class="comment_controll_1">
				
					<a id="comment_update${ commentCount + 1 }" class="comment_update">수정</a>
					<font style="font-size: 9pt;">|</font>
					<a id="comment_delete${ commentCount + 1 }" class="comment_delete"
					onclick="deleteComment(this)">삭제</a>
					
				</td>
				<td id="comment_controll_2_${ commentCount + 1 }" class="comment_controll_2">
					<a id="comment_update_cancel${ commentCount + 1 }" class="comment_update_cancel">수정 취소</a>
				</td>
			</tr>
			<tr id="comment_row_2_${ commentCount + 1 }">
				<td colspan="3" class="comment_content">
				
					<pre id="comment_content${ commentCount + 1 }" style="line-height: 20px;">${ comment.content }</pre>
					
					<textarea id="textarea_comment_update${ commentCount + 1 }" 
					class="textarea_comment_update" name="comment">${ fn:replace(comment.content, "<br/>", newLine) }</textarea>
					
					<input type="button" id="btn_comment_update${ commentCount + 1 }" 
					class="btn_white_square" value="수정" onclick="updateComment(this)"  
					style="margin-bottom: 5px; margin-left: 5px;"/>
					
				</td>
				<c:set var="commentCount" value="${ commentCount + 1 }"/>
			</tr>
		</c:forEach>
		
		<tr>
			<td colspan="3" style="border-bottom: none;">
				
				<input type="hidden" id="comment_count" value="${ commentCount }"/>
				
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


<!-- 				[ Script ]					 -->
<!-- ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ -->

<script type="text/javascript">

/*		댓글		*/

var textareaHeightTemp = 0;

$(function(){
	
	var commentCount = ${ commentCount };
	resizeTextareaHeight(commentCount);
	
	// 모든 댓글의 수정 취소, 수정 모드 숨김
	for ( var i = 1; i <= commentCount; i++ ) {
		
		$("#comment_controll_2_" + i).hide();
		$("#textarea_comment_update" + i).hide();
		$("#btn_comment_update" + i).hide();
	}
	
	
	// 수정 클릭한 댓글만 수정 모드로 변환
	$(".comment_update").click(function() {
		
		var cmtId = $(this).attr("id");
		var cmtNum = cmtId.replace("comment_update", "");
		var switchMode = "on";
		
		var commentContent = $("#comment_content" + cmtNum).html();
		commentContent = commentContent.replace(/<br>/g,"\n");
		$("#textarea_comment_update" + cmtNum).val(commentContent);
		
		updateMode(switchMode, cmtNum);
		
		switchMode = "off";
		changeOtherComment(switchMode, cmtNum, commentCount);
		
		textareaHeightTemp = $("#textarea_comment_update" + cmtNum).prop("scrollHeight");
	});
	
	
	// 수정 모드 취소
	$(".comment_update_cancel").click(function() {
		
		var cmtId = $(this).attr("id");
		var cmtNum = cmtId.replace("comment_update_cancel", "");
		var switchMode = "off";
		
		// 추가 수정 없이 취소만 반복할 경우 높이 변화 초기화
		$("#textarea_comment_update" + cmtNum).css("height", (textareaHeightTemp - 20) + "px");
		
		updateMode(switchMode, cmtNum);
		changeOtherComment(switchMode, cmtNum, commentCount);
	});
	
	// TextArea 스크롤 변화 시  높이 변경
	$(".textarea_comment_update").keyup(function(){
		
		var cmtId = $(this).attr("id");
		var scrollHeight = $(this).prop("scrollHeight");
		
		$("#" + cmtId).css("height", "0px");
		$("#" + cmtId).css("height", (scrollHeight - 30) + "px");
	});
});


// 댓글 작성
function writeComment(loginId, boardSeq, refreshUrl) {
	
	var commentContent = $("#textarea_comment_write").val();
	
	$.ajax({
		
		url : "boardControll",
		type: "post",
		data : { 
			"command" : "board_comment_write",
			"loginId" : loginId,
			"boardSeq" : boardSeq,
			"commentContent" : commentContent
		},
		success : function() {
			$("body").load(refreshUrl);
		}
	});
}


// 댓글 수정
function updateComment(comment) {
	
	var commentCount = ${ commentCount };
	var cmtId = comment.id;
	var cmtNum = cmtId.replace("btn_comment_update", "");
	var switchMode = "off";
	
	var commentSeq = $("#comment_seq" + cmtNum).val();
	var commentContent = $("#textarea_comment_update" + cmtNum).val();
	
	textareaHeightTemp = $("#textarea_comment_update" + cmtNum).prop("scrollHeight");
	alert("textareaHeightTemp: " + textareaHeightTemp);
	
	$.ajax({
		
		url : "boardControll",
		type: "post",
		data : { 
			"command" : "board_comment_update",
			"commentSeq" : commentSeq,
			"commentContent" : commentContent
		},
		success : function() {
			
			$("#comment_content" + cmtNum).text(commentContent);
			$("#textarea_comment_update" + cmtNum).val(commentContent);
			
			resizeTextareaHeight(commentCount, cmtNum);
			updateMode(switchMode, cmtNum);
			
			// 추가 수정 없이 취소만 반복할 경우 높이 변화 초기화
			$("#textarea_comment_update" + cmtNum).css("height", (textareaHeightTemp - 20) + "px");
		}
	});
}


// 댓글 삭제
function deleteComment(comment) {
	
	var cmtId = comment.id;
	var cmtNum = cmtId.replace("comment_delete", "");
	
	var commentSeq = $("#comment_seq" + cmtNum).val();
	
	$.ajax({
		
		url : "boardControll",
		type: "post",
		data : { 
			"command" : "board_comment_delete",
			"commentSeq" : commentSeq,
		},
		success : function() {
			$("#comment_row_1_" + cmtNum).hide();
			$("#comment_row_2_" + cmtNum).hide();
		}
	});
}


// 댓글의 수정 모드 상태일 때의 'textarea' 높이를 입력 된 값에 맞춰 변경
function resizeTextareaHeight() {
	
	var commentCount = arguments[0];
	var cmtNum = arguments[1];
	
	switch (arguments.length) {
	case 1 :
		for ( var i = 1; i <= commentCount; i++ ) {
			
			var textareaMinHeight = $(".textarea_comment_update").css("min-height");
			var textareaMaxHeight = $(".textarea_comment_update").css("max-height");
			var textareaHeight = $("#textarea_comment_update" + i).prop("scrollHeight");
			
			textareaMinHeight = textareaMinHeight.replace("px", "");
			textareaMaxHeight = textareaMaxHeight.replace("px", "");
			
			textareaHeight = textareaHeight - 20;
			
			if ( textareaHeight > textareaMaxHeight ) {
				textareaHeight = textareaMaxHeight;
			}
			
			textareaHeight = textareaHeight + "px";
			$("#textarea_comment_update" + i).css("height", textareaHeight);
		}
		break;
		
	case 2 :
		var textareaMinHeight = $(".textarea_comment_update").css("min-height");
		var textareaMaxHeight = $(".textarea_comment_update").css("max-height");
		var textareaHeight = $("#textarea_comment_update" + cmtNum).prop("scrollHeight");
		
		textareaMinHeight = textareaMinHeight.replace("px", "");
		textareaMaxHeight = textareaMaxHeight.replace("px", "");
		
		if ( textareaHeight > textareaMaxHeight ) {
			textareaHeight = textareaMaxHeight;
		}
		
		$("#textarea_comment_update" + cmtNum).css("height", textareaHeight + "px");
		break;
	}
}


// 댓글 수정 모드 변환
function updateMode(switchMode, cmtNum) {
	switch (switchMode) {
	case "on" :
		$("#comment_controll_1_" + cmtNum).hide();
		$("#comment_content" + cmtNum).hide();
		
		$("#comment_controll_2_" + cmtNum).show();
		$("#textarea_comment_update" + cmtNum).show();
		$("#btn_comment_update" + cmtNum).show();
		break;
		
	case "off" :
		$("#comment_controll_1_" + cmtNum).show();
		$("#comment_content" + cmtNum).show();
		
		$("#comment_controll_2_" + cmtNum).hide();
		$("#textarea_comment_update" + cmtNum).hide();
		$("#btn_comment_update" + cmtNum).hide();
		break;
		
	default :
		alert("수정 오류");
		break;
	}
}


// 선택한 댓글을 제외한 나머지 댓글들을 기본 상태로 변경
function changeOtherComment(switchMode, cmtNum, commentCount) {
	
	for ( var i = 1; i <= commentCount; i++ ) {
		if ( i != cmtNum ) {
			updateMode(switchMode, i);
		}
	}
}

/*		 // 댓글 		*/ 

</script>

</body>
</html>