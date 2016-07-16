package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import DTO.MemberDTO;

@WebServlet("/memberUpdate_do")
public class MemberUpdateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "member/MemberUpdate.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("loginUser");
		
		String userNick = mdto.getNick();
		String inputNick = request.getParameter("nick");
		int inputAge = Integer.parseInt(request.getParameter("age"));
		
		String url = "member/MemberUpdate.jsp";
		
		if ( !userNick.equals(inputNick) ) {
			
			MemberDAO mdao = MemberDAO.getInstance();
			int result = mdao.checkNick(inputNick);
			
			if ( result == 1 ) {
				request.setAttribute("msg", "이미 존재하는 별명입니다.");
			} else {
				
				mdto = mdao.updateMember(mdto, userNick, inputNick, inputAge);
				session.setAttribute("loginUser", mdto);
				request.setAttribute("msg_update_member", "회원 정보가 수정되었습니다.");
				
				url = "member/MemberInfo.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				
				return;
			}
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		return;
	}

}
