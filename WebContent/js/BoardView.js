// 수정, 삭제 권한 체크
function checkAuthor(loginId, boardId) {
	
	if ( loginId == boardId ) {
		return true;
	} else {
		
		alert("권한이 없습니다.");
		
		return false;
	}
}


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
	
	var cmtId = comment.id;
	var cmtIdLeng = cmtId.length;
	
	cmtNum = cmtId.substring(cmtIdLeng-1, cmtIdLeng);
	
	var commentSeq = $("#comment_seq" + cmtNum).val();
	var commentContent = $("#textarea_comment_update" + cmtNum).val();
	
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
			
			$("#comment_content" + cmtNum).show();
			$("#textarea_comment_update" + cmtNum).hide();
			$("#btn_comment_update" + cmtNum).hide();
		}
	});
}


// 댓글 삭제
function deleteComment(comment) {
	
	var cmtId = comment.id;
	var cmtIdLeng = cmtId.length;
	
	cmtNum = cmtId.substring(cmtIdLeng-1, cmtIdLeng);
	
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