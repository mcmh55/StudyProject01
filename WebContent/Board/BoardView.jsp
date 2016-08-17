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
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
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
		
		<!-- 글 내용 영역 -->
		<table class="tbl_view">
		<col style="width: 15%;"/><col style="width: 85%;"/>
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
			<td colspan="2" style="padding: 10px;">
				<pre class="board_content">${ board.content }</pre>
			</td>
		</tr>
		</table>
		<!-- // 글 내용 영역 -->
		
		<!-- 댓글 리스트 영역 -->
		
		<table class="tbl_comment">
		<col style="width: 90%;"/><col style="width: 10%;"/>
		
		<c:set var="commentCount" value="0"/>
		
		<c:forEach var="comment" items="${ commentList }">
			
			<c:choose>
				
				<c:when test="${ comment.depth == 0 && comment.del == 1 }">
					<tr>
						<td colspan="2" class="comment_delete_content">삭제된 댓글입니다.</td>
					</tr>
				</c:when>
				
				<c:when test="${ comment.del == 0 }">
					<input type="hidden" id="comment_seq${ commentCount + 1 }" value="${ comment.seq }"/>
			
					<tr id="comment_row_1_${ commentCount + 1 }">
						
						<!-- 작성자 정보 -->
						<td class="comment_info_${ commentCount + 1 }">
						
							<c:if test="${ comment.depth > 0 }">
								<i class="fa fa-caret-right" aria-hidden="true"></i>
							</c:if>
							
							${ comment.id }&nbsp;&nbsp;
							
							<font class="comment_writeDate">
								<fmt:formatDate value="${ comment.writeDate }" pattern="yyyy.MM.dd HH:mm"/>
							</font>&nbsp;&nbsp;
							
							<img src="images/comment_reply.png"/>
							<a id="comment_reply-${ commentCount + 1 }" class="comment_reply">답글</a>
							
							<!-- 댓글의 답글 취소 -->
							<a id="comment_reply_cancel-${ commentCount + 1 }" class="comment_reply_cancel">답글 취소</a>
							
						</td>
						
						<!-- 댓글 수정/삭제 -->
						<td id="comment_controll_1_${ commentCount + 1 }" class="comment_controll_1">
						
							<a id="comment_update${ commentCount + 1 }" class="comment_update">수정</a>
							
							<font style="font-size: 9pt;">|</font>
							
							<a id="comment_delete${ commentCount + 1 }" class="comment_delete"
							onclick="deleteComment(this)">삭제</a>
							
						</td>
						
						<!-- 댓글 수정 취소 -->
						<td id="comment_controll_2_${ commentCount + 1 }" class="comment_controll_2">
							<a id="comment_update_cancel${ commentCount + 1 }" class="comment_update_cancel">수정 취소</a>
						</td>
					</tr>
					
					<!-- 댓글 내용 or 내용 수정 -->
					<tr id="comment_row_2_${ commentCount + 1 }">
						<td colspan="2" id="comment_update_content_${ commentCount + 1 }" class="comment_update_content">
							
							<textarea id="textarea_comment_content-${ commentCount + 1 }"
							class="textarea_comment" disabled="disabled">${ comment.content }</textarea>
							
							<textarea id="textarea_comment_update${ commentCount + 1 }" 
							class="textarea_comment_update">${ comment.content }</textarea>
							
							<input type="button" id="btn_comment_update${ commentCount + 1 }" 
							class="btn_white_square" value="수정" onclick="updateComment(this)"  
							style="margin-bottom: 5px; margin-left: 5px;"/>
							
						</td>
					</tr>
					
					<!-- 댓글의 답글 작성 -->
					<tr id="comment_row_3_${ commentCount + 1 }">
						<td colspan="2" class="comment_reply_content">
							
							<textarea id="textarea_comment_reply-${ commentCount + 1 }" 
							class="textarea_comment_reply"></textarea>
							
							<input type="button" id="btn_comment_reply${ commentCount + 1 }" 
							class="btn_white_square" value="등록" onclick="replyComment(this)" 
							style="margin-bottom: 5px; margin-left: 5px;"/>
							
						</td>
					</tr>
				</c:when>
			</c:choose>
			
			<c:if test="${ comment.depth > 0 }">
				<script type="text/javascript">
					$(".comment_info_" + '${ commentCount + 1 }').css("padding-left", "40px");
					$("#comment_update_content_" + '${ commentCount + 1 }').css("padding-left", "40px");
					$("#textarea_comment_update" + '${ commentCount + 1 }').css("width", "720px");
				</script>
			</c:if>
			
			<c:set var="commentCount" value="${ commentCount + 1 }"/>
			
		</c:forEach>
		<!-- // 댓글 리스트 영역 -->
		
		<!-- 댓글 작성 영역 -->
		<tr>
			<td colspan="3" style="border-bottom: none;">
				
				<input type="hidden" id="comment_count" value="${ commentCount }"/>
				
				<textarea id="textarea_comment_write" class="textarea_comment_write" name="comment"></textarea>
				
				<input type="button" id="btn_comment_write" class="btn_white_square" value="등록"
				onclick="writeComment()"/>
				
			</td>
		</tr>
		</table>
		<!-- // 댓글 작성 영역 -->
		
		<!-- 버튼 영역 -->
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
		<!-- // 버튼 영역 -->
	
	</form>
	
