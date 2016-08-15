package Controller.Board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DTO.BoardCommentDTO;
import Interface.Action;

public class ReplyComment implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {

		int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));
		String loginId = request.getParameter("loginId");
		int commentSeq = Integer.parseInt(request.getParameter("commentSeq"));
		String commentContent = request.getParameter("commentContent");
		
		boolean result = BoardDAO.INSTANCE.replyComment(new BoardCommentDTO(commentSeq, boardSeq, loginId, commentContent));
		
		return;
	}
}