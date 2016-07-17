package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import DTO.MemberDTO;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet("/memberFindSend_do")
public class svlMemberFindSend extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "member/Login.jsp";
		String inputEmail = request.getParameter("email");
		
		MemberDAO mdao = MemberDAO.getInstance();
		MemberDTO mdto = mdao.getFindMember(inputEmail);
		
		String getId = mdto.getId();
		String getPw = mdto.getPw();
		
		///// 이메일 처리
		// 메일 관련 정보
		String fromEmail = "mcyahma@gmail.com";
		String fromPw = "q1832465";
		 
		// 메일 내용
		String toEmail = inputEmail;
		String subject = "요청하신 ID/PW입니다.";
		String body = "ID: " + getId + "\n"
					+ "PW: " + getPw;
		 
		//properties 설정
		String host = "smtp.gmail.com";
		Properties props = new Properties();				// HashTable 상속 받은 컬렉션 프레임워크. 메일 서버 접속에 필요한 정보를 저장
		props.put("mail.smtp.starttls.enable", "true");		// gmail은 무조건 true 고정
		props.put("mail.smtp.host", host);					// smtp 서버 주소
		props.put("mail.smtp.user", fromEmail);
		props.put("mail.smtp.password", fromPw);
		props.put("mail.smtp.port", "587");					// 포트 ※465(TLS 방식), 587(SSL0 방식) 서로 방법이 다름
		props.put("mail.smtp.auth", "true");				// gmail은 무조건 true 고정
		
		// 메일 세션
		Session session = Session.getDefaultInstance(props);
		MimeMessage msg = new MimeMessage(session);			// 전달할 값들을 저장
 
        try {
        	// 메일 내용 저장
        	msg.setFrom(new InternetAddress(fromEmail));
			
			InternetAddress toAddress = new InternetAddress(toEmail);
			msg.addRecipient(Message.RecipientType.TO, toAddress);
			msg.setSubject(subject);
			msg.setText(body);
			
		    // 발송 처리
			Transport transport = session.getTransport("smtp");
			transport.connect(host, fromEmail, fromPw);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException e) {
        	e.printStackTrace();
        }
		
		request.setAttribute("msg_alarm", "이메일 전송 완료!");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		return;
	}

}