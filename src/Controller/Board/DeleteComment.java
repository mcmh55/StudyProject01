package Controller.Board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import Interface.Action;

public class DeleteComment implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {

		int commentSeq = Integer.parseInt(request.getParameter("commentSeq"));
		
		boolean result = BoardDAO.INSTANCE.deleteComment(commentSeq);
		
		return;
	}
}