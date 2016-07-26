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

public class BoardSearchAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
		
		String url = "Board/BoardList.jsp";
		
		int searchType = Integer.parseInt(request.getParameter("search_type"));
		String searchText = request.getParameter("search_text");
		
		BoardDAO bdao = BoardDAO.getGetInstance();
		List<BoardDTO> boardList = bdao.selectSearchBoardList(searchType, searchText);
		
		request.setAttribute("boardList", boardList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, reponse);
		
		return;
	}
}
