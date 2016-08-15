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

@WebServlet("/login_do")
public class svlLogin extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "member/Login.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		return;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "member/Login.jsp";
		
		// 'login.jsp'에서 입력받은 아이디, 암호를 받아옴
		String inputID = request.getParameter("id");
		String inputPW = request.getParameter("pw");
		
		MemberDAO mdao = MemberDAO.getInstance();
		int result = mdao.checkMember(inputID, inputPW);
		
		switch ( result ) {
		
		case -1:
			request.setAttribute("msg", "비밀번호가 맞지 않습니다.");
			break;
		
		case 0:
			request.setAttribute("msg", "존재하지 않는 회원입니다.");
			break;
			
		case 1:
			
			MemberDTO memdto = mdao.getMember(inputID);
			HttpSession session = request.getSession();
			
			session.setAttribute("loginUser", memdto);
			session.setMaxInactiveInterval(60 * 60 * 24);
			
			request.setAttribute("msg", "로그인 성공!");
			url = "boardControll?command=board_list";
			
			break;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		return;
	}
}