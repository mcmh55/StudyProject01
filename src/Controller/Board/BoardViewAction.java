package Controller.Board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DTO.BoardCommentDTO;
import DTO.BoardDTO;
import Interface.Action;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {

		int seq = Integer.parseInt(request.getParameter("seq"));
		
		BoardDTO bdto = BoardDAO.INSTANCE.selectOneBoard(seq);
		List<BoardCommentDTO> commentList = BoardDAO.INSTANCE.selectCommentList(seq);
		
		request.setAttribute("boardView", bdto);
		request.setAttribute("commentList", commentList);
		
		String url = "Board/BoardView.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, reponse);
		
		return;
	}
}