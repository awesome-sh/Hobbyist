package com.hobbyist.oneday.model.dao;

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

import com.hobbyist.member.model.vo.Member;
import com.hobbyist.oneday.model.vo.Oneday;
import com.hobbyist.oneday.model.vo.Cate;
public class OnedayDao {

	static Properties prop = new Properties();

	public OnedayDao() {
		String fileName = OnedayDao.class.getResource("/sql/oneday/oneday.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 기본 샵 리스트 가져오기
	public List<Oneday> onedayBasicList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Oneday> list = new ArrayList<Oneday>();

		try {
			String sql = prop.getProperty("onedayBasicList");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Oneday oneday = new Oneday();
				oneday.setOnedayNo(rs.getInt("oneday_no"));
				oneday.setOnedayName(rs.getString("oneday_name"));
				oneday.setOnedayCate(rs.getString("oneday_cate"));
				oneday.setOnedayInfo(rs.getString("oneday_info"));
				oneday.setOnedayWriter(rs.getString("oneday_writer"));
				oneday.setOnedayContent(rs.getString("oneday_content"));
				oneday.setOnedayPrice(rs.getInt("oneday_price"));
				oneday.setOnedayOption1(rs.getString("oneday_option1"));
				oneday.setOnedayOption2(rs.getString("oneday_option2"));
				oneday.setOnedayOption3(rs.getString("oneday_option3"));
				oneday.setOnedayOption4(rs.getString("oneday_option4"));
				oneday.setOnedayOption5(rs.getString("oneday_option5"));
				oneday.setOnedayImage1(rs.getString("oneday_image1"));
				oneday.setOnedayImage2(rs.getString("oneday_image2"));
				oneday.setOnedayImage3(rs.getString("oneday_image3"));
				oneday.setOnedayImage4(rs.getString("oneday_image4"));
				oneday.setOnedayImage5(rs.getString("oneday_image5"));
				oneday.setOnedayPolicy(rs.getString("oneday_policy"));
				oneday.setOnedayDate(rs.getDate("oneday_date"));
				oneday.setOnedayPeople(rs.getInt("oneday_people"));
				oneday.setOnedayCurrentPeople(rs.getInt("oneday_current_people"));
				oneday.setOnedayReservationStatus(rs.getString("oneday_reservation_status"));
				oneday.setOnedayAddress(rs.getString("oneday_address"));
				list.add(oneday);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 검색결과에 따른 총개수 가져오기
	public int searchCount(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			String sql = prop.getProperty("searchCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 등록일순 정렬 리스트 가져오기
	public List<Oneday> descEnroll(Connection conn, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Oneday> list = new ArrayList<Oneday>();

		try {
			String sql = prop.getProperty("descEnroll");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Oneday oneday = new Oneday();
				oneday.setOnedayNo(rs.getInt("oneday_no"));
				oneday.setOnedayName(rs.getString("oneday_name"));
				oneday.setOnedayCate(rs.getString("oneday_cate"));
				oneday.setOnedayInfo(rs.getString("oneday_info"));
				oneday.setOnedayWriter(rs.getString("oneday_writer"));
				oneday.setOnedayContent(rs.getString("oneday_content"));
				oneday.setOnedayPrice(rs.getInt("oneday_price"));
				oneday.setOnedayOption1(rs.getString("oneday_option1"));
				oneday.setOnedayOption2(rs.getString("oneday_option2"));
				oneday.setOnedayOption3(rs.getString("oneday_option3"));
				oneday.setOnedayOption4(rs.getString("oneday_option4"));
				oneday.setOnedayOption5(rs.getString("oneday_option5"));
				oneday.setOnedayImage1(rs.getString("oneday_image1"));
				oneday.setOnedayImage2(rs.getString("oneday_image2"));
				oneday.setOnedayImage3(rs.getString("oneday_image3"));
				oneday.setOnedayImage4(rs.getString("oneday_image4"));
				oneday.setOnedayImage5(rs.getString("oneday_image5"));
				oneday.setOnedayPolicy(rs.getString("oneday_policy"));
				oneday.setOnedayDate(rs.getDate("oneday_date"));
				oneday.setOnedayPeople(rs.getInt("oneday_people"));
				oneday.setOnedayCurrentPeople(rs.getInt("oneday_current_people"));
				oneday.setOnedayReservationStatus(rs.getString("oneday_reservation_status"));
				oneday.setOnedayAddress(rs.getString("oneday_address"));
				list.add(oneday);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}


	// 높은가격순 정렬 리스트 가져오기
	public List<Oneday> descPrice(Connection conn, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Oneday> list = new ArrayList<Oneday>();

		try {
			String sql = prop.getProperty("descPrice");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Oneday oneday = new Oneday();
				oneday.setOnedayNo(rs.getInt("oneday_no"));
				oneday.setOnedayName(rs.getString("oneday_name"));
				oneday.setOnedayCate(rs.getString("oneday_cate"));
				oneday.setOnedayInfo(rs.getString("oneday_info"));
				oneday.setOnedayWriter(rs.getString("oneday_writer"));
				oneday.setOnedayContent(rs.getString("oneday_content"));
				oneday.setOnedayPrice(rs.getInt("oneday_price"));
				oneday.setOnedayOption1(rs.getString("oneday_option1"));
				oneday.setOnedayOption2(rs.getString("oneday_option2"));
				oneday.setOnedayOption3(rs.getString("oneday_option3"));
				oneday.setOnedayOption4(rs.getString("oneday_option4"));
				oneday.setOnedayOption5(rs.getString("oneday_option5"));
				oneday.setOnedayImage1(rs.getString("oneday_image1"));
				oneday.setOnedayImage2(rs.getString("oneday_image2"));
				oneday.setOnedayImage3(rs.getString("oneday_image3"));
				oneday.setOnedayImage4(rs.getString("oneday_image4"));
				oneday.setOnedayImage5(rs.getString("oneday_image5"));
				oneday.setOnedayPolicy(rs.getString("oneday_policy"));
				oneday.setOnedayDate(rs.getDate("oneday_date"));
				oneday.setOnedayPeople(rs.getInt("oneday_people"));
				oneday.setOnedayCurrentPeople(rs.getInt("oneday_current_people"));
				oneday.setOnedayReservationStatus(rs.getString("oneday_reservation_status"));
				oneday.setOnedayAddress(rs.getString("oneday_address"));
				list.add(oneday);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 낮은가격순 정렬 리스트 가져오기
	public List<Oneday> ascPrice(Connection conn, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Oneday> list = new ArrayList<Oneday>();

		try {
			String sql = prop.getProperty("ascPrice");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Oneday oneday = new Oneday();
				oneday.setOnedayNo(rs.getInt("oneday_no"));
				oneday.setOnedayName(rs.getString("oneday_name"));
				oneday.setOnedayCate(rs.getString("oneday_cate"));
				oneday.setOnedayInfo(rs.getString("oneday_info"));
				oneday.setOnedayWriter(rs.getString("oneday_writer"));
				oneday.setOnedayContent(rs.getString("oneday_content"));
				oneday.setOnedayPrice(rs.getInt("oneday_price"));
				oneday.setOnedayOption1(rs.getString("oneday_option1"));
				oneday.setOnedayOption2(rs.getString("oneday_option2"));
				oneday.setOnedayOption3(rs.getString("oneday_option3"));
				oneday.setOnedayOption4(rs.getString("oneday_option4"));
				oneday.setOnedayOption5(rs.getString("oneday_option5"));
				oneday.setOnedayImage1(rs.getString("oneday_image1"));
				oneday.setOnedayImage2(rs.getString("oneday_image2"));
				oneday.setOnedayImage3(rs.getString("oneday_image3"));
				oneday.setOnedayImage4(rs.getString("oneday_image4"));
				oneday.setOnedayImage5(rs.getString("oneday_image5"));
				oneday.setOnedayPolicy(rs.getString("oneday_policy"));
				oneday.setOnedayDate(rs.getDate("oneday_date"));
				oneday.setOnedayPeople(rs.getInt("oneday_people"));
				oneday.setOnedayCurrentPeople(rs.getInt("oneday_current_people"));
				oneday.setOnedayReservationStatus(rs.getString("oneday_reservation_status"));
				oneday.setOnedayAddress(rs.getString("oneday_address"));
				list.add(oneday);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 반응형 검색창
	public List<Oneday> ajaxSearch(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Oneday> list = new ArrayList<Oneday>();

		try {
			String sql = prop.getProperty("ajaxSearch");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Oneday oneday = new Oneday();
				oneday.setOnedayName(rs.getString("oneday_name"));
				list.add(oneday);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	
	// 카테고리 불러오기
	public List<Cate> CateList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Cate> list = new ArrayList<Cate>();

		try {
			String sql = prop.getProperty("CateList");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Cate cate = new Cate();
				cate.setCateNo(rs.getInt(1));
				cate.setCateTitle(rs.getString(2));
				list.add(cate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 신규카테고리 생성
	public int insertCate(Connection conn, String newCate) {
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			String sql = prop.getProperty("insertCate");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newCate);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public Oneday selectOne(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Oneday oneday = null;

		try {
			String sql = prop.getProperty("selectOne");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				oneday = new Oneday();
				oneday.setOnedayNo(rs.getInt("oneday_no"));
				oneday.setOnedayName(rs.getString("oneday_name"));
				oneday.setOnedayCate(rs.getString("oneday_cate"));
				oneday.setOnedayInfo(rs.getString("oneday_info"));
				oneday.setOnedayWriter(rs.getString("oneday_writer"));
				oneday.setOnedayContent(rs.getString("oneday_content"));
				oneday.setOnedayPrice(rs.getInt("oneday_price"));
				oneday.setOnedayOption1(rs.getString("oneday_option1"));
				oneday.setOnedayOption2(rs.getString("oneday_option2"));
				oneday.setOnedayOption3(rs.getString("oneday_option3"));
				oneday.setOnedayOption4(rs.getString("oneday_option4"));
				oneday.setOnedayOption5(rs.getString("oneday_option5"));
				oneday.setOnedayImage1(rs.getString("oneday_image1"));
				oneday.setOnedayImage2(rs.getString("oneday_image2"));
				oneday.setOnedayImage3(rs.getString("oneday_image3"));
				oneday.setOnedayImage4(rs.getString("oneday_image4"));
				oneday.setOnedayImage5(rs.getString("oneday_image5"));
				oneday.setOnedayPolicy(rs.getString("oneday_policy"));
				oneday.setOnedayDate(rs.getDate("oneday_date"));
				oneday.setOnedayPeople(rs.getInt("oneday_people"));
				oneday.setOnedayCurrentPeople(rs.getInt("oneday_current_people"));
				oneday.setOnedayReservationStatus(rs.getString("oneday_reservation_status"));
				oneday.setOnedayAddress(rs.getString("oneday_address"));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return oneday;
	}

	public List<Oneday> selectAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Oneday> list = new ArrayList<Oneday>();

		try {
			String sql = prop.getProperty("selectAll");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Oneday oneday = new Oneday();
				oneday.setOnedayNo(rs.getInt("oneday_no"));
				oneday.setOnedayName(rs.getString("oneday_name"));
				oneday.setOnedayCate(rs.getString("oneday_cate"));
				oneday.setOnedayInfo(rs.getString("oneday_info"));
				oneday.setOnedayWriter(rs.getString("oneday_writer"));
				oneday.setOnedayContent(rs.getString("oneday_content"));
				oneday.setOnedayPrice(rs.getInt("oneday_price"));
				oneday.setOnedayOption1(rs.getString("oneday_option1"));
				oneday.setOnedayOption2(rs.getString("oneday_option2"));
				oneday.setOnedayOption3(rs.getString("oneday_option3"));
				oneday.setOnedayOption4(rs.getString("oneday_option4"));
				oneday.setOnedayOption5(rs.getString("oneday_option5"));
				oneday.setOnedayImage1(rs.getString("oneday_image1"));
				oneday.setOnedayImage2(rs.getString("oneday_image2"));
				oneday.setOnedayImage3(rs.getString("oneday_image3"));
				oneday.setOnedayImage4(rs.getString("oneday_image4"));
				oneday.setOnedayImage5(rs.getString("oneday_image5"));
				oneday.setOnedayPolicy(rs.getString("oneday_policy"));
				oneday.setOnedayDate(rs.getDate("oneday_date"));
				oneday.setOnedayPeople(rs.getInt("oneday_people"));
				oneday.setOnedayCurrentPeople(rs.getInt("oneday_current_people"));
				oneday.setOnedayReservationStatus(rs.getString("oneday_reservation_status"));
				oneday.setOnedayAddress(rs.getString("oneday_address"));
				list.add(oneday);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int onedayInsert(Connection conn, Oneday oneday) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = prop.getProperty("onedayInsert");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oneday.getOnedayName());
			pstmt.setString(2, oneday.getOnedayCate());
			pstmt.setString(3, oneday.getOnedayInfo());
			pstmt.setString(4, oneday.getOnedayWriter());
			pstmt.setString(5, 	oneday.getOnedayContent());
			pstmt.setInt(6, oneday.getOnedayPrice());
			pstmt.setString(7, oneday.getOnedayOption1());
			pstmt.setString(8, oneday.getOnedayOption2());
			pstmt.setString(9, oneday.getOnedayOption3());
			pstmt.setString(10, oneday.getOnedayOption4());
			pstmt.setString(11, oneday.getOnedayOption5());
			pstmt.setString(12, oneday.getOnedayImage1());
			pstmt.setString(13, oneday.getOnedayImage2());
			pstmt.setString(14, oneday.getOnedayImage3());
			pstmt.setString(15, oneday.getOnedayImage4());
			pstmt.setString(16, oneday.getOnedayImage5());
			pstmt.setString(17, oneday.getOnedayPolicy());
			pstmt.setInt(18, oneday.getOnedayPeople());
			pstmt.setString(19, oneday.getOnedayAddress());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}

	public List<Member> writerSearch(Connection conn, String classWriter) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<Member>();

		try {
			String sql = prop.getProperty("writerSearch");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + classWriter + "%");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Member member = new Member();
				member.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int updateOneday(Connection conn, Oneday oneday) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateOneday");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oneday.getOnedayName());
			pstmt.setString(2, oneday.getOnedayInfo());
			pstmt.setString(3, oneday.getOnedayWriter());
			pstmt.setString(4, oneday.getOnedayContent());
			pstmt.setInt(5, oneday.getOnedayPrice());
			pstmt.setString(6, oneday.getOnedayOption1());
			pstmt.setString(7, oneday.getOnedayOption2());
			pstmt.setString(8, oneday.getOnedayOption3());
			pstmt.setString(9, oneday.getOnedayOption4());
			pstmt.setString(10, oneday.getOnedayOption5());
			pstmt.setString(11, oneday.getOnedayImage1());
			pstmt.setString(12, oneday.getOnedayImage2());
			pstmt.setString(13, oneday.getOnedayImage3());
			pstmt.setString(14, oneday.getOnedayImage4());
			pstmt.setString(15, oneday.getOnedayImage5());
			pstmt.setString(16, oneday.getOnedayPolicy());
			pstmt.setInt(17, oneday.getOnedayPeople());
			pstmt.setString(18, oneday.getOnedayAddress());
			pstmt.setInt(19, oneday.getOnedayNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int searchCateCount(Connection conn, String cate, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("searchCateCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			pstmt.setString(2, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public List<Oneday> descEnrollCate(Connection conn, String cate, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Oneday> list = new ArrayList<Oneday>();

		try {
			String sql = prop.getProperty("descEnrollCate");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Oneday oneday = new Oneday();
				oneday.setOnedayNo(rs.getInt("oneday_no"));
				oneday.setOnedayName(rs.getString("oneday_name"));
				oneday.setOnedayCate(rs.getString("oneday_cate"));
				oneday.setOnedayInfo(rs.getString("oneday_info"));
				oneday.setOnedayWriter(rs.getString("oneday_writer"));
				oneday.setOnedayContent(rs.getString("oneday_content"));
				oneday.setOnedayPrice(rs.getInt("oneday_price"));
				oneday.setOnedayOption1(rs.getString("oneday_option1"));
				oneday.setOnedayOption2(rs.getString("oneday_option2"));
				oneday.setOnedayOption3(rs.getString("oneday_option3"));
				oneday.setOnedayOption4(rs.getString("oneday_option4"));
				oneday.setOnedayOption5(rs.getString("oneday_option5"));
				oneday.setOnedayImage1(rs.getString("oneday_image1"));
				oneday.setOnedayImage2(rs.getString("oneday_image2"));
				oneday.setOnedayImage3(rs.getString("oneday_image3"));
				oneday.setOnedayImage4(rs.getString("oneday_image4"));
				oneday.setOnedayImage5(rs.getString("oneday_image5"));
				oneday.setOnedayPolicy(rs.getString("oneday_policy"));
				oneday.setOnedayDate(rs.getDate("oneday_date"));
				oneday.setOnedayPeople(rs.getInt("oneday_people"));
				oneday.setOnedayCurrentPeople(rs.getInt("oneday_current_people"));
				oneday.setOnedayReservationStatus(rs.getString("oneday_reservation_status"));
				oneday.setOnedayAddress(rs.getString("oneday_address"));
				list.add(oneday);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<Oneday> descPriceCate(Connection conn, String cate, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Oneday> list = new ArrayList<Oneday>();

		try {
			String sql = prop.getProperty("descPriceCate");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Oneday oneday = new Oneday();
				oneday.setOnedayNo(rs.getInt("oneday_no"));
				oneday.setOnedayName(rs.getString("oneday_name"));
				oneday.setOnedayCate(rs.getString("oneday_cate"));
				oneday.setOnedayInfo(rs.getString("oneday_info"));
				oneday.setOnedayWriter(rs.getString("oneday_writer"));
				oneday.setOnedayContent(rs.getString("oneday_content"));
				oneday.setOnedayPrice(rs.getInt("oneday_price"));
				oneday.setOnedayOption1(rs.getString("oneday_option1"));
				oneday.setOnedayOption2(rs.getString("oneday_option2"));
				oneday.setOnedayOption3(rs.getString("oneday_option3"));
				oneday.setOnedayOption4(rs.getString("oneday_option4"));
				oneday.setOnedayOption5(rs.getString("oneday_option5"));
				oneday.setOnedayImage1(rs.getString("oneday_image1"));
				oneday.setOnedayImage2(rs.getString("oneday_image2"));
				oneday.setOnedayImage3(rs.getString("oneday_image3"));
				oneday.setOnedayImage4(rs.getString("oneday_image4"));
				oneday.setOnedayImage5(rs.getString("oneday_image5"));
				oneday.setOnedayPolicy(rs.getString("oneday_policy"));
				oneday.setOnedayDate(rs.getDate("oneday_date"));
				oneday.setOnedayPeople(rs.getInt("oneday_people"));
				oneday.setOnedayCurrentPeople(rs.getInt("oneday_current_people"));
				oneday.setOnedayReservationStatus(rs.getString("oneday_reservation_status"));
				oneday.setOnedayAddress(rs.getString("oneday_address"));
				list.add(oneday);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<Oneday> ascPriceCate(Connection conn, String cate, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Oneday> list = new ArrayList<Oneday>();

		try {
			String sql = prop.getProperty("ascPriceCate");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Oneday oneday = new Oneday();
				oneday.setOnedayNo(rs.getInt("oneday_no"));
				oneday.setOnedayName(rs.getString("oneday_name"));
				oneday.setOnedayCate(rs.getString("oneday_cate"));
				oneday.setOnedayInfo(rs.getString("oneday_info"));
				oneday.setOnedayWriter(rs.getString("oneday_writer"));
				oneday.setOnedayContent(rs.getString("oneday_content"));
				oneday.setOnedayPrice(rs.getInt("oneday_price"));
				oneday.setOnedayOption1(rs.getString("oneday_option1"));
				oneday.setOnedayOption2(rs.getString("oneday_option2"));
				oneday.setOnedayOption3(rs.getString("oneday_option3"));
				oneday.setOnedayOption4(rs.getString("oneday_option4"));
				oneday.setOnedayOption5(rs.getString("oneday_option5"));
				oneday.setOnedayImage1(rs.getString("oneday_image1"));
				oneday.setOnedayImage2(rs.getString("oneday_image2"));
				oneday.setOnedayImage3(rs.getString("oneday_image3"));
				oneday.setOnedayImage4(rs.getString("oneday_image4"));
				oneday.setOnedayImage5(rs.getString("oneday_image5"));
				oneday.setOnedayPolicy(rs.getString("oneday_policy"));
				oneday.setOnedayDate(rs.getDate("oneday_date"));
				oneday.setOnedayPeople(rs.getInt("oneday_people"));
				oneday.setOnedayCurrentPeople(rs.getInt("oneday_current_people"));
				oneday.setOnedayReservationStatus(rs.getString("oneday_reservation_status"));
				oneday.setOnedayAddress(rs.getString("oneday_address"));
				list.add(oneday);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	
	// 원데이클래스 주문완료 시, 모집정원보다 크지 않으면 예약인원 1증가
	public int updateOrderOneday(Connection conn, int classNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateOrderOneday");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 예약가능한 인원 수 가져오기 (모집정원)
	public int selectMaxPeople(Connection conn, int classNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("selectMaxPeople");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	
	// 현재 예약된 인원수 가져오기
		public int selectCurrentPeople(Connection conn, int classNo) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			
			try {
				String sql = prop.getProperty("selectCurrentPeople");
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, classNo);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					result = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return result;
		}
	
	// 모집정원과 현재인원이 같다면 ONEDAY_STATUS = 'N' (예약불가) 로 바꾸기
	public int updateReservation(Connection conn, int classNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateReservation");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteOneday(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = prop.getProperty("deleteOneday");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
