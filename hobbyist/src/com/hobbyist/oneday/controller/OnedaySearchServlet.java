package com.hobbyist.oneday.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.hobbyist.oneday.model.service.OnedayService;
import com.hobbyist.oneday.model.vo.Oneday;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

@WebServlet("/oneday/onedaySearch")
public class OnedaySearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OnedaySearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		
		List<Oneday> list = new OnedayService().ajaxSearch(keyword);
		
		JSONObject json = new JSONObject();
		
		for(int i=0;i<list.size();i++) {
			json.put("subject" + i, list.get(i).getOnedayName());
		}

		String result = json.toJSONString();
		
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
