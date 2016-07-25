package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import DTO.MemberDTO;

@WebServlet("/join_do")
public class svlJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 로그인 페이지에서 '회원가입'버튼으로 진입할 경우
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/Join.jsp");
		dispatcher.forward(request, response);
		
		return;
	}

	// 회원가입 페이지에서 '가입'버튼으로 진입할 경우
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String url = "member/Join.jsp";
		String inputId = request.getParameter("id");
		String inputNick = request.getParameter("nick");
		String inputEmail =  request.getParameter("email");
		
		MemberDAO mdao = MemberDAO.getInstance();
		boolean resultId = mdao.checkId(inputId);			// 아이디 중복 체크
		boolean resultNick = mdao.checkNick(inputNick);		// 별명 중복 체크
		boolean resultEmail = mdao.checkEmail(inputEmail);	// 이메일 중복 체크
		
		if ( resultId ) {
			request.setAttribute("msg", "이미 존재하는 회원입니다.");
		} else if ( resultNick ) {
			request.setAttribute("msg", "이미 존재하는 별명입니다.");
		} else if ( resultEmail ) {
			request.setAttribute("msg", "이미 존재하는 이메일입니다.");
		} else {
			
			String pw = request.getParameter("pw");
			String age = request.getParameter("age");
			
			MemberDTO mdto = new MemberDTO();
			mdto.setId(inputId);
			mdto.setPw(pw);
			mdto.setNick(inputNick);
			mdto.setAge(Integer.parseInt(age));
			mdto.setEmail(inputEmail);
			
			boolean result = mdao.addMember(mdto);
			
			if ( result ) {
				
				request.setAttribute("msg_alarm", "회원으로 가입되었습니다.");
				url = "index.jsp";
				
			}
		}
			
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		return;
	}
}