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
 * Servlet implementation class BoardEndDirectQuestionServlet
 */
@WebServlet("/board/boardEndDirectQuestion")
public class BoardEndDirectQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardEndDirectQuestionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "경고! 접근방식에 문제가 발생하였습니다.");
			request.setAttribute("loc", "/board/boardList");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		String dir = getServletContext().getRealPath("/upload");
		String filePath = dir + File.separator + "board";
		
		int maxSize = 1024*1024*10;
		
		MultipartRequest mr = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		BoardDQ b = new BoardDQ();
		
		b.setBoardDQTitle (mr.getParameter("title"));
		b.setBoardDQWriter(mr.getParameter("writer"));
		b.setBoardDQContent(mr.getParameter("content"));
		b.setBoardDQOriginalFileName(mr.getOriginalFileName("up_file"));
		b.setBoardDQReNameFileName(mr.getFilesystemName("up_file"));
		
		int result = new BoardService().insertDQBoard(b);
		
		String msg = "";
		String loc = "/board/boardDirectQuestion";
		String view = "/views/common/msg.jsp";
		
		if(result > 0) {
			msg = "문의 등록되었습니다! 처리가 지연되어 답변이 늦어질수 있습니다.";
		} else {
			msg = "문의등록 실패";
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
