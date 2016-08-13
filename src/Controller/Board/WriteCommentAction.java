package Controller.Board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DTO.BoardCommentDTO;
import Interface.Action;

public class WriteCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {

		int seq = Integer.parseInt(request.getParameter("seq"));
		String id = request.getParameter("id");
		String comment = request.getParameter("comment");
		
		boolean result = BoardDAO.INSTANCE.insertComment(seq, new BoardCommentDTO(id, comment));
		
		String url = "boardControll?command=board_view";
		
		request.setAttribute("seq", seq);
		request.setAttribute("insertCommentResult", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, reponse);
		
		return;
	}
}