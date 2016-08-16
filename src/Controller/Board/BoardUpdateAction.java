package Controller.Board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DTO.BoardDTO;
import Interface.Action;

public class BoardUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {

		int seq = Integer.parseInt(request.getParameter("seq"));
		int pw = Integer.parseInt(request.getParameter("pw"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String filename = request.getParameter("filename");
		
		boolean result = false;
		result = BoardDAO.INSTANCE.updateBoard(new BoardDTO(seq, pw, title, content, filename));
		
		if ( !result ) {
			request.setAttribute("msg_alarm", "잠시 후 다시 시도해주세요.");
		}
		
		request.setAttribute("seq", seq);
		
		String url = "boardControll?command=board_view";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, reponse);
		
		return;
	}
}