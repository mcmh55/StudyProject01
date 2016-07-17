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

@WebServlet("/memberLeave_do")
public class MemberLeaveServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("loginUser");
		String userId = mdto.getId();
		
		MemberDAO mdao = MemberDAO.getInstance();
		int result = mdao.deleteMember(userId);
		
		if ( result == 1 ) {
			request.setAttribute("msg_member_leave", "회원 탈퇴가 정상적으로 처리되었습니다.");
		} else {
			request.setAttribute("msg_member_leave", "잠시 후 다시 시도해주세요.");
		}
		
		String url = "logout_do";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}