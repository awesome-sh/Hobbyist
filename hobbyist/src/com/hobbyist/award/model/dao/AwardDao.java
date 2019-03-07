package com.hobbyist.award.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hobbyist.award.model.vo.Award;
import com.hobbyist.award.model.vo.AwardComment;
import com.hobbyist.board.model.vo.Board;
import com.sun.corba.se.impl.ior.GenericTaggedComponent;

public class AwardDao {

	private Properties prop = new Properties();

	public AwardDao() {
		String fileName = AwardDao.class.getResource("/sql/award/award-query.properties").getPath();
		try {

			prop.load(new FileReader(fileName));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Award> selectList(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = prop.getProperty("selectList");
		List<Award> list = new ArrayList<Award>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage - 1) * numPerPage + 1);
			pstmt.setInt(2, cPage * numPerPage);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Award a = new Award();
				a.setAwardNo(rs.getInt("AWARD_NO"));
				a.setAwardName(rs.getString("AWARD_NAME"));
				a.setLikeCount(rs.getInt("AWARD_LIKECOUNT"));
				a.setAwardWriter(rs.getString("AWARD_WRITER"));
				a.setAwardContent(rs.getString("AWARD_CONTENT"));
				a.setReadCount(rs.getInt("AWARD_READCOUNT"));			
				a.setAwardImage1(rs.getString("AWARD_IMAGE1"));
				a.setAwardImage2(rs.getString("AWARD_IMAGE2"));
				a.setAwardImage3(rs.getString("AWARD_IMAGE3"));
				a.setAwardImage4(rs.getString("AWARD_IMAGE4"));
				a.setAwardImage5(rs.getString("AWARD_IMAGE5"));
				a.setAwardDate(rs.getDate("AWARD_DATE"));
				a.setAwardStatus(rs.getString("AWARD_STATUS"));
				a.setAwardOriginalFilename(rs.getString("AWARD_ORIGINALFILENAME"));
				a.setAwardRenamedFilename(rs.getString("AWARD_RENAMEDFILENAME"));
				list.add(a);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}


	public Award selectOne(Connection conn, int awardNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Award a = null;
		String sql = prop.getProperty("selectOne");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, awardNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				a = new Award();
				a.setAwardNo(rs.getInt("AWARD_NO"));
				a.setAwardName(rs.getString("AWARD_NAME"));
				a.setLikeCount(rs.getInt("AWARD_LIKECOUNT"));
				a.setAwardWriter(rs.getString("AWARD_WRITER"));
				a.setAwardContent(rs.getString("AWARD_CONTENT"));
				a.setReadCount(rs.getInt("AWARD_READCOUNT"));			
				a.setAwardImage1(rs.getString("AWARD_IMAGE1"));
				a.setAwardImage2(rs.getString("AWARD_IMAGE2"));
				a.setAwardImage3(rs.getString("AWARD_IMAGE3"));
				a.setAwardImage4(rs.getString("AWARD_IMAGE4"));
				a.setAwardImage5(rs.getString("AWARD_IMAGE5"));
				a.setAwardDate(rs.getDate("AWARD_DATE"));
				a.setAwardStatus(rs.getString("AWARD_STATUS"));
				a.setAwardOriginalFilename(rs.getString("AWARD_ORIGINALFILENAME"));
				a.setAwardRenamedFilename(rs.getString("AWARD_RENAMEDFILENAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return a;
	}

	
	public int selectCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		String sql = prop.getProperty("selectCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, "Y");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
		
	}

	public int increReadCount(Connection conn, int awardNo) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		String sql = prop.getProperty("increReadCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, awardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectSeq(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("selectSeq");
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
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

	public int insertAward(Connection conn, Award a) {
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("insertAward");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getAwardName());
			pstmt.setInt(2, a.getLikeCount());
			pstmt.setString(3, a.getAwardWriter());
			pstmt.setString(4, a.getAwardContent());
			pstmt.setInt(5, a.getReadCount());
			pstmt.setString(6, a.getAwardImage1());
			pstmt.setString(7, a.getAwardImage2());
			pstmt.setString(8, a.getAwardImage3());
			pstmt.setString(9, a.getAwardImage4());
			pstmt.setString(10, a.getAwardImage5());

			pstmt.setString(11, a.getAwardOriginalFilename());
			pstmt.setString(12, a.getAwardRenamedFilename());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int insertComment(Connection conn, AwardComment comment) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("insertComment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getAwardCommentLevel());
			pstmt.setString(2, comment.getAwardCommentWriter());
			pstmt.setString(3, comment.getAwardCommentContent());
			pstmt.setInt(4, comment.getAwardRef());
			pstmt.setInt(5, comment.getAwardCommentRef());

			// 0이면 없다는 거고 null값:참조번호
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int deleteAward(Connection conn, Award a) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteAward");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getAwardNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateAward(Connection conn, Award a) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateAward");
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, a.getAwardName());
			pstmt.setString(2, a.getAwardContent());
			pstmt.setString(3, a.getAwardOriginalFilename());
			pstmt.setString(4, a.getAwardRenamedFilename());
			pstmt.setInt(5, a.getAwardNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			close(pstmt);
		}
		return result;
	}

	public List<AwardComment> selectCountAll(Connection conn, int awardNo) {
		List<AwardComment> list = new ArrayList<AwardComment>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = prop.getProperty("selectCommentAll");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, awardNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AwardComment ac = new AwardComment();
				ac.setAwardCommentNo(rs.getInt("award_comment_no"));
				ac.setAwardCommentLevel(rs.getInt("award_comment_level"));
				ac.setAwardCommentWriter(rs.getString("award_comment_writer"));
				ac.setAwardCommentContent(rs.getString("award_comment_content"));
				ac.setAwardRef(rs.getInt("award_ref"));
				ac.setAwardCommentRef(rs.getInt("award_comment_ref"));
				ac.setAwardCommentDate(rs.getDate("award_comment_date"));
				list.add(ac);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int deleteComment(Connection conn, int deleteNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteComment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deleteNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}

	public String selectLikeUsers(Connection conn, int awardNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectLikeUsers");
		String users = "";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, awardNo);
			rs = pstmt.executeQuery();
			
			// 만약 리스트가 있다면 users 값을 String user에 담는다
			if (rs.next()) {
				users = rs.getString("USERS");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return users;
	}

	public int increLikeCountsub(Connection conn, int awardNo) {

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increLikeCountSub");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, awardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int increLikeCountAdd(Connection conn, int awardNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increLikeCountAdd");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, awardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int increLikeUsers(Connection conn, int awardNo, String users) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increLikeUsers");
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, users);
			pstmt.setInt(2, awardNo);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}

	
	public List<Award> selectSearchList(Connection conn, int cPage, int numPerPage, String searchType, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = "";
		//String sql2 = "";
		List<Award> list = new ArrayList<Award>();
		if(searchType.equals("newUp")) {
			sql1 = prop.getProperty("newUpList");
			//sql2 = prop.getProperty("newUpListCommentCount");
		}
		else if(searchType.equals("viewsUp")) {
			sql1 = prop.getProperty("viewsUpList");
			//sql2 = prop.getProperty("viewsUpListCommentCount");
		}
		else if(searchType.equals("LikeUp")) {
			sql1 = prop.getProperty("LikeUpList");
			//sql2 = prop.getProperty("LikeUpListCommentCount");
		}
		try {
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, "%" + searchKeyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rs = pstmt.executeQuery();
			
		
		while(rs.next()) {
			Award a=new Award();
			a.setAwardNo(rs.getInt("AWARD_NO"));
			a.setAwardName(rs.getString("AWARD_NAME"));
			a.setLikeCount(rs.getInt("AWARD_LIKECOUNT"));
			a.setAwardWriter(rs.getString("AWARD_WRITER"));
			a.setAwardContent(rs.getString("AWARD_CONTENT"));
			a.setReadCount(rs.getInt("AWARD_READCOUNT"));			
			a.setAwardImage1(rs.getString("AWARD_IMAGE1"));
			a.setAwardImage2(rs.getString("AWARD_IMAGE2"));
			a.setAwardImage3(rs.getString("AWARD_IMAGE3"));
			a.setAwardImage4(rs.getString("AWARD_IMAGE4"));
			a.setAwardImage5(rs.getString("AWARD_IMAGE5"));
			a.setAwardDate(rs.getDate("AWARD_DATE"));
			a.setAwardStatus(rs.getString("AWARD_STATUS"));
			a.setAwardOriginalFilename(rs.getString("AWARD_ORIGINALFILENAME"));
			a.setAwardRenamedFilename(rs.getString("AWARD_RENAMEDFILENAME"));
			list.add(a);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int selectSearchCount(Connection conn, String searchType, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int totalCount = 0;
		
		if(searchType.equals("newUp")) sql = prop.getProperty("newUpCount");
		else if(searchType.equals("viewsUp")) sql = prop.getProperty("viewsUpCount");
		else if(searchType.equals("LikeUp")) sql = prop.getProperty("LikeUpCount");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "Y");
			pstmt.setString(2, "%" + searchKeyword + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return totalCount;
	}
		
	}
	
	


