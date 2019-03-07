package com.hobbyist.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.mycart.model.service.MyCartService;
import com.hobbyist.oneday.model.service.OnedayService;
import com.hobbyist.order.model.service.OrderService;
import com.hobbyist.order.model.vo.Order;

@WebServlet("/order/orderInsert")
public class OrderInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cartNo_temp = request.getParameter("cartNoVal");
		String cartNo[] = cartNo_temp.split(",");
		System.out.println("---------------- 주문처리 (★) ---------------");
		// 주문정보 매개변수로 받아오기 ------------------------------------->>
		String member = request.getParameter("member");
		String classNo_temp = request.getParameter("classNo");
		String classNo[] = classNo_temp.split(",");
		String classOption_temp = request.getParameter("classOption");
		String classOption[] = classOption_temp.split(",");
		String orderType = request.getParameter("order_type");
		int orderPrice = Integer.parseInt(request.getParameter("order_price"));
		String orderAddName = request.getParameter("order_add_name");
		String orderAddPhone = request.getParameter("order_add_phone");
		String orderAddAddress = request.getParameter("order_add_address");
		String orderMsg = request.getParameter("order_msg");
		
		System.out.println("주문하기 전 값이 잘들어왔나? : " + member + " , " + classNo_temp + " , " + " , " + classOption_temp  + " , " + orderType  + " , " + orderPrice + " , " + orderAddName  + " , " + orderAddPhone + " , " + orderAddAddress + " , " + orderMsg);;
		for(String s : cartNo) {
			System.out.println("받은 카트번호 : " + s);
		}
		for(String s : classNo) {
			System.out.println("결제하는 클래스 : " + s);
		}
		for(String s : classOption) {
			System.out.println("결제하는 클래스옵션들 : " + s);
		}
		
		
		// ------------------------------------------------------------------------------------------------------
		
		String msg = "";
		String loc = "";
		String view = "/views/common/msg.jsp";
		
		String flag = request.getParameter("orderOneday");
		
		System.out.println("클래스샵인지 원데이인지 : " + flag);
		// 원데이클래스 주문인지 아닌지 확인
		if(!flag.equals("oneday")) {
			System.out.println("---------------- 클래스샵 주문처리 (★★) ---------------");
			int randomNum = 0;
			int result = 0;
			// ----------------------------- 핀코드 등록하기
				for(String s : classNo) {
				randomNum = (int) ((Math.random()*100000000)+1);
				
				// 이미 있는 핀코드인지 구별하기
				int isPincode = new OrderService().selectPincode(randomNum);
				if(isPincode>0) {
					randomNum = (int) ((Math.random()*100000000)+1);
				}
				result += new OrderService().insertPin(randomNum, member, Integer.parseInt(s));
			}
			
			if(result>0) {
				System.out.println("PINCODE [" + randomNum + "]가 생성되었습니다");
			} else {
				System.out.println("PINCODE 생성 실패");
			}
			System.out.println("---------------- 주문처리 (★★★) ---------------");
			int insertResult = 0;
			
			// ----------------------------- 결제정보 등록
			int randomCode_temp = (int) ((Math.random()*10000000)+1);
			String randomCode = "A" + randomCode_temp;   // 주문번호
			for(int i=0; i<classNo.length; i++) {
					insertResult = new OrderService().insertOrder(randomCode, member, classNo[i], classOption[i], orderType, orderPrice, orderAddName, orderAddPhone, orderAddAddress, orderMsg, "클래스샵");
			}
			System.out.println("---------------- 주문처리 (★★★★★) 완료 ---------------");
			
			
			if(insertResult>0) {
				// 장바구니 지워주기 ---------------------------------------------------
				for(int i=0; i<cartNo.length;i++) {
					new MyCartService().deleteCart(Integer.parseInt(cartNo[i]));
				}
				
				msg = "결제가 완료되었습니다 [ 핀코드(PINCODE) : " + randomNum + " ] 핀코드를 복사(Ctrl + C)";
				loc = "/myClass?member=" + member;
			} else {
				msg = "결제실패";
				loc = "/myCart?=" + member;
			}
			
		} else {
			
			// 원데이클래스 주문 ---------------------------------------------------------------------------------------------
			
			int updateOneday = 0;
			int randomCode_temp = (int) ((Math.random()*10000000)+1);
			String randomCode = "B" + randomCode_temp;   // 주문번호
			for(int i=0; i<classNo.length; i++) {
				updateOneday = new OnedayService().updateOrderOneday(Integer.parseInt(classNo[i]));
				if(updateOneday>0) {
					new OrderService().insertOrder(randomCode, member, classNo[i], classOption[i], orderType, orderPrice, orderAddName, orderAddPhone, orderAddAddress, orderMsg, "원데이클래스");
					msg += "주문번호 : " + randomCode + "예약성공 ";
					
					// 장바구니 지워주기 ---------------------------------------------------
					for(int j=0; j<cartNo.length;j++) {
						new MyCartService().deleteCart(Integer.parseInt(cartNo[j]));
					}
				} else {
					msg += "모집인원 초과되었습니다, 예약 실패";
				}
			}
			loc = "/myClass?member=" + member;
			
		}
		
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
