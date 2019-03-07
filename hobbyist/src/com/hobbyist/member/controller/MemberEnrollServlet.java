package com.hobbyist.member.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.hobbyist.member.model.service.MemberService;
import com.hobbyist.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.rename.MyFileRenamePolicy;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet(name="MemberEnrollServlet", urlPatterns="/member/memberEnroll")
public class MemberEnrollServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String msg = "";
      String loc = "";
      String view = "/views/common/msg.jsp";
      
      if(!ServletFileUpload.isMultipartContent(request)) {
         msg="회원가입실패! 사진을 올려주세요";
         return;
      } else {
         String dir = getServletContext().getRealPath("/");
         String filepath = dir+File.separator+"upload"+File.separator+"member";
         int maxSize=1024*1024*10;
         MultipartRequest mr = new MultipartRequest(request, filepath, maxSize, "UTF-8", new MyFileRenamePolicy());
         
         String memberEmail = mr.getParameter("memberEmail");
         String memberEmailaddress = mr.getParameter("memberEmailaddress");
         String memberPassword = mr.getParameter("memberPassword");
         String memberNickname = mr.getParameter("memberNickname");
         String memberName = mr.getParameter("memberName");
         String memberBirthday = mr.getParameter("memberBirthday");
         String memberPhone = mr.getParameter("memberPhone");
         String memberOriginalImage = mr.getOriginalFileName("memberOriginalImage");
         String memberRenameImage = mr.getFilesystemName("memberOriginalImage");
         String finalEmail = memberEmail+memberEmailaddress;
         
         Member m = new Member();
         
         m.setMemberEmail(finalEmail);
         m.setMemberPassword(memberPassword);
         m.setMemberNickname(memberNickname);
         m.setMemberName(memberName);
         m.setMemberBirthday(memberBirthday);
         m.setMemberPhone(memberPhone);
         m.setMemberOriginalImage(memberOriginalImage);
         m.setMemberRenamedImage(memberRenameImage);
         
         int result = new MemberService().enrollMember(m);
         
         if(result>0) {
            msg = "회원가입 완료";
            loc = "/member/loginPage?loginBtn=1";
         }
         else {
            msg = "회원가입 실패";
            loc = "/member/loginPage?loginBtn=1&signupWindow=1";
         }
         request.setAttribute("msg", msg);
         request.setAttribute("loc", loc);
         request.setAttribute("member", m);
         request.getRequestDispatcher(view).forward(request, response);
      }
 
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}