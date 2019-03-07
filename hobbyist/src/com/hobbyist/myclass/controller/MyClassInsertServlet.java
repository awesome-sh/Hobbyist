package com.hobbyist.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.myclass.model.service.MyClassService;
import com.hobbyist.myclass.model.vo.MyClass;
import com.hobbyist.order.model.service.OrderService;
import com.hobbyist.order.model.vo.Pin;

@WebServlet("/myClassInsert")
public class MyClassInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyClassInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pinCode = Integer.parseInt(request.getParameter("pin"));
		String member = request.getParameter("member");
		System.out.println("입력받은 핀코드 : " + pinCode);
		System.out.println(" 회원명 : " + member);
		
		Pin pin = new OrderService().activePin(pinCode, member);
		
		String msg = "";
		// 핀코드가
		if(pin!=null) {
			if(pin.getPinStatus().equals("Y")) {
				MyClass mc = new MyClass();
				mc.setMyClassMember(member);
				mc.setMyClassClass(pin.getPinClass());
				// 내클래스에 등록
				new MyClassService().insertClass(mc);
				// 핀코드 사용여부 N 으로 바꿔
				int pinResult = new OrderService().updatePin(pinCode);
				if(pinResult>0) {
					System.out.println("핀코드 사용 완료 [업데이트 완료]");
				}
				msg = "핀코드 등록 성공!";
				System.out.println("핀코드 등록 성공!");
			} else {
				msg = "이미 등록한 핀코드 입니다";
				System.out.println("이미 등록한 핀코드");
			}
		} else {
			msg = "존재하지 않는 핀코드 입니다";
			System.out.println("존재하지 않는 핀코드");
		}
		
		response.getWriter().write(msg);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
