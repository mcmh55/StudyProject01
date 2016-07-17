package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;

@WebServlet("/memberFind_do")
public class svlMemberFind extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "member/MemberFind.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "member/Login.jsp";
		String inputEmail = request.getParameter("email");
		
		MemberDAO mdao = MemberDAO.getInstance();
		boolean result = false;
		
		result = mdao.checkEmail(inputEmail);
		
		if ( !result ) {
			
			request.setAttribute("msg", "해당 이메일이 등록된 계정이 존재하지 않습니다.");
			url = "member/MemberFind.jsp";
			
		} else {
			url = "memberFindSend_do";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		return;
	}

}