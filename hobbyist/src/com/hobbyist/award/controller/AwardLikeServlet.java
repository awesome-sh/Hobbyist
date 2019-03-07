package com.hobbyist.award.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.award.model.service.AwardService;


@WebServlet("/award/awardLike")
public class AwardLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AwardLikeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	int awardNo = Integer.parseInt(request.getParameter("awardNo"));
	String userId = request.getParameter("userId");
	
	System.out.println("추천수 올릴 게시물 번호 : " + awardNo);
	System.out.println("추천한 회원아이디 : " + userId);
	
	boolean flag = false;
	
	// 이미 추천한 아이디 인지 확인하기 위해 어워드라이크 조회
	String users = new AwardService().selectLikeUser(awardNo);
	
	// 값이 없다면 users는 비어있겠지? user = "";
	
	// 값이 있다면 , 로 유저아이디를 담을 배열그릇을 만들어준다
	String[] temp;
	List<String> userlist = new ArrayList<String>();
	
	// 만약 users에 값이 있다면
	if(users!=null) {
		// users를 콤마로 잘라 temp 배열에 담는다
		temp = users.split(",");
		for(int i=0;i<temp.length;i++) {
			// userlist 어레이리스트에 자른 users(회원아이디)를 담는다
			userlist.add(temp[i]);
		}
		
		// userlist 리스트를 돌면서 지금 추천을 누른 아이디를 찾아 값이 있으면 해당 아이디를 제거한다.
		for(int i=0;i<userlist.size();i++) {
	 		if(userlist.get(i).equals(userId)) {
	 			// 그리고 flag를 true로 바꾼다.
	 			userlist.remove(i);
	 			flag  = true;
	 			break;
	 		}
	 	}
	}
 	
 	if(flag) {
 		users="";
 		for(int i=0;i<userlist.size();i++) {
 			if(i==0) {
 				users = userlist.get(i);
 			} else {
 				users += "," + userlist.get(i);
 			}
 		}
 		
 		new AwardService().increLikeCountsub(awardNo);
 		new AwardService().increLikeUsers(awardNo, users);
 		
 		String msg = "게시물 추천취소";
 		System.out.println(msg);
 		response.setContentType("text/html; charset=UTF-8");
 		response.getWriter().print(msg);
 	} else {
 		if(users==null) {
 			users = userId;
 		} else {
 			users += "," + userId;
 		}
 		new AwardService().increLikeCountAdd(awardNo);
 		new AwardService().increLikeUsers(awardNo,users);
 		
 		String msg = "어워드가 추천되었습니다.";
 		System.out.println(msg);
 		
 		response.setContentType("text/html; charset=UTF-8");
 		response.getWriter().print(msg);
 	}
 	
}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
