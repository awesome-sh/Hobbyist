package com.hobbyist.mycart.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.hobbyist.mycart.model.dao.MyCartDao;
import com.hobbyist.mycart.model.vo.MyCart;

public class MyCartService {

	public List<MyCart> selectCartList(String member, String cate) {
		Connection conn = getConnection();
		List<MyCart> list = new MyCartDao().selectCartList(conn, member, cate);
		close(conn);
		return list;
	}

	public int insertCart(MyCart mc) {
		Connection conn = getConnection();
		int result = new MyCartDao().insertCart(conn, mc);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	// 장바구니 삭제
	public int deleteCart(int cartno) {
		Connection conn = getConnection();
		int result = new MyCartDao().deleteCart(conn, cartno);
		close(conn);
		return result;
	}

	public int selectCount(String member) {
		Connection conn = getConnection();
		int result = new MyCartDao().selectCount(conn, member);
		close(conn);
		return result;
	}
}
