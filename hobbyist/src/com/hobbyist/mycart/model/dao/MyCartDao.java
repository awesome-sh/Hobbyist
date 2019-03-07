package com.hobbyist.mycart.model.dao;

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

import com.hobbyist.mycart.model.vo.MyCart;
import com.hobbyist.shop.model.dao.ShopDao;

import static common.JdbcTemplate.*;

public class MyCartDao {

	static Properties prop = new Properties(); 
	
	public MyCartDao() {
		String fileName = ShopDao.class.getResource("/sql/mycart/mycart.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<MyCart> selectCartList(Connection conn, String member) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyCart> list = new ArrayList<MyCart>();
		try {
			String sql = prop.getProperty("selectCartList");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MyCart mc = new MyCart();
				mc.setMyCartNo(rs.getInt("mycart_no"));
				mc.setMyCartMember(rs.getString("mycart_member"));
				mc.setMyCartClass(rs.getInt("mycart_class"));
				mc.setMyCartDate(rs.getTimestamp("mycart_date"));
				mc.setMyCartCate(rs.getString("mycart_cate"));
				mc.setMyCartClassOption(rs.getString("mycart_class_option"));
				list.add(mc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<MyCart> selectCartList(Connection conn, String member, String cate) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyCart> list = new ArrayList<MyCart>();
		try {
			String sql = prop.getProperty("selectCartList");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member);
			pstmt.setString(2, cate);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MyCart mc = new MyCart();
				mc.setMyCartNo(rs.getInt("mycart_no"));
				mc.setMyCartMember(rs.getString("mycart_member"));
				mc.setMyCartClass(rs.getInt("mycart_class"));
				mc.setMyCartDate(rs.getTimestamp("mycart_date"));
				mc.setMyCartCate(rs.getString("mycart_cate"));
				mc.setMyCartClassOption(rs.getString("mycart_class_option"));
				list.add(mc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public int insertCart(Connection conn, MyCart mc) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertCart");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mc.getMyCartMember());
			pstmt.setInt(2, mc.getMyCartClass());
			pstmt.setString(3, mc.getMyCartCate());
			pstmt.setString(4, mc.getMyCartClassOption());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteCart(Connection conn, int cartno) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteCart");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectCount(Connection conn, String member) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			String sql = prop.getProperty("selectCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}

}
