package com.hobbyist.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.board.model.vo.BoardDQ;
import com.oreilly.servlet.MultipartRequest;

import common.rename.MyFileRenamePolicy;

/**
 * Servlet implementation class BoardDQReviseEnd
 */
@WebServlet("/board/boardDQReviseEnd")
public class BoardDQReviseEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDQReviseEnd() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "경고! 접근방식에 문제가 발생하였습니다.");
			request.setAttribute("loc", "/board/boardDirectQuestion");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		int boardDQNo = Integer.parseInt(request.getParameter("boardDQNo"));
		
		String dir = getServletContext().getRealPath("/upload");
		String filePath = dir + File.separator + "board";
		
		int maxSize = 10*1024*1024;
		
		MultipartRequest mr = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		BoardDQ b = new BoardDQ();
		
		b.setBoardDQNo(boardDQNo);
		b.setBoardDQTitle(mr.getParameter("title"));
		b.setBoardDQWriter(mr.getParameter("writer"));
		b.setBoardDQContent(mr.getParameter("content"));
		b.setBoardDQOriginalFileName(mr.getOriginalFileName("up_file"));
		b.setBoardDQReNameFileName(mr.getFilesystemName("up_file"));
		
		int result = new BoardService().updateDQBoard(b);
		
		String msg = "";
		String loc = "/board/boardDirectQuestionView?boardDQNo=" + boardDQNo;
		String view = "/views/common/msg.jsp";
		
		if(result > 0) {
			msg = "문의가 수정되었습니다.";
		} else {
			msg = "문의 수정에 실패하였습니다.";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
