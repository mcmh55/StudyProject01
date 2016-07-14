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
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "member/Login.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "member/Login.jsp";
		
		// 'login.jsp'에서 입력받은 아이디, 암호를 받아옴
		String inputID = request.getParameter("input_login_id");
		String inputPW = request.getParameter("input_login_pw");
		
		MemberDAO mdao = MemberDAO.getInstance();
		int result = mdao.checkMember(inputID, inputPW);
		
		switch ( result ) {
		
		case -1:
			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
			break;
		
		case 0:
			request.setAttribute("message", "존재하지 않는 회원입니다.");
			break;
			
		case 1:
			
			MemberDTO memdto = mdao.getMember(inputID);
			HttpSession session = request.getSession();
			
			// 다른 사이트로 이동하더라도 로그인 상태를 유지하기 위해 'session'에 회원 정보를 저장
			session.setAttribute("loginUser", memdto);
			request.setAttribute("message", "로그인 성공!");
			url = "bbsList_do";
			
			break;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}