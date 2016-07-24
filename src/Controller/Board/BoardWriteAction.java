package Controller.Board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DTO.BoardDTO;
import Interface.Action;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String filename = request.getParameter("filename");
		
		BoardDAO bdao = BoardDAO.getGetInstance();
		
		boolean runDB = bdao.insertBoard(new BoardDTO(id, pw, title, content, filename));
		
		if ( runDB ) {
			new BoardListAction().execute(request, reponse);
			
			return;
		}
		
		return;
	}
}