package com.hobbyist.order.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hobbyist.oneday.model.dao.OnedayDao;
import com.hobbyist.order.model.vo.OrderData;

public class OrderDataDao {

	static Properties prop = new Properties();

	public OrderDataDao() {
		String fileName = OnedayDao.class.getResource("/sql/order/order.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertOrderData(Connection conn, OrderData od) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertOrderData");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, od.getOrderDataMember());
			pstmt.setString(2, od.getOrderDataClass());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<OrderData> selectOrderData(Connection conn, String member) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderData> list = new ArrayList<OrderData>();
		try {
			String sql = prop.getProperty("selectOrderData");
			pstmt = conn.prepareStatement(sql);
			System.out.println("MEMBER :" + member);
			pstmt.setString(1, member);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderData od = new OrderData();
				od.setOrderDataNo(rs.getInt("order_data_no"));
				od.setOrderDataMember(rs.getString("order_data_member"));
				od.setOrderDataClass(rs.getString("order_data_class"));
				od.setOrderDataDate(rs.getTimestamp("order_data_date"));
				list.add(od);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
}
