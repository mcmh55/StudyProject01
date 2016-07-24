package Controller.Board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.BoardDTO;
import Interface.Action;

public class BoardUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
		
		String url = "Board/BoardUpdate.jsp";
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		String id = request.getParameter("id");
		int pw = Integer.parseInt(request.getParameter("pw"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String filename = request.getParameter("filename");
		
		BoardDTO bdto = new BoardDTO(seq, id, pw, title, content, filename);
		
		request.setAttribute("boardUpdate", bdto);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, reponse);
		
		return;
	}
}