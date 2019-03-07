package com.hobbyist.order.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.hobbyist.order.model.dao.OrderDao;
import com.hobbyist.order.model.vo.Order;
import com.hobbyist.order.model.vo.Pin;

public class OrderService {

	static OrderDao dao = new OrderDao();
	
	public int insertPin(int randomNum, String member, int s) {
		Connection conn = getConnection();
		int result = dao.insertPin(conn, randomNum, member, s);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}


	public int insertOrder(String randomCode, String member, String s, String c, String orderType, int orderPrice,
			String orderAddName, String orderAddPhone, String orderAddAddress, String orderMsg, String orderCate) {
		Connection conn = getConnection();
		int result = dao.insertOrder(conn, randomCode, member, s, c, orderType, orderPrice, orderAddName, orderAddPhone, orderAddAddress, orderMsg, orderCate);
		close(conn);
		return result;
	}

	public int selectPincode(int randomNum) {
		Connection conn = getConnection();
		int result = dao.selectPincode(conn, randomNum);
		close(conn);
		return result;
	}

	public Pin activePin(int pinCode, String member) {
		Connection conn = getConnection();
		Pin pin = dao.activePin(conn, pinCode, member);
		close(conn);
		return pin;
	}

	public int updatePin(int pinCode) {
		Connection conn = getConnection();
		int result = dao.updatePin(conn, pinCode);
		close(conn);
		return result;
	}

	public List<Pin> selectPinList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Pin> list = dao.selectPinList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectPinCount() {
		Connection conn = getConnection();
		int result = dao.selectPinCount(conn);
		close(conn);
		return result;
	}

	public int searchCount() {
		Connection conn = getConnection();
		int result = dao.searchCount(conn);
		close(conn);
		return result;
	}
	
	// 등록순 정렬
		public List<Order> descEnroll(String keyword, int cPage, int numPerPage) {
			Connection conn = getConnection();
			List<Order> list = new OrderDao().descEnroll(conn, keyword, cPage, numPerPage);
			close(conn);
			return list;
		}


		public List<Order> inCome() {
			Connection conn = getConnection();
			List<Order> list = new OrderDao().inCome(conn);
			close(conn);
			return list;
		}


		public int deleteOrder(int no) {
			Connection conn = getConnection();
			int result = new OrderDao().deleteOrder(conn, no);
			close(conn);
			return result;
		}


		public List<Order> exList(int cPage, int numPerPage) {
			Connection conn = getConnection();
			List<Order> list = new OrderDao().exList(conn, cPage, numPerPage);
			close(conn);
			return list;
		}


		public int recoverOrder(int no) {
			Connection conn = getConnection();
			int result = new OrderDao().recoverOrder(conn, no);
			close(conn);
			return result;
		}


		public List<Order> myOrderList(int cPage, int numPerPage, String member) {
			Connection conn = getConnection();
			List<Order> list = new OrderDao().myOrderList(conn, cPage, numPerPage, member);
			close(conn);
			return list;
		}


		public int searchOrderCount(String member) {
			Connection conn = getConnection();
			int result = dao.searchOrderCount(conn, member);
			close(conn);
			return result;
		}


		public int searchExCount() {
			Connection conn = getConnection();
			int result = dao.searchExCount(conn);
			close(conn);
			return result;
		}
		
/*		// 높은가격순
		public List<Order> descPrice(String keyword, int cPage, int numPerPage) {
			Connection conn = getConnection();
			List<Order> list = new OrderDao().descPrice(conn, keyword, cPage, numPerPage);
			close(conn);
			return list;
		}


		// 낮은가격순
		public List<Order> ascPrice(String keyword, int cPage, int numPerPage) {
			Connection conn = getConnection();
			List<Order> list = new OrderDao().ascPrice(conn, keyword, cPage, numPerPage);
			close(conn);
			return list;
		}*/
	
}

