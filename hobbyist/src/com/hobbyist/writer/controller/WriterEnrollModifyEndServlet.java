package com.hobbyist.writer.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.hobbyist.writer.model.service.WriterService;
import com.hobbyist.writer.model.vo.WriterEnroll;
import com.oreilly.servlet.MultipartRequest;

import common.rename.MyFileRenamePolicy;

/**
 * Servlet implementation class WriterEnrollModifyEndServlet
 */
@WebServlet("/writer/writerEnrollModifyEnd")
public class WriterEnrollModifyEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriterEnrollModifyEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "잘못접근!");
			request.setAttribute("loc", "/writer/writerEnroll");
			request.getRequestDispatcher("views/common/msg.jsp").forward(request, response);
			return;
		}
		
		String root = getServletContext().getRealPath("/upload");
		String filePath = root + File.separator + "writer";
		int maxSize = 1024*1024*10;
		MultipartRequest mr = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		int memberNo = Integer.parseInt(mr.getParameter("memberNo"));
		String memberProfileImg = mr.getParameter("memberProfileImg");
		String memberNickname = mr.getParameter("memberNickname");
		String memberEmail = mr.getParameter("memberEmail");
		String memberName = mr.getParameter("memberName");
		String memberBirthday = mr.getParameter("memberBirthday");
		String memberPhone = mr.getParameter("memberPhone");
		String writerEnrollQuarter = mr.getParameter("writerEnrollQuarter");
		String writerCategory = mr.getParameter("writerCategory");
		String writerAddress = mr.getParameter("writerAddress");
		String writerReason = mr.getParameter("writerReason");
		String writerMajorImgfileOriginal = mr.getOriginalFileName("writerMajorImgfileOriginal");
		String writerMajorImgfileRenamed = mr.getFilesystemName("writerMajorImgfileOriginal");
		String writerScheduleYN = mr.getParameter("writerScheduleYN");
		String writerContractYN = mr.getParameter("writerContractYN");
		String writerWishMonth = mr.getParameter("writerWishMonth");
		String writerClassName = mr.getParameter("writerClassName");
		String writerClassSelectReason = mr.getParameter("writerClassSelectReason");
		int writerClassLevel = Integer.parseInt(mr.getParameter("writerClassLevel"));
		String writerProductTime = mr.getParameter("writerProductTime");
		String writerClassKitWarningPoint = mr.getParameter("writerClassKitWarningPoint");
		String writerClassKitPart = mr.getParameter("writerClassKitPart");
		String classImgfileOriginal = mr.getOriginalFileName("classImgfileOriginal");
		String classImgfileRenamed = mr.getFilesystemName("classImgfileOriginal");
		String writerPrepRequestYN = mr.getParameter("writerPrepRequestYN");
		String writerFinalPoint = mr.getParameter("writerFinalPoint");
		int writerEnrollNo = Integer.parseInt(mr.getParameter("writerEnrollNo"));
		
		//기존 파일 삭제
		File imgM = mr.getFile("writerMajorImgfileOriginal");
		File imgC = mr.getFile("classImgfileOriginal");
		
		if(imgM!=null&&imgM.length()>0) {
			File delFile=new File(filePath+"/"+mr.getParameter("old_wmImgR"));
			boolean resul = delFile.delete();
		} else if(mr.getParameter("wmImgDelFlag").equals("true")){
			writerMajorImgfileOriginal = "";
			writerMajorImgfileRenamed = "";
		} else {
			writerMajorImgfileOriginal = mr.getParameter("old_wmImgO");
			writerMajorImgfileRenamed = mr.getParameter("old_wmImgR");
		}

		if(imgC!=null&&imgC.length()>0) {
			File delFile=new File(filePath+"/"+mr.getParameter("old_cImgR"));
			boolean resul = delFile.delete();
		} else if(mr.getParameter("cImgDelFlag").equals("true")){
			classImgfileOriginal = "";
			classImgfileRenamed = "";
		} else {
			classImgfileOriginal = mr.getParameter("old_cImgO");
			classImgfileRenamed = mr.getParameter("old_cImgR");
		}
		
		WriterEnroll we = new WriterEnroll();
		we.setMemberNo(memberNo);
		we.setMemberProfileImg(memberProfileImg);
		we.setMemberNickname(memberNickname);
		we.setMemberEmail(memberEmail);
		we.setMemberName(memberName);
		we.setMemberBirthday(memberBirthday);
		we.setMemberPhone(memberPhone);
		we.setWriterEnrollQuarter(writerEnrollQuarter);
		we.setWriterCategory(writerCategory);
		we.setWriterAddress(writerAddress);
		we.setWriterReason(writerReason);
		we.setWriterMajorImgfileOriginal(writerMajorImgfileOriginal);
		we.setWriterMajorImgfileRenamed(writerMajorImgfileRenamed);
		we.setWriterScheduleYN(writerScheduleYN);
		we.setWriterContractYN(writerContractYN);
		we.setWriterWishMonth(writerWishMonth);
		we.setWriterClassName(writerClassName);
		we.setWriterClassSelectReason(writerClassSelectReason);
		we.setWriterClassLevel(writerClassLevel);
		we.setWriterProductTime(writerProductTime);
		we.setWriterClassKitWarningPoint(writerClassKitWarningPoint);
		we.setWriterClassKitPart(writerClassKitPart);
		we.setClassImgfileOriginal(classImgfileOriginal);
		we.setClassImgfileRenamed(classImgfileRenamed);
		we.setWriterPrepRequestYN(writerPrepRequestYN);
		we.setWriterFinalPoint(writerFinalPoint);
		we.setWriterEnrollNo(writerEnrollNo);
		
		int result = new WriterService().UpdateWriterEnroll(we);
		
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		if(result>0) {
			msg="작가신청 수정 성공";
			loc="/mypage/mypage_writerEnrollList";
		}
		else {
			msg="작가신청 수정 실패";
			loc="/mypage/mypageWEmodify";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
