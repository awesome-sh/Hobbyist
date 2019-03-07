package com.hobbyist.member.controller;

/**
*
*/

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;

/**
* @author viper9
*
*/
public class SMTPAuthenticator extends Authenticator {
   public SMTPAuthenticator() {
       super();
   }

   public PasswordAuthentication getPasswordAuthentication() {
       String username = "아이디";
       String password = "비밀번호";
       return new PasswordAuthentication(username, password);
   }
}



