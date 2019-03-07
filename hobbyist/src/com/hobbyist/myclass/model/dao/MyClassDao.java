package com.hobbyist.myclass.model.dao;

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

import com.hobbyist.myclass.model.vo.Lecture;
import com.hobbyist.myclass.model.vo.MyClass;
import com.hobbyist.shop.model.dao.ShopDao;

public class MyClassDao {

static Properties prop = new Properties(); 
	
	public MyClassDao() {
		String fileName = ShopDao.class.getResource("/sql/myclass/myclass.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertClass(Connection conn, MyClass mc) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertMyClass");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mc.getMyClassMember());
			pstmt.setInt(2, mc.getMyClassClass());
			pstmt.setString(3, null);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 내클래스 목록 가져오기
	public List<MyClass> selectMyClass(Connection conn, String member) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyClass> list = new ArrayList<MyClass>();
		
		try {
			String sql = prop.getProperty("selectMyClass");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MyClass mc = new MyClass();
				mc.setMyClassNo(rs.getInt(1));
				mc.setMyClassMember(rs.getString(2));
				mc.setMyClassClass(rs.getInt(3));
				mc.setMyClassProgress(rs.getString(4));
				mc.setMyClassInfo(rs.getString(5));
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

	// 강좌불러오기
	public List<Lecture> selectLecture(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Lecture> list = new ArrayList<Lecture>();
		try {
			String sql = prop.getProperty("selectLecture");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Lecture lc = new Lecture();
				lc.setLectureNo(rs.getInt(1));
				lc.setLectureClass(rs.getInt(2));
				lc.setLectureWriter(rs.getString(3));
				lc.setLectureTime(rs.getInt(4));
				lc.setLectureTitle(rs.getString(5));
				lc.setLectureInfo(rs.getString(6));
				lc.setLectureContent(rs.getString(7));
				lc.setLectureVideo(rs.getString(8));
				lc.setLectureDate(rs.getDate(9));
				list.add(lc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 강좌세부내용 불러오기
	public Lecture selectLectureOne(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Lecture lecture = null;
		try {
			String sql = prop.getProperty("selectLectureOne");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				lecture = new Lecture();
				lecture.setLectureNo(rs.getInt(1));
				lecture.setLectureClass(rs.getInt(2));
				lecture.setLectureWriter(rs.getString(3));
				lecture.setLectureTime(rs.getInt(4));
				lecture.setLectureTitle(rs.getString(5));
				lecture.setLectureInfo(rs.getString(6));
				lecture.setLectureContent(rs.getString(7));
				lecture.setLectureVideo(rs.getString(8));
				lecture.setLectureDate(rs.getDate(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return lecture;
	}

	// 내클래스 진행상태 업데이트
	/*
	 * public int updateClass(Connection conn, int no, String member) {
	 * PreparedStatement pstmt = null; int result = 0;
	 * 
	 * try { String sql = prop.getProperty("updateClass"); pstmt =
	 * conn.prepareStatement(sql); pstmt.setInt(1, no); pstmt.setString(2, member);
	 * result = pstmt.executeUpdate(); }
	 * 
	 * return 0; }
	 */

}
