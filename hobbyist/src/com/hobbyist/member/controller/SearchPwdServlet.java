package com.hobbyist.member.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.hobbyist.member.model.service.MemberService;
import com.hobbyist.member.model.vo.Member;


@WebServlet("/searchPwd.do")
public class SearchPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchPwdServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberEmail = request.getParameter("memberEmail");
		String memberEmailaddress =request.getParameter("memberEmailaddress");
		String pwdCode = request.getParameter("pwdCode"); //임시 비밀번호 고유값값 받기
		String finalEmail = memberEmail+memberEmailaddress;
		
		Member m = new Member();
		m.setMemberEmail(finalEmail);
		System.out.println("비밀번호 찾기 아이디 : " + finalEmail);
		System.out.println("발급된 임시 비밀번호 : " + pwdCode);
		Member result = new MemberService().selectOne(m);
		
		if(result!=null) {
			Properties props = System.getProperties();
		    props.put("mail.smtp.user", "hobbyist"); //서버 아이디만 쓰기
		    props.put("mail.smtp.host", "smtp.gmail.com"); //구글 smtp
		    props.put("mail.smtp.port", "465");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.socketFactory.port", "465");
		    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		    props.put("mail.smtp.socketFactory.fallback", "false");
		    props.put("mail.transport.protocol", "smtp");
		    props.put("mail.smtp.auth", "true");
		    
		    Authenticator auth = new MyAuthentication();
		    Session session = Session.getDefaultInstance(props, auth);
		    MimeMessage mmsg = new MimeMessage(session);
		     
		    try {
		    	String email_content = "요청하신 인증번호를 발송해 드립니다.";
		    	String email_title = "<비밀번호 찾기 찾기> 요청하신 인증번호를 알려드립니다.";
		    
		    	
		    	mmsg.setSentDate(new Date());
		    	
		    	InternetAddress from = new InternetAddress();
		    	from.setPersonal("hobbyist");//이메일 발신자
		    	mmsg.setFrom(from);
		    	
		    	InternetAddress to = new InternetAddress();
		    	to.setAddress(finalEmail);
		    	mmsg.setRecipient(Message.RecipientType.TO, to);
		    	
		    	mmsg.setSubject(email_title, "UTF-8");
		    	
		    	System.out.println(pwdCode);
		    	mmsg.setText(email_content+"인증번호 : "+"[ "+pwdCode+" ]", "UTF-8");
		    	
		    	javax.mail.Transport.send(mmsg);
		    	
		    	m.setMemberPassword(pwdCode);
		    	int resultPwd = new MemberService().updateTempPwd(m);
		    	
		    	System.out.println("비밀번호 인증번호 요청 성공");
		    	System.out.println(finalEmail);
		    	System.out.println("임시비밀번호 : " + pwdCode);
		    	
		    	String msg = "Email로 임시비밀번호가 전송되었습니다. 확인해주세요";
		    	
		    	JSONObject json = new JSONObject();
		    	json.put("msg", msg);
		    	json.put("pwdCode", pwdCode);
		    	
				response.setContentType("application/json; charset=UTF-8"); 
				response.getWriter().print(json);
		    	
		    } catch(AddressException addr_e) {
		        addr_e.printStackTrace();
		         
		    } catch(MessagingException msg_e) {
		    	 msg_e.printStackTrace();
		    }
		} else {
			String msg = "일치하는 이메일이 없습니다!";
			response.setContentType("text/html; charset=UTF-8"); 
			response.getWriter().write(msg);
		}
	}
	
	class MyAuthentication extends Authenticator {
	      PasswordAuthentication pa;
	      
	      public MyAuthentication() { //혜진이꺼 구글 아이디와 비밀번호 ★해킹금지!★
	         String id="gpwls7342";//구글 ID
	         String pw="gpwlsl12!!";//구글 비밀번호
	         
	         //ID와 비밀번호를 입력한다.
	        pa=new PasswordAuthentication(id,pw);
	      }
	      
	      public PasswordAuthentication getPasswordAuthentication() {
	         return pa;
	      }
	   }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
