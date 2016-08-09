package Controller.Board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DTO.BoardDTO;
import Interface.Action;

public class BoardReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {

		int seq = Integer.parseInt(request.getParameter("seq"));
		String loginId = request.getParameter("id");
		int pw = Integer.parseInt(request.getParameter("pw"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String filename = request.getParameter("filename");
		
		BoardDTO bdto = new BoardDTO(seq, loginId, pw, title, content, filename);
		
		boolean result = BoardDAO.INSTANCE.insertReplyBoard(seq, bdto);
		
		String url = "boardControll?command=board_list";
		
		if ( !result ) {
			url = "boardControll?command=board_view&seq=" + seq;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, reponse);
	}
}