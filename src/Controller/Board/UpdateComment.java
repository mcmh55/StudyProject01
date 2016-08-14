package Controller.Board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import Interface.Action;

public class UpdateComment implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {

		int commentSeq = Integer.parseInt(request.getParameter("commentSeq"));
		String commentContent = request.getParameter("commentContent");
		
		boolean result = BoardDAO.INSTANCE.updateComment(commentSeq, commentContent);
		
		return;
	}
}