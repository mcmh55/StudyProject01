package Controller.Board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import Interface.Action;

public class BoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {

		String url = "boardControll?command=board_list";
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		BoardDAO bdao = BoardDAO.getGetInstance();
		boolean runDB = bdao.deleteBoard(seq);
		
		if ( !runDB ) {
			url = "boardControll?command=board_view&seq=" + seq;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, reponse);
		
		return;
	}
}