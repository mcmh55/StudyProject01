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
public class svlMemberUpdate extends HttpServlet {
	
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
		MemberDAO mdao = MemberDAO.getInstance();
		
		String userNick = mdto.getNick();
		String userEmail = mdto.getEmail();
		
		String inputPw = request.getParameter("pw");
		String inputNick = request.getParameter("nick");
		int inputAge = Integer.parseInt(request.getParameter("age"));
		String inputEmail = request.getParameter("email");
		
		String url = "member/MemberInfo.jsp";
		
		if ( !userNick.equals(inputNick) ) {
			
			boolean resultNick = mdao.checkNick(inputNick);
			
			if ( resultNick ) {
				
				request.setAttribute("msg", "이미 존재하는 별명입니다.");
				
				url = "member/MemberUpdate.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				
				return;
			}
			
		} else if ( !userEmail.equals(inputEmail) ) {
			
			boolean resultEmail = mdao.checkEmail(inputEmail);
			
			if ( resultEmail ) {
				
				request.setAttribute("msg", "이미 존재하는 이메일입니다.");
				
				url = "member/MemberUpdate.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				
				return;
			}
			
		}
			
		mdto = mdao.updateMember(mdto, inputPw, inputNick, inputAge, inputEmail);
		session.setAttribute("loginUser", mdto);
		request.setAttribute("msg_alarm", "회원 정보가 수정되었습니다.");
			
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		return;
	}

}
