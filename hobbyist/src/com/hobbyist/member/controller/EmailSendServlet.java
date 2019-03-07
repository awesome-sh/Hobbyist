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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.member.model.vo.Member;

/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/emailSend.do")
public class EmailSendServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailSendServlet() {
        super();
        
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	  String memberEmail = request.getParameter("memberEmail");
	      String memberEmailaddress = request.getParameter("memberEmailaddress");
	      String finalEmail = memberEmail+memberEmailaddress; //이메일 수신자
	      String msg = "";
	      
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
	      
	      try{
	    	 String email_content = "hobbyist의 멤버가입을 감사드립니다! <br><br> 아래의 인증번호를 입력해주세요.<br>";
	    	 String email_title = "hobbyist 가입을 위한 인증번호를 입력해주세요.";
	    	 String admin_name= "hobbyist";

	    	  
	    	 mmsg.setSentDate(new Date()); //메일 보낸시간
	    	 
	    	 InternetAddress from = new InternetAddress(admin_name); //이메일 발신자
	    	 mmsg.setFrom(from);
	    	 
	    	 //이메일 수신자
	    	 InternetAddress to = new InternetAddress(finalEmail);
	    	 mmsg.setRecipient(Message.RecipientType.TO, to);
	    	 
	    	 //이메일 제목
	    	 mmsg.setSubject(email_title, "UTF-8");
	    	 
	    	 //이메일 내용
	    	 String numberCode = request.getParameter("code_check"); //인증번호 값 받기
	    	 System.out.println(numberCode);
	    	 mmsg.setText(email_content+"인증번호 : "+"[ "+numberCode+" ]", "UTF-8");
	    	 
	    	 //이메일 헤더
	    	 mmsg.setHeader("Content-type", "text/html;");
	    	 
	    	 //메일 보내기
	    	 javax.mail.Transport.send(mmsg);
	    	 
	         msg="인증메일을 발송했습니다. 확인해주세요.";
	        
	         
	         request.setAttribute("msg", msg);
	         request.setAttribute("numberCode", numberCode);
	         System.out.println("이메일전송 성공");
	        

	      } catch(AddressException addr_e) {
	         msg="인증메일 발송이 실패되었습니다. 다시 진행해주세요.";
	         addr_e.printStackTrace();
	         
	      } catch(MessagingException msg_e) {
	    	  msg="인증메일 발송이 실패되었습니다. 다시 진행해주세요.";
	    	  msg_e.printStackTrace();
	      }
	   
	      request.setAttribute("finalEmail", finalEmail);
	      request.setAttribute("msg", msg);
	      
	      RequestDispatcher rd = request.getRequestDispatcher("/views/member/checkEmail.jsp");
	      rd.forward(request, response);
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