</div>


<!-- 				[ Script ]					 -->
<!-- ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ -->

<script type="text/javascript">

/*		댓글		*/

var textareaHeightTemp = 0;

$(function(){
	
	var commentCount = '${ commentCount }';
	resizeTextareaHeight(commentCount);
	
	$.ajaxSetup({chche:false});
	
	// 모든 댓글의 답글, 수정 취소, 수정 모드 숨김
	for ( var i = 1; i <= commentCount; i++ ) {
		
		$("#comment_row_3_" + i).hide();
		$("#comment_reply_cancel-" + i).hide();
		$("#comment_controll_2_" + i).hide();
		$("#textarea_comment_update" + i).hide();
		$("#btn_comment_update" + i).hide();
	}
	
	
	// 수정 클릭한 댓글만 수정 모드로 변환
	$(".comment_update").click(function() {
		
		var cmtId = $(this).attr("id");
		var cmtNum = cmtId.replace("comment_update", "");
		var switchMode = "on";
		
		var commentContent = $("#textarea_comment_content-" + cmtNum).val();
		$("#textarea_comment_update" + cmtNum).val(commentContent);
		
		updateMode(switchMode, cmtNum);
		
		switchMode = "off";
		replyMode(switchMode, cmtNum);
		changeOtherComment(switchMode, cmtNum, commentCount);
		
		textareaHeightTemp = $("#textarea_comment_update" + cmtNum).prop("scrollHeight");
		resizeTextareaHeight(commentCount);
	});
	
	
	// 수정 모드 취소
	$(".comment_update_cancel").click(function() {
		
		var cmtId = $(this).attr("id");
		var cmtNum = cmtId.replace("comment_update_cancel", "");
		var switchMode = "off";
		
		resetTextAreaHeight(cmtNum, "update");
		updateMode(switchMode, cmtNum);
		replyMode(switchMode, cmtNum);
		changeOtherComment(switchMode, cmtNum, commentCount);
		resizeTextareaHeight(commentCount, cmtNum);
	});
	
	
	// 답글 클릭한 댓글만 답글 모드로 변환
	$(".comment_reply").click(function() {
		
		var cmtId = $(this).attr("id");
		var cmtNum = cmtId.replace("comment_reply-", "");
		var switchMode = "on";
		
		replyMode(switchMode, cmtNum);
		resetTextAreaHeight(cmtNum, "reply");
		
		switchMode = "off";
		updateMode(switchMode, cmtNum);
		changeOtherComment(switchMode, cmtNum, commentCount);
	});
	
	
	// 답글 모드 취소
	$(".comment_reply_cancel").click(function() {
		
		var cmtId = $(this).attr("id");
		var cmtNum = cmtId.replace("comment_reply_cancel-", "");
		var switchMode = "off";
		
		resetTextAreaHeight(cmtNum, "reply");
		replyMode(switchMode, cmtNum);
		changeOtherComment(switchMode, cmtNum, commentCount);
	});
	
	
	// TextArea 스크롤 변화 시  높이 변경
	$(".textarea_comment_update").add(".textarea_comment_reply").keyup(function(){
		
		var cmtId = $(this).attr("id");
		var scrollHeight = $(this).prop("scrollHeight");
		
		$("#" + cmtId).css("height", "0px");
		$("#" + cmtId).css("height", (scrollHeight - 30) + "px");
	});
	
});


