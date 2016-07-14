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

@WebServlet("/join_do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 로그인 페이지에서 '회원가입'버튼으로 진입할 경우
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/Join.jsp");
		dispatcher.forward(request, response);
		
	}

	// 회원가입 페이지에서 '가입'버튼으로 진입할 경우
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String url = "member/Join.jsp";
		String inputID = request.getParameter("input_join_id");
		
		MemberDAO mdao = MemberDAO.getInstance();
		int result = mdao.checkId(inputID);		// 아이디 중복 체크
		
		switch ( result ) {
		
		case 1:
			request.setAttribute("message", "이미 존재하는 회원입니다.");
			break;
			
		case 0:
			
			String id = request.getParameter("input_join_id");
			String pw = request.getParameter("input_join_pw");
			String nick = request.getParameter("input_join_nick");
			String age = request.getParameter("input_join_age");
			
			MemberDTO mdto = new MemberDTO();
			mdto.setId(id);
			mdto.setPw(pw);
			mdto.setNick(nick);
			mdto.setAge(Integer.parseInt(age));
			
			result = mdao.addMember(mdto);
			
			HttpSession session = request.getSession();
			
			if ( result == 1 ) {
				session.setAttribute("userid", mdto.getId());				
			}
			
			url = "member/Login.jsp";
			break;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}