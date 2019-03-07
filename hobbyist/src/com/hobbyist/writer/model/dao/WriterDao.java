package com.hobbyist.writer.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hobbyist.writer.model.vo.WriterEnroll;

public class WriterDao {
	
	private Properties prop = new Properties();
	
	public WriterDao() {
		String fileName = WriterDao.class.getResource("/sql/writer/writer.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int insertWriterEnroll(Connection conn, WriterEnroll we) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertWriterEnroll");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, we.getMemberNo());
			pstmt.setString(2, we.getMemberProfileImg());
			pstmt.setString(3, we.getMemberNickname());
			pstmt.setString(4, we.getMemberEmail());
			pstmt.setString(5, we.getMemberName());
			pstmt.setString(6, we.getMemberBirthday());
			pstmt.setString(7, we.getMemberPhone());
			pstmt.setString(8, we.getWriterEnrollQuarter());
			pstmt.setString(9, we.getWriterCategory());
			pstmt.setString(10, we.getWriterAddress());
			pstmt.setString(11, we.getWriterReason());
			pstmt.setString(12, we.getWriterMajorImgfileOriginal());
			pstmt.setString(13, we.getWriterMajorImgfileRenamed());
			pstmt.setString(14, we.getWriterScheduleYN());
			pstmt.setString(15, we.getWriterContractYN());
			pstmt.setString(16, we.getWriterWishMonth());
			pstmt.setString(17, we.getWriterClassName());
			pstmt.setString(18, we.getWriterClassSelectReason());
			pstmt.setInt(19, we.getWriterClassLevel());
			pstmt.setString(20, we.getWriterProductTime());
			pstmt.setString(21, we.getWriterClassKitWarningPoint());
			pstmt.setString(22, we.getWriterClassKitPart());
			pstmt.setString(23, we.getClassImgfileOriginal());
			pstmt.setString(24, we.getClassImgfileRenamed());
			pstmt.setString(25, we.getWriterPrepRequestYN());
			pstmt.setString(26, we.getWriterFinalPoint());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 검색결과에 다른 리스트 갯수 가져오기
	public int searchCount(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("searchCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 리스트 최근 등록일순으로 가져오기
	public List<WriterEnroll> descEnroll(Connection conn, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<WriterEnroll> list = new ArrayList();
		String sql = prop.getProperty("descEnroll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				WriterEnroll we = new WriterEnroll();
				we.setWriterEnrollNo(rs.getInt("writer_enroll_no"));
				we.setMemberNo(rs.getInt("member_no"));
				we.setMemberProfileImg(rs.getString("member_profile_img"));
				we.setMemberNickname(rs.getString("member_nickname"));
				we.setMemberEmail(rs.getString("member_email"));
				we.setMemberName(rs.getString("member_name"));
				we.setMemberBirthday(rs.getString("member_birthday"));
				we.setMemberPhone(rs.getString("member_phone"));
				we.setWriterEnrollQuarter(rs.getString("writer_enroll_quarter"));
				we.setWriterCategory(rs.getString("writer_category"));
				we.setWriterAddress(rs.getString("writer_address"));
				we.setWriterReason(rs.getString("writer_reason"));
				we.setWriterMajorImgfileOriginal(rs.getString("writer_major_imgfile_original"));
				we.setWriterMajorImgfileRenamed(rs.getString("writer_major_imgfile_renamed"));
				we.setWriterScheduleYN(rs.getString("writer_schedule_yn"));
				we.setWriterContractYN(rs.getString("writer_contract_yn"));
				we.setWriterWishMonth(rs.getString("writer_wish_month"));
				we.setWriterClassName(rs.getString("writer_class_name"));
				we.setWriterClassSelectReason(rs.getString("writer_class_select_reason"));
				we.setWriterClassLevel(rs.getInt("writer_class_level"));
				we.setWriterProductTime(rs.getString("writer_product_time"));
				we.setWriterClassKitWarningPoint(rs.getString("writer_class_kit_warning_point"));
				we.setWriterClassKitPart(rs.getString("writer_class_kit_part"));
				we.setClassImgfileOriginal(rs.getString("class_imgfile_original"));
				we.setClassImgfileRenamed(rs.getString("class_imgfile_renamed"));
				we.setWriterPrepRequestYN(rs.getString("writer_prep_request_yn"));
				we.setWriterFinalPoint(rs.getString("writer_final_point"));
				we.setWriterEnrolldate(rs.getDate("writer_enrolldate"));
				we.setWriterPassYN(rs.getString("writer_pass_yn"));
				we.setWriterStatus(rs.getString("writer_status"));
				list.add(we);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	// 리스트 최근 등록일순으로 가져오기
	public List<WriterEnroll> ascEnroll(Connection conn, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<WriterEnroll> list = new ArrayList();
		String sql = prop.getProperty("ascEnroll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				WriterEnroll we = new WriterEnroll();
				we.setWriterEnrollNo(rs.getInt("writer_enroll_no"));
				we.setMemberNo(rs.getInt("member_no"));
				we.setMemberProfileImg(rs.getString("member_profile_img"));
				we.setMemberNickname(rs.getString("member_nickname"));
				we.setMemberEmail(rs.getString("member_email"));
				we.setMemberName(rs.getString("member_name"));
				we.setMemberBirthday(rs.getString("member_birthday"));
				we.setMemberPhone(rs.getString("member_phone"));
				we.setWriterEnrollQuarter(rs.getString("writer_enroll_quarter"));
				we.setWriterCategory(rs.getString("writer_category"));
				we.setWriterAddress(rs.getString("writer_address"));
				we.setWriterReason(rs.getString("writer_reason"));
				we.setWriterMajorImgfileOriginal(rs.getString("writer_major_imgfile_original"));
				we.setWriterMajorImgfileRenamed(rs.getString("writer_major_imgfile_renamed"));
				we.setWriterScheduleYN(rs.getString("writer_schedule_yn"));
				we.setWriterContractYN(rs.getString("writer_contract_yn"));
				we.setWriterWishMonth(rs.getString("writer_wish_month"));
				we.setWriterClassName(rs.getString("writer_class_name"));
				we.setWriterClassSelectReason(rs.getString("writer_class_select_reason"));
				we.setWriterClassLevel(rs.getInt("writer_class_level"));
				we.setWriterProductTime(rs.getString("writer_product_time"));
				we.setWriterClassKitWarningPoint(rs.getString("writer_class_kit_warning_point"));
				we.setWriterClassKitPart(rs.getString("writer_class_kit_part"));
				we.setClassImgfileOriginal(rs.getString("class_imgfile_original"));
				we.setClassImgfileRenamed(rs.getString("class_imgfile_renamed"));
				we.setWriterPrepRequestYN(rs.getString("writer_prep_request_yn"));
				we.setWriterFinalPoint(rs.getString("writer_final_point"));
				we.setWriterEnrolldate(rs.getDate("writer_enrolldate"));
				we.setWriterPassYN(rs.getString("writer_pass_yn"));
				we.setWriterStatus(rs.getString("writer_status"));
				list.add(we);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	// (마이페이지)검색결과에 다른 리스트 갯수 가져오기
	public int myPageSearchCount(Connection conn, String keyword, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("myPageSearchCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, memberNo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// (마이페이지)리스트 최근 등록일순으로 가져오기
	public List<WriterEnroll> myPageDescEnroll(Connection conn, String keyword, int memberNo, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<WriterEnroll> list = new ArrayList();
		String sql = prop.getProperty("myPageDescEnroll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, memberNo);
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				WriterEnroll we = new WriterEnroll();
				we.setWriterEnrollNo(rs.getInt("writer_enroll_no"));
				we.setMemberNo(rs.getInt("member_no"));
				we.setMemberProfileImg(rs.getString("member_profile_img"));
				we.setMemberNickname(rs.getString("member_nickname"));
				we.setMemberEmail(rs.getString("member_email"));
				we.setMemberName(rs.getString("member_name"));
				we.setMemberBirthday(rs.getString("member_birthday"));
				we.setMemberPhone(rs.getString("member_phone"));
				we.setWriterEnrollQuarter(rs.getString("writer_enroll_quarter"));
				we.setWriterCategory(rs.getString("writer_category"));
				we.setWriterAddress(rs.getString("writer_address"));
				we.setWriterReason(rs.getString("writer_reason"));
				we.setWriterMajorImgfileOriginal(rs.getString("writer_major_imgfile_original"));
				we.setWriterMajorImgfileRenamed(rs.getString("writer_major_imgfile_renamed"));
				we.setWriterScheduleYN(rs.getString("writer_schedule_yn"));
				we.setWriterContractYN(rs.getString("writer_contract_yn"));
				we.setWriterWishMonth(rs.getString("writer_wish_month"));
				we.setWriterClassName(rs.getString("writer_class_name"));
				we.setWriterClassSelectReason(rs.getString("writer_class_select_reason"));
				we.setWriterClassLevel(rs.getInt("writer_class_level"));
				we.setWriterProductTime(rs.getString("writer_product_time"));
				we.setWriterClassKitWarningPoint(rs.getString("writer_class_kit_warning_point"));
				we.setWriterClassKitPart(rs.getString("writer_class_kit_part"));
				we.setClassImgfileOriginal(rs.getString("class_imgfile_original"));
				we.setClassImgfileRenamed(rs.getString("class_imgfile_renamed"));
				we.setWriterPrepRequestYN(rs.getString("writer_prep_request_yn"));
				we.setWriterFinalPoint(rs.getString("writer_final_point"));
				we.setWriterEnrolldate(rs.getDate("writer_enrolldate"));
				we.setWriterPassYN(rs.getString("writer_pass_yn"));
				we.setWriterStatus(rs.getString("writer_status"));
				list.add(we);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	// (마이페이지)리스트 최근 등록일순으로 가져오기
	public List<WriterEnroll> myPageAscEnroll(Connection conn, String keyword, int memberNo, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<WriterEnroll> list = new ArrayList();
		String sql = prop.getProperty("myPageAscEnroll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, memberNo);
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				WriterEnroll we = new WriterEnroll();
				we.setWriterEnrollNo(rs.getInt("writer_enroll_no"));
				we.setMemberNo(rs.getInt("member_no"));
				we.setMemberProfileImg(rs.getString("member_profile_img"));
				we.setMemberNickname(rs.getString("member_nickname"));
				we.setMemberEmail(rs.getString("member_email"));
				we.setMemberName(rs.getString("member_name"));
				we.setMemberBirthday(rs.getString("member_birthday"));
				we.setMemberPhone(rs.getString("member_phone"));
				we.setWriterEnrollQuarter(rs.getString("writer_enroll_quarter"));
				we.setWriterCategory(rs.getString("writer_category"));
				we.setWriterAddress(rs.getString("writer_address"));
				we.setWriterReason(rs.getString("writer_reason"));
				we.setWriterMajorImgfileOriginal(rs.getString("writer_major_imgfile_original"));
				we.setWriterMajorImgfileRenamed(rs.getString("writer_major_imgfile_renamed"));
				we.setWriterScheduleYN(rs.getString("writer_schedule_yn"));
				we.setWriterContractYN(rs.getString("writer_contract_yn"));
				we.setWriterWishMonth(rs.getString("writer_wish_month"));
				we.setWriterClassName(rs.getString("writer_class_name"));
				we.setWriterClassSelectReason(rs.getString("writer_class_select_reason"));
				we.setWriterClassLevel(rs.getInt("writer_class_level"));
				we.setWriterProductTime(rs.getString("writer_product_time"));
				we.setWriterClassKitWarningPoint(rs.getString("writer_class_kit_warning_point"));
				we.setWriterClassKitPart(rs.getString("writer_class_kit_part"));
				we.setClassImgfileOriginal(rs.getString("class_imgfile_original"));
				we.setClassImgfileRenamed(rs.getString("class_imgfile_renamed"));
				we.setWriterPrepRequestYN(rs.getString("writer_prep_request_yn"));
				we.setWriterFinalPoint(rs.getString("writer_final_point"));
				we.setWriterEnrolldate(rs.getDate("writer_enrolldate"));
				we.setWriterPassYN(rs.getString("writer_pass_yn"));
				we.setWriterStatus(rs.getString("writer_status"));
				list.add(we);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<WriterEnroll> selectAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectAll");
		List<WriterEnroll> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				WriterEnroll we = new WriterEnroll();
				we.setWriterEnrollNo(rs.getInt("writer_enroll_no"));
				we.setMemberNo(rs.getInt("member_no"));
				we.setMemberProfileImg(rs.getString("member_profile_img"));
				we.setMemberNickname(rs.getString("member_nickname"));
				we.setMemberEmail(rs.getString("member_email"));
				we.setMemberName(rs.getString("member_name"));
				we.setMemberBirthday(rs.getString("member_birthday"));
				we.setMemberPhone(rs.getString("member_phone"));
				we.setWriterEnrollQuarter(rs.getString("writer_enroll_quarter"));
				we.setWriterCategory(rs.getString("writer_category"));
				we.setWriterAddress(rs.getString("writer_address"));
				we.setWriterReason(rs.getString("writer_reason"));
				we.setWriterMajorImgfileOriginal(rs.getString("writer_major_imgfile_original"));
				we.setWriterMajorImgfileRenamed(rs.getString("writer_major_imgfile_renamed"));
				we.setWriterScheduleYN(rs.getString("writer_schedule_yn"));
				we.setWriterContractYN(rs.getString("writer_contract_yn"));
				we.setWriterWishMonth(rs.getString("writer_wish_month"));
				we.setWriterClassName(rs.getString("writer_class_name"));
				we.setWriterClassSelectReason(rs.getString("writer_class_select_reason"));
				we.setWriterClassLevel(rs.getInt("writer_class_level"));
				we.setWriterProductTime(rs.getString("writer_product_time"));
				we.setWriterClassKitWarningPoint(rs.getString("writer_class_kit_warning_point"));
				we.setWriterClassKitPart(rs.getString("writer_class_kit_part"));
				we.setClassImgfileOriginal(rs.getString("class_imgfile_original"));
				we.setClassImgfileRenamed(rs.getString("class_imgfile_renamed"));
				we.setWriterPrepRequestYN(rs.getString("writer_prep_request_yn"));
				we.setWriterFinalPoint(rs.getString("writer_final_point"));
				we.setWriterEnrolldate(rs.getDate("writer_enrolldate"));
				we.setWriterPassYN(rs.getString("writer_pass_yn"));
				we.setWriterStatus(rs.getString("writer_status"));
				list.add(we);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public WriterEnroll selectOne(Connection conn, WriterEnroll we) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String spl = prop.getProperty("selectOne");
		WriterEnroll result = null;
		try {
			pstmt = conn.prepareStatement(spl);
			pstmt.setInt(1, we.getWriterEnrollNo());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new WriterEnroll();
				result.setWriterEnrollNo(rs.getInt("writer_enroll_no"));
				result.setMemberNo(rs.getInt("member_no"));
				result.setMemberProfileImg(rs.getString("member_profile_img"));
				result.setMemberNickname(rs.getString("member_nickname"));
				result.setMemberEmail(rs.getString("member_email"));
				result.setMemberName(rs.getString("member_name"));
				result.setMemberBirthday(rs.getString("member_birthday"));
				result.setMemberPhone(rs.getString("member_phone"));
				result.setWriterEnrollQuarter(rs.getString("writer_enroll_quarter"));
				result.setWriterCategory(rs.getString("writer_category"));
				result.setWriterAddress(rs.getString("writer_address"));
				result.setWriterReason(rs.getString("writer_reason"));
				result.setWriterMajorImgfileOriginal(rs.getString("writer_major_imgfile_original"));
				result.setWriterMajorImgfileRenamed(rs.getString("writer_major_imgfile_renamed"));
				result.setWriterScheduleYN(rs.getString("writer_schedule_yn"));
				result.setWriterContractYN(rs.getString("writer_contract_yn"));
				result.setWriterWishMonth(rs.getString("writer_wish_month"));
				result.setWriterClassName(rs.getString("writer_class_name"));
				result.setWriterClassSelectReason(rs.getString("writer_class_select_reason"));
				result.setWriterClassLevel(rs.getInt("writer_class_level"));
				result.setWriterProductTime(rs.getString("writer_product_time"));
				result.setWriterClassKitWarningPoint(rs.getString("writer_class_kit_warning_point"));
				result.setWriterClassKitPart(rs.getString("writer_class_kit_part"));
				result.setClassImgfileOriginal(rs.getString("class_imgfile_original"));
				result.setClassImgfileRenamed(rs.getString("class_imgfile_renamed"));
				result.setWriterPrepRequestYN(rs.getString("writer_prep_request_yn"));
				result.setWriterFinalPoint(rs.getString("writer_final_point"));
				result.setWriterEnrolldate(rs.getDate("writer_enrolldate"));
				result.setWriterPassYN(rs.getString("writer_pass_yn"));
				result.setWriterStatus(rs.getString("writer_status"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int passWriterEnroll(Connection conn, int writerEnrollNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("passWriterEnroll");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, writerEnrollNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int failWriterEnroll(Connection conn, int writerEnrollNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("failWriterEnroll");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, writerEnrollNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int returnWriterEnroll(Connection conn, int writerEnrollNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("returnWriterEnroll");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, writerEnrollNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int UpdateWriterEnroll(Connection conn, WriterEnroll we) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("UpdateWriterEnroll");
		System.out.println(we.toString());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, we.getMemberNo());
			pstmt.setString(2, we.getMemberProfileImg());
			pstmt.setString(3, we.getMemberNickname());
			pstmt.setString(4, we.getMemberEmail());
			pstmt.setString(5, we.getMemberName());
			pstmt.setString(6, we.getMemberBirthday());
			pstmt.setString(7, we.getMemberPhone());
			pstmt.setString(8, we.getWriterEnrollQuarter());
			pstmt.setString(9, we.getWriterCategory());
			pstmt.setString(10, we.getWriterAddress());
			pstmt.setString(11, we.getWriterReason());
			pstmt.setString(12, we.getWriterMajorImgfileOriginal());
			pstmt.setString(13, we.getWriterMajorImgfileRenamed());
			pstmt.setString(14, we.getWriterScheduleYN());
			pstmt.setString(15, we.getWriterContractYN());
			pstmt.setString(16, we.getWriterWishMonth());
			pstmt.setString(17, we.getWriterClassName());
			pstmt.setString(18, we.getWriterClassSelectReason());
			pstmt.setInt(19, we.getWriterClassLevel());
			pstmt.setString(20, we.getWriterProductTime());
			pstmt.setString(21, we.getWriterClassKitWarningPoint());
			pstmt.setString(22, we.getWriterClassKitPart());
			pstmt.setString(23, we.getClassImgfileOriginal());
			pstmt.setString(24, we.getClassImgfileRenamed());
			pstmt.setString(25, we.getWriterPrepRequestYN());
			pstmt.setString(26, we.getWriterFinalPoint());
			pstmt.setInt(27, we.getWriterEnrollNo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	//(마이페이지) 예비작가 신청 수락 여부
	public int prepRequestYN(Connection conn, int writerEnrollNo, String writerPrepRequestYN) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("prepRequestYN");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writerPrepRequestYN);
			pstmt.setInt(2, writerEnrollNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<WriterEnroll> selectQuarter(Connection conn, String weQuarter) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectQuarter");
		List<WriterEnroll> result = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, weQuarter);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				WriterEnroll we = new WriterEnroll();
				we.setWriterEnrollNo(rs.getInt("writer_enroll_no"));
				we.setMemberNo(rs.getInt("member_no"));
				we.setMemberProfileImg(rs.getString("member_profile_img"));
				we.setMemberNickname(rs.getString("member_nickname"));
				we.setMemberEmail(rs.getString("member_email"));
				we.setMemberName(rs.getString("member_name"));
				we.setMemberBirthday(rs.getString("member_birthday"));
				we.setMemberPhone(rs.getString("member_phone"));
				we.setWriterEnrollQuarter(rs.getString("writer_enroll_quarter"));
				we.setWriterCategory(rs.getString("writer_category"));
				we.setWriterAddress(rs.getString("writer_address"));
				we.setWriterReason(rs.getString("writer_reason"));
				we.setWriterMajorImgfileOriginal(rs.getString("writer_major_imgfile_original"));
				we.setWriterMajorImgfileRenamed(rs.getString("writer_major_imgfile_renamed"));
				we.setWriterScheduleYN(rs.getString("writer_schedule_yn"));
				we.setWriterContractYN(rs.getString("writer_contract_yn"));
				we.setWriterWishMonth(rs.getString("writer_wish_month"));
				we.setWriterClassName(rs.getString("writer_class_name"));
				we.setWriterClassSelectReason(rs.getString("writer_class_select_reason"));
				we.setWriterClassLevel(rs.getInt("writer_class_level"));
				we.setWriterProductTime(rs.getString("writer_product_time"));
				we.setWriterClassKitWarningPoint(rs.getString("writer_class_kit_warning_point"));
				we.setWriterClassKitPart(rs.getString("writer_class_kit_part"));
				we.setClassImgfileOriginal(rs.getString("class_imgfile_original"));
				we.setClassImgfileRenamed(rs.getString("class_imgfile_renamed"));
				we.setWriterPrepRequestYN(rs.getString("writer_prep_request_yn"));
				we.setWriterFinalPoint(rs.getString("writer_final_point"));
				we.setWriterEnrolldate(rs.getDate("writer_enrolldate"));
				we.setWriterPassYN(rs.getString("writer_pass_yn"));
				we.setWriterStatus(rs.getString("writer_status"));
				result.add(we);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
}