// 댓글 작성
function writeComment() {
	
	var loginId = '${ loginUser.id }';
	var boardSeq = '${ board.seq }';
	var commentContent = $("#textarea_comment_write").val();
	var refreshUrl = document.location.href;
	
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
			$("body").load(refreshUrl + "&temp=" + Math.random());
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
	
	$.ajax({
		
		url : "boardControll",
		type: "post",
		data : { 
			"command" : "board_comment_update",
			"commentSeq" : commentSeq,
			"commentContent" : commentContent
		},
		success : function() {
			
			$("#textarea_comment_content-" + cmtNum).val(commentContent);
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
	var refreshUrl = document.location.href;
	
	$.ajax({
		
		url : "boardControll",
		type: "post",
		data : { 
			"command" : "board_comment_delete",
			"commentSeq" : commentSeq,
		},
		success : function() {
			$("body").load(refreshUrl + "&temp=" + Math.random());
		}
	});
}


// 댓글의 답글 작성
function replyComment(comment) {
	
	var loginId = '${ loginUser.id }';
	var boardSeq = '${ board.seq }';
	var cmtId = comment.id;
	var cmtNum = cmtId.replace("btn_comment_reply", "");
	var switchMode = "off";
	
	var commentSeq = $("#comment_seq" + cmtNum).val();
	var commentContent = $("#textarea_comment_reply-" + cmtNum).val();
	
	var refreshUrl = document.location.href;
	
	$.ajax({
		
		url : "boardControll",
		type: "post",
		data : { 
			"command" : "board_comment_reply",
			"loginId" : loginId,
			"boardSeq" : boardSeq,
			"commentSeq" : commentSeq,
			"commentContent" : commentContent
		},
		success : function() {
			$("body").load(refreshUrl + "&temp=" + Math.random());
		},
		error : function() {
			alert("실패");
		}
	});
}


// 댓글의 수정 모드 상태일 때의 'textarea' 높이를 입력 된 값에 맞춰 변경
function resizeTextareaHeight() {
	
	// 오버로딩 사용
	var commentCount = arguments[0];
	var cmtNum = arguments[1];
	
	switch (arguments.length) {
	case 1 :
		for ( var i = 1; i <= commentCount; i++ ) {
			
			var commentHeight = $("#textarea_comment_content-" + i).prop("scrollHeight");
			commentHeight = commentHeight + "px";
			$("#textarea_comment_content-" + i).css("height", commentHeight);
			
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
		
		var commentHeight = $("#textarea_comment_content-" + cmtNum).prop("scrollHeight");
		commentHeight = commentHeight + "px";
		$("#textarea_comment_content-" + cmtNum).css("height", commentHeight);
		
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


// 'TextArea' 높이 초기화
function resetTextAreaHeight(cmtNum, type) {
	
	if ( type == "reply" ) {
		
		var textareaMinHeight = $(".textarea_comment_reply").css("min-height");
		$("#textarea_comment_reply-" + cmtNum).css("height", textareaMinHeight);
		
	} else if ( type == "update" ) {
		$("#textarea_comment_update" + cmtNum).css("height", (textareaHeightTemp - 20) + "px");
	}
}


// 댓글 수정 모드 변환
function updateMode(switchMode, cmtNum) {
	switch (switchMode) {
	case "on" :
		$("#comment_controll_1_" + cmtNum).hide();
		$("#textarea_comment_content-" + cmtNum).hide();
		
		$("#comment_controll_2_" + cmtNum).show();
		$("#textarea_comment_update" + cmtNum).show();
		$("#btn_comment_update" + cmtNum).show();
		break;
		
	case "off" :
		$("#comment_controll_1_" + cmtNum).show();
		$("#textarea_comment_content-" + cmtNum).show();
		
		$("#comment_controll_2_" + cmtNum).hide();
		$("#textarea_comment_update" + cmtNum).hide();
		$("#btn_comment_update" + cmtNum).hide();
		break;
		
	default :
		alert("수정 오류");
		break;
	}
}


// 댓글 답글 모드 변환
function replyMode(switchMode, cmtNum) {
	switch (switchMode) {
	case "on" :
		$("#comment_reply_cancel-" + cmtNum).show();
		$("#comment_row_3_" + cmtNum).show();
		
		$("#comment_reply-" + cmtNum).hide();
		break;
		
	case "off" :
		$("#comment_reply-" + cmtNum).show();
		
		$("#comment_reply_cancel-" + cmtNum).hide();
		$("#comment_row_3_" + cmtNum).hide();
		$("#textarea_comment_reply-" + cmtNum).val("");
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
			replyMode(switchMode, i);
		}
	}
}

/*		 // 댓글 		*/ 

</script>

</body>
</html>