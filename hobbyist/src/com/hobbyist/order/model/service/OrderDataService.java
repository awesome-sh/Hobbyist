package com.hobbyist.order.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.hobbyist.order.model.dao.OrderDataDao;
import com.hobbyist.order.model.vo.OrderData;

public class OrderDataService {

	static OrderDataDao dao = new OrderDataDao();
	
	public int insertOrderData(OrderData od) {
		Connection conn = getConnection();
		int result = dao.insertOrderData(conn, od);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public List<OrderData> selectOrderData(String member) {
		Connection conn = getConnection();
		List<OrderData> list = dao.selectOrderData(conn, member);
		close(conn);
		return list;
	}
	
}
