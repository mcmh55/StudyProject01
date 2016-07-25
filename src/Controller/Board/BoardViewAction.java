package Controller.Board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DTO.BoardDTO;
import Interface.Action;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {

		String url = "Board/BoardView.jsp";
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		BoardDAO bdao = BoardDAO.getGetInstance();
		BoardDTO bdto = bdao.selectOneBoard(seq);
		
		request.setAttribute("boardView", bdto);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, reponse);
		
		return;
	}
}