package com.hobbyist.shop.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/shop/shopShortUrl")
public class ShopShortUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShopShortUrlServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 네이버 짧은글 주소 Api 서블릿 시작
		
		// 하비스트 해당 상세페이지 주소를 넘겨 받자~
		String urlText = request.getParameter("urlText");
		
		// 박성화 네이버 개발자 클라이언트 아이디와 시크릿값
		String clientId = "lFw_DwfPJFpa3GMgq6Bq";   //애플리케이션 클라이언트 아이디값";
        String clientSecret = "BUH3NoYnHT";   //애플리케이션 클라이언트 시크릿값";
        
        try {
            String apiURL = "https://openapi.naver.com/v1/util/shorturl?url=" + urlText;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer sb = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();
            
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(sb);
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
