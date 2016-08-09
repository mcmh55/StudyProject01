package Controller.Board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DTO.BoardDTO;
import Interface.Action;

public class BoardListAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
		
		String url = "Board/BoardList.jsp";
		
		List<BoardDTO> boardList = BoardDAO.INSTANCE.selectAllBoardList();
		
		request.setAttribute("boardList", boardList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, reponse);
		
		return;
	}
}
