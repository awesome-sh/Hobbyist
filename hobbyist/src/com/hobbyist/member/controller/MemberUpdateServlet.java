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

import common.rename.MyFileRenamePolicy;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//내정보 수정 서블릿
    public MemberUpdateServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			msg="회원수정에 오류가 생겼습니다.";
			return;
		} else {
			String dir = getServletContext().getRealPath("/");
	        String filepath = dir+File.separator+"upload"+File.separator+"member";
	        int maxSize=1024*1024*10;
	        MultipartRequest mr = new MultipartRequest(request, filepath, maxSize, "UTF-8", new MyFileRenamePolicy());
	        
	        String memberEmail = mr.getParameter("memberEmail");
	        String memberNickname = mr.getParameter("memberNickname");
	        String memberPhone = mr.getParameter("memberPhone");
	        String memberOriginalImage = mr.getOriginalFileName("memberOriginalImage");
	        String memberRenameImage = mr.getFilesystemName("memberOriginalImage");
	        
	        System.out.println("업데이트 : "+memberEmail+memberNickname+memberPhone+memberOriginalImage);
	        
			Member m = new Member();
			
			m.setMemberEmail(memberEmail);
			m.setMemberNickname(memberNickname);
			m.setMemberPhone(memberPhone);
		
			File f = mr.getFile("memberOriginalImage");
			
			if(f!=null && f.length()>0) {
				File deleteFile = new File(filepath+"/"+mr.getParameter("old_rename"));
				boolean deleteResult = deleteFile.delete();
				System.out.println(deleteResult?"제대로 지워짐":"안지워짐");
			} else {
				memberOriginalImage=mr.getParameter("old_file");
				memberOriginalImage=mr.getParameter("old_rename");
			}
			
			m.setMemberOriginalImage(memberOriginalImage);
			m.setMemberRenamedImage(memberRenameImage);
			
			int result = new MemberService().updateMember(m);
			
			if(result>0) {
				msg="회원수정이 완료되었습니다.";
				loc="/memberUpdateView.do?memberEmail=" + memberEmail;
				System.out.println("회원수정 성공 (servlet)");
			} else {
				msg="회원수정에 오류가 생겼습니다.";
				System.out.println("회원수정 실패 (servlet)");
				loc="/memberUpdateView.do?memberEmail="+memberEmail;
			}
			System.out.println(result);
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
