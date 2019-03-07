package com.hobbyist.board.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hobbyist.board.model.vo.Board;
import com.hobbyist.board.model.vo.BoardComment;
import com.hobbyist.board.model.vo.BoardDQ;
import com.hobbyist.board.model.vo.BoardFAQ;
import com.hobbyist.board.model.vo.BoardReport;

public class BoardDao {
	
	private Properties prop = new Properties();
	
	public BoardDao() {
		
		String fileName = BoardDao.class.getResource("/sql/board/board-query.properties").getPath();
		try {
			
			prop.load(new FileReader(fileName));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Board> selectAll(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectAll");
		List<Board> list = new ArrayList();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				Board b = new Board();
				
				b.setBoardNo(rs.getInt("BOARD_NO"));
				b.setBoardTitle(rs.getString("BOARD_TITLE"));
				b.setBoardWriter(rs.getString("BOARD_WRITER"));
				b.setBoardContent(rs.getString("BOARD_CONTENT"));
				b.setBoardOriginalFileName(rs.getString("BOARD_ORIGINAL_FILENAME"));
				b.setBoardDate(rs.getDate("BOARD_DATE"));
				b.setBoardReadCount(rs.getInt("BOARD_READCOUNT"));
				b.setStatus(rs.getString("STATUS"));
				b.setBoardLike(rs.getInt("BOARD_LIKE"));
				
				list.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public Board selectOne(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectOne");
		Board b = null;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				b = new Board();
				
				b.setBoardNo(boardNo);
				b.setBoardTitle(rs.getString("BOARD_TITLE"));
				b.setBoardWriter(rs.getString("BOARD_WRITER"));
				b.setBoardContent(rs.getString("BOARD_CONTENT"));
				b.setBoardReNamedFileName(rs.getString("BOARD_RENAMED_FILENAME"));
				b.setBoardOriginalFileName(rs.getString("BOARD_ORIGINAL_FILENAME"));
				b.setBoardDate(rs.getDate("BOARD_DATE"));
				b.setBoardReadCount(rs.getInt("BOARD_READCOUNT"));
				b.setStatus(rs.getString("STATUS"));
				b.setBoardLike(rs.getInt("BOARD_LIKE"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return b;
	}
	
	public BoardDQ selectDQOne(Connection conn, int boardDQNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectDQOne");
		BoardDQ b = null;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardDQNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				b = new BoardDQ();
				
				b.setBoardDQNo(boardDQNo);
				b.setBoardDQTitle(rs.getString("BOARD_DQ_TITLE"));
				b.setBoardDQWriter(rs.getString("BOARD_DQ_WRITER"));
				b.setBoardDQContent(rs.getString("BOARD_DQ_CONTENT"));
				b.setBoardDQOriginalFileName(rs.getString("BOARD_DQ_ORIGINAL_FILENAME"));
				b.setBoardDQReNameFileName(rs.getString("BOARD_DQ_RENAMED_FILENAME"));
				b.setBoardDQDate(rs.getDate("BOARD_DQ_DATE"));
				b.setBoardDQProcess(rs.getString("BOARD_DQ_PROCESS"));
				b.setBoardDQAnswer(rs.getString("BOARD_DQ_ANSWER"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return b;
	}
	
	public List<Board> selectList(Connection conn, int cPage, int numPerPage) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = prop.getProperty("selectList");
		String sql2 = prop.getProperty("selectListCommentCount");
		List<Board> list = new ArrayList<Board>();
		
		try {
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Board b = new Board();
				
				b.setBoardNo(rs.getInt("BOARD_NO"));
				b.setBoardTitle(rs.getString("BOARD_TITLE"));
				b.setBoardWriter(rs.getString("BOARD_WRITER"));
				b.setBoardContent(rs.getString("BOARD_CONTENT"));
				b.setBoardOriginalFileName(rs.getString("BOARD_ORIGINAL_FILENAME"));
				b.setBoardReNamedFileName(rs.getString("BOARD_RENAMED_FILENAME"));
				b.setBoardDate(rs.getDate("BOARD_DATE"));
				b.setBoardReadCount(rs.getInt("BOARD_READCOUNT"));
				b.setStatus(rs.getString("STATUS"));
				b.setBoardLike(rs.getInt("BOARD_LIKE"));
				
				list.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pstmt = null;
		rs = null;
		
		try {
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			for(Board b : list) {
				rs.next();
				b.setBoardCommentCount(rs.getInt("COMMENTCOUNT"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
public List<BoardFAQ> selectFAQList(Connection conn, int cPage, int numPerPage) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectFAQList");
		List<BoardFAQ> list = new ArrayList();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardFAQ b = new BoardFAQ();
				
				b.setBoardFAQNo(rs.getInt("BOARD_FAQ_NO"));
				b.setBoardFAQCategory(rs.getString("BOARD_FAQ_CATEGORY"));
				b.setBoardFAQTitle(rs.getString("BOARD_FAQ_TITLE"));
				b.setBoardFAQContent(rs.getString("BOARD_FAQ_CONTENT"));
				b.setBoardFAQDate(rs.getDate("BOARD_FAQ_DATE"));
				
				list.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public List<BoardFAQ> selectFAQSearchList(Connection conn, int cPage, int numPerPage, String searchType, String searchKeyword) {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectFAQSearchList");
		List<BoardFAQ> list = new ArrayList();
		
		switch (searchType) {
			case "all": searchType = ""; break;
			case "changeAndCancel": searchType = "변경/취소"; break;
			case "depositAndDelivery": searchType = "입금/배송"; break;
			case "exchangeAndReturn": searchType = "교환/반품"; break;
			case "therInquiries": searchType = "기타문의"; break;
			case "saleAndBenefits": searchType = "할인/혜택"; break;
			case "memberAndBenefits": searchType = "회원/혜택"; break;
			case "orderAndLookup": searchType = "주문/조회"; break;
			case "paymentInquiry": searchType = "결제문의"; break;
			case "soldOutAndWearing": searchType = "품절/입고"; break;
			case "contactUs": searchType = "상품문의"; break;
		}
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchType + "%");
			pstmt.setString(2, "%" + searchKeyword + "%");
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardFAQ b = new BoardFAQ();
				
				b.setBoardFAQNo(rs.getInt("BOARD_FAQ_NO"));
				b.setBoardFAQCategory(rs.getString("BOARD_FAQ_CATEGORY"));
				b.setBoardFAQTitle(rs.getString("BOARD_FAQ_TITLE"));
				b.setBoardFAQContent(rs.getString("BOARD_FAQ_CONTENT"));
				b.setBoardFAQDate(rs.getDate("BOARD_FAQ_DATE"));
				
				list.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public List<BoardDQ> selectDQList(Connection conn, int cPage, int numPerPage, String nickName) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectDQList");
		List<BoardDQ> list = new ArrayList();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickName);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardDQ b = new BoardDQ();
				
				b.setBoardDQNo(rs.getInt("BOARD_DQ_NO"));
				b.setBoardDQTitle(rs.getString("BOARD_DQ_TITLE"));
				b.setBoardDQWriter(rs.getString("BOARD_DQ_WRITER"));
				b.setBoardDQContent(rs.getString("BOARD_DQ_CONTENT"));
				b.setBoardDQOriginalFileName(rs.getString("BOARD_DQ_ORIGINAL_FILENAME"));
				b.setBoardDQReNameFileName(rs.getString("BOARD_DQ_RENAMED_FILENAME"));
				b.setBoardDQDate(rs.getDate("BOARD_DQ_DATE"));
				b.setBoardDQProcess(rs.getString("BOARD_DQ_PROCESS"));
				
				list.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<Board> selectSearchList(Connection conn, int cPage, int numPerPage, String searchType, String searchKeyword) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = "";
		String sql2 = "";
		List<Board> list = new ArrayList<Board>();
		if(searchType.equals("newUp")) {
			sql1 = prop.getProperty("newUpList");
			sql2 = prop.getProperty("newUpListCommentCount");
		}
		else if(searchType.equals("viewsUp")) {
			sql1 = prop.getProperty("viewsUpList");
			sql2 = prop.getProperty("viewsUpListCommentCount");
		}
		else if(searchType.equals("LikeUp")) {
			sql1 = prop.getProperty("LikeUpList");
			sql2 = prop.getProperty("LikeUpListCommentCount");
		}
		try {
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, "%" + searchKeyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Board b = new Board();
				
				b.setBoardNo(rs.getInt("BOARD_NO"));
				b.setBoardTitle(rs.getString("BOARD_TITLE"));
				b.setBoardWriter(rs.getString("BOARD_WRITER"));
				b.setBoardContent(rs.getString("BOARD_CONTENT"));
				b.setBoardOriginalFileName(rs.getString("BOARD_ORIGINAL_FILENAME"));
				b.setBoardReNamedFileName(rs.getString("BOARD_RENAMED_FILENAME"));
				b.setBoardDate(rs.getDate("BOARD_DATE"));
				b.setBoardReadCount(rs.getInt("BOARD_READCOUNT"));
				b.setStatus(rs.getString("STATUS"));
				b.setBoardLike(rs.getInt("BOARD_LIKE"));
				
				list.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pstmt = null;
		rs = null;
		
		try {
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, "%" + searchKeyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rs = pstmt.executeQuery();
			
			for(Board b : list) {
				rs.next();
				b.setBoardCommentCount(rs.getInt("COMMENTCOUNT"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public int selectCount(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectCount");
		int totalCount = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "Y");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("CNT");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return totalCount;
	}
	
	public int selectFAQCount(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectFAQCount");
		int totalCount = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("CNT");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return totalCount;
	}
	
	public int selectFAQSearchCount(Connection conn, String searchType, String searchKeyword) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectFAQSearchCount");
		int totalCount = 0;
		
		switch (searchType) {
			case "all": searchType = ""; break;
			case "changeAndCancel": searchType = "변경/취소"; break;
			case "depositAndDelivery": searchType = "입금/배송"; break;
			case "exchangeAndReturn": searchType = "교환/반품"; break;
			case "therInquiries": searchType = "기타문의"; break;
			case "saleAndBenefits": searchType = "할인/혜택"; break;
			case "memberAndBenefits": searchType = "회원/혜택"; break;
			case "orderAndLookup": searchType = "주문/조회"; break;
			case "paymentInquiry": searchType = "결제문의"; break;
			case "soldOutAndWearing": searchType = "품절/입고"; break;
			case "contactUs": searchType = "상품문의"; break;
		}
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchType + "%");
			pstmt.setString(2, "%" + searchKeyword + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("CNT");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return totalCount;
	}
	
	public int selectDQCount(Connection conn, String nickName) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectDQCount");
		int totalCount = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("CNT");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return totalCount;
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
				totalCount = rs.getInt("CNT");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return totalCount;
	}
	
	public List<BoardComment> selectCommentAll(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectCommentAll");
		List<BoardComment> bcList = new ArrayList();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardComment bc = new BoardComment();
				
				bc.setBoardCommentNo(rs.getInt("BOARD_COMMENT_NO"));
				bc.setBoardCommentLevel(rs.getInt("BOARD_COMMENT_LEVEL"));
				bc.setBoardCommentWriter(rs.getString("BOARD_COMMENT_WRITER"));
				bc.setBoardCommentContent(rs.getString("BOARD_COMMENT_CONTENT"));
				bc.setBoardRef(rs.getInt("BOARD_REF"));
				bc.setBoardCommentRef(rs.getInt("BOARD_COMMENT_REF"));
				bc.setBoardCommentDate(rs.getDate("BOARD_COMMENT_DATE"));
				
				bcList.add(bc);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return bcList;
	}
	
	public int insertBoard(Connection conn, Board b) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertBoard");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardOriginalFileName());
			pstmt.setString(5, b.getBoardReNamedFileName());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertDQBoard(Connection conn, BoardDQ b) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertDQBoard");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardDQTitle());
			pstmt.setString(2, b.getBoardDQWriter());
			pstmt.setString(3, b.getBoardDQContent());
			pstmt.setString(4, b.getBoardDQOriginalFileName());
			pstmt.setString(5, b.getBoardDQReNameFileName());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertComment(Connection conn, BoardComment bc) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertComment");
		int result = 0;

		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bc.getBoardCommentLevel());
			pstmt.setString(2, bc.getBoardCommentWriter());
			pstmt.setString(3, bc.getBoardCommentContent());
			pstmt.setInt(4, bc.getBoardRef());
			if(bc.getBoardCommentRef() != 0) pstmt.setInt(5, bc.getBoardCommentRef());
			else pstmt.setString(5, null);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertBoardReport(Connection conn, BoardReport b) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBoardReport");
		int result = 0;
		
		try {
			System.out.println(b);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getBoardNo());
			pstmt.setString(2, b.getBoardMainCategory());
			pstmt.setString(3, b.getBoardWriter());
			pstmt.setString(4, b.getBoardReporter());
			pstmt.setString(5, b.getBoardContent());
			pstmt.setString(6, b.getBoardReportContent());
			pstmt.setString(7, b.getBoardReportCategory());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateReadCount(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReadCount");
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int selectNextOne(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectNextOne");
		int moveNo = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				moveNo = rs.getInt("NEXT");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return moveNo;
	}
	
	public int selectPrevOne(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectPrevOne");
		int moveNo = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				moveNo = rs.getInt("PREV");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return moveNo;
	}
	
	public int updateBoard(Connection conn, Board b) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBoard");
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getBoardOriginalFileName());
			pstmt.setString(4, b.getBoardReNamedFileName());
			pstmt.setInt(5, b.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateDQBoard(Connection conn, BoardDQ b) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateDQBoard");
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardDQTitle());
			pstmt.setString(2, b.getBoardDQContent());
			pstmt.setString(3, b.getBoardDQOriginalFileName());
			pstmt.setString(4, b.getBoardDQReNameFileName());
			pstmt.setInt(5, b.getBoardDQNo());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateDQAnswerBoard(Connection conn, int BoardDQNo, String boardDQContent) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateDQAnswerBoard");
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDQContent);
			pstmt.setInt(2, BoardDQNo);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateLikeCountAdd(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateLikeCountAdd");
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateLikeCountSub(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateLikeCountSub");
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateLikeUsers(Connection conn, int boardNo, String users) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateLikeUsers");
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, users);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteBoard(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoard");
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteDQBoard(Connection conn, int boardDQNo) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteDQBoard");
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardDQNo);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteComment(Connection conn, int delNo) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteComment");
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, delNo);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public String selectLikeUsers(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectLikeUsers");
		String users = "";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				users = rs.getString("USERS");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return users;
	}
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	------------------------------------------------------------admin
	
	public int selectAdminCount(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectAdminCount");
		int totalCount = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("CNT");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return totalCount;
	}
	
	public List<Board> selectAdminList(Connection conn, int cPage, int numPerPage) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = prop.getProperty("selectAdminList");
		String sql2 = prop.getProperty("selectAdminListCommentCount");
		List<Board> list = new ArrayList<Board>();
		
		try {
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Board b = new Board();
				
				b.setBoardNo(rs.getInt("BOARD_NO"));
				b.setBoardTitle(rs.getString("BOARD_TITLE"));
				b.setBoardWriter(rs.getString("BOARD_WRITER"));
				b.setBoardContent(rs.getString("BOARD_CONTENT"));
				b.setBoardOriginalFileName(rs.getString("BOARD_ORIGINAL_FILENAME"));
				b.setBoardReNamedFileName(rs.getString("BOARD_RENAMED_FILENAME"));
				b.setBoardDate(rs.getDate("BOARD_DATE"));
				b.setBoardReadCount(rs.getInt("BOARD_READCOUNT"));
				b.setStatus(rs.getString("STATUS"));
				b.setBoardLike(rs.getInt("BOARD_LIKE"));
				
				list.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pstmt = null;
		rs = null;
		
		try {
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			for(Board b : list) {
				rs.next();
				b.setBoardCommentCount(rs.getInt("COMMENTCOUNT"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public int selectAdminSearchCount(Connection conn, String searchType, String searchKeyword) {
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "";
			int totalCount = 0;
			
			if(searchType.equals("all")) sql = prop.getProperty("allCount");
			else if(searchType.equals("delete")) sql = prop.getProperty("deleteCount");
			else if(searchType.equals("exist")) sql = prop.getProperty("existCount");
			
			try {
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + searchKeyword + "%");
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					totalCount = rs.getInt("CNT");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			return totalCount;
		}
		
	public List<Board> selectAdminSearchList(Connection conn, int cPage, int numPerPage, String searchType, String searchKeyword) {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = "";
		String sql2 = "";
		List<Board> list = new ArrayList<Board>();
		if(searchType.equals("all")) {
			sql1 = prop.getProperty("allList");
			sql2 = prop.getProperty("allListAdminCommentCount");
		}
		else if(searchType.equals("delete")) {
			sql1 = prop.getProperty("deleteList");
			sql2 = prop.getProperty("deleteListAdminCommentCount");
		}
		else if(searchType.equals("exist")) {
			sql1 = prop.getProperty("existList");
			sql2 = prop.getProperty("existListAdminCommentCount");
		}
		try {
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, "%" + searchKeyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Board b = new Board();
				
				b.setBoardNo(rs.getInt("BOARD_NO"));
				b.setBoardTitle(rs.getString("BOARD_TITLE"));
				b.setBoardWriter(rs.getString("BOARD_WRITER"));
				b.setBoardContent(rs.getString("BOARD_CONTENT"));
				b.setBoardOriginalFileName(rs.getString("BOARD_ORIGINAL_FILENAME"));
				b.setBoardReNamedFileName(rs.getString("BOARD_RENAMED_FILENAME"));
				b.setBoardDate(rs.getDate("BOARD_DATE"));
				b.setBoardReadCount(rs.getInt("BOARD_READCOUNT"));
				b.setStatus(rs.getString("STATUS"));
				b.setBoardLike(rs.getInt("BOARD_LIKE"));
				
				list.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pstmt = null;
		rs = null;
		
		try {
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, "%" + searchKeyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rs = pstmt.executeQuery();
			
			for(Board b : list) {
				rs.next();
				b.setBoardCommentCount(rs.getInt("COMMENTCOUNT"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public int selectAdminDQSearchCount(Connection conn, String searchType, String searchKeyword) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int totalCount = 0;
		
		if(searchType.equals("all")) sql = prop.getProperty("DQallCount");
		else if(searchType.equals("statusY")) sql = prop.getProperty("DQYCount");
		else if(searchType.equals("statusN")) sql = prop.getProperty("DQNCount");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("CNT");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return totalCount;
	}
	
	public List<BoardDQ> selectAdminDQSearchList(Connection conn, int cPage, int numPerPage, String searchType, String searchKeyword) {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		List<BoardDQ> list = new ArrayList<BoardDQ>();
		if(searchType.equals("all")) {
			sql = prop.getProperty("allDQList");
		}
		else if(searchType.equals("statusY")) {
			sql = prop.getProperty("DQYList");
		}
		else if(searchType.equals("statusN")) {
			sql = prop.getProperty("DQNList");
		}
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardDQ b = new BoardDQ();
				
				b.setBoardDQNo(rs.getInt("BOARD_DQ_NO"));
				b.setBoardDQTitle(rs.getString("BOARD_DQ_TITLE"));
				b.setBoardDQWriter(rs.getString("BOARD_DQ_WRITER"));
				b.setBoardDQContent(rs.getString("BOARD_DQ_CONTENT"));
				b.setBoardDQOriginalFileName(rs.getString("BOARD_DQ_ORIGINAL_FILENAME"));
				b.setBoardDQReNameFileName(rs.getString("BOARD_DQ_RENAMED_FILENAME"));
				b.setBoardDQDate(rs.getDate("BOARD_DQ_DATE"));
				b.setBoardDQProcess(rs.getString("BOARD_DQ_PROCESS"));
				b.setBoardDQAnswer(rs.getString("BOARD_DQ_ANSWER"));
				
				list.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int insertBoardFAQ(Connection conn, BoardFAQ b) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertBoardFAQ");
		String category = b.getBoardFAQCategory();
		
		switch (category) {
			case "all": category = ""; break;
			case "changeAndCancel": category = "변경/취소"; break;
			case "depositAndDelivery": category = "입금/배송"; break;
			case "exchangeAndReturn": category = "교환/반품"; break;
			case "therInquiries": category = "기타문의"; break;
			case "saleAndBenefits": category = "할인/혜택"; break;
			case "memberAndBenefits": category = "회원/혜택"; break;
			case "orderAndLookup": category = "주문/조회"; break;
			case "paymentInquiry": category = "결제문의"; break;
			case "soldOutAndWearing": category = "품절/입고"; break;
			case "contactUs": category = "상품문의"; break;
		}
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, b.getBoardFAQTitle());
			pstmt.setString(3, b.getBoardFAQContent());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public BoardFAQ selectFAQOne(Connection conn, int boardFAQNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectFAQOne");
		BoardFAQ b = new BoardFAQ();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardFAQNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				b.setBoardFAQNo(boardFAQNo);
				b.setBoardFAQCategory(rs.getString("BOARD_FAQ_CATEGORY"));
				b.setBoardFAQTitle(rs.getString("BOARD_FAQ_TITLE"));
				b.setBoardFAQContent(rs.getString("BOARD_FAQ_CONTENT"));
				b.setBoardFAQDate(rs.getDate("BOARD_FAQ_DATE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return b;
	}
	
	public int deleteFAQBoard(Connection conn, int boardFAQNo) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteFAQBoard");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardFAQNo);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateFAQBoard(Connection conn, BoardFAQ b) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateFAQBoard");
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardFAQCategory());
			pstmt.setString(2, b.getBoardFAQTitle());
			pstmt.setString(3, b.getBoardFAQContent());
			pstmt.setInt(4, b.getBoardFAQNo());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateBoardDetail(Connection conn, Board b) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBoardDetail");
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getBoardReadCount());
			pstmt.setString(2, b.getStatus());
			pstmt.setInt(3, b.getBoardLike());
			pstmt.setInt(4, b.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteBoardDetail(Connection conn, int no) {
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoardDetail");
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int selectAdminReportCount(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectAdminReportCount");
		int totalCount = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("CNT");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return totalCount;
	}
	
	public List<BoardReport> selectAdminReportList(Connection conn, int cPage, int numPerPage) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectAdminReportList");
		List<BoardReport> list = new ArrayList<BoardReport>();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardReport b = new BoardReport();
				
				b.setBoardNo(rs.getInt("BOARD_NO"));
				b.setBoardMainCategory(rs.getString("BOARD_MAIN_CATEGORY"));
				b.setBoardWriter(rs.getString("BOARD_WRITER"));
				b.setBoardReporter(rs.getString("BOARD_REPORTER"));
				b.setBoardContent(rs.getString("BOARD_CONTENT"));
				b.setBoardReportContent(rs.getString("BOARD_REPORTER_CONTENT"));
				b.setBoardReportCategory(rs.getString("BOARD_REPORT_CATRGORY"));
				b.setBoardReportDate(rs.getDate("BOARD_REPORT_DATE"));
				
				list.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int selectDQCountAdmin(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectDQCountAdmin");
		int totalCount = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("CNT");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return totalCount;
	}
	
	public List<BoardDQ> selectDQListAdmin(Connection conn, int cPage, int numPerPage) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectDQListAdmin");
		List<BoardDQ> list = new ArrayList();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardDQ b = new BoardDQ();
				
				b.setBoardDQNo(rs.getInt("BOARD_DQ_NO"));
				b.setBoardDQTitle(rs.getString("BOARD_DQ_TITLE"));
				b.setBoardDQWriter(rs.getString("BOARD_DQ_WRITER"));
				b.setBoardDQContent(rs.getString("BOARD_DQ_CONTENT"));
				b.setBoardDQOriginalFileName(rs.getString("BOARD_DQ_ORIGINAL_FILENAME"));
				b.setBoardDQReNameFileName(rs.getString("BOARD_DQ_RENAMED_FILENAME"));
				b.setBoardDQDate(rs.getDate("BOARD_DQ_DATE"));
				b.setBoardDQProcess(rs.getString("BOARD_DQ_PROCESS"));
				
				list.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	
	
	
	
//	--------------------------------------- mypage
	
	public int selectMyPageBoardCount(Connection conn, String nickName) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectMyPageBoardCount");
		int totalCount = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "Y");
			pstmt.setString(2, nickName);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("CNT");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return totalCount;
	}
	
	public List<Board> selectMyPageBoardList(Connection conn, int cPage, int numPerPage, String nickName) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = prop.getProperty("selectMyPageBoardList");
		String sql2 = prop.getProperty("selectListCommentCount");
		List<Board> list = new ArrayList<Board>();
		
		try {
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, nickName);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Board b = new Board();
				
				b.setBoardNo(rs.getInt("BOARD_NO"));
				b.setBoardTitle(rs.getString("BOARD_TITLE"));
				b.setBoardWriter(rs.getString("BOARD_WRITER"));
				b.setBoardContent(rs.getString("BOARD_CONTENT"));
				b.setBoardOriginalFileName(rs.getString("BOARD_ORIGINAL_FILENAME"));
				b.setBoardReNamedFileName(rs.getString("BOARD_RENAMED_FILENAME"));
				b.setBoardDate(rs.getDate("BOARD_DATE"));
				b.setBoardReadCount(rs.getInt("BOARD_READCOUNT"));
				b.setStatus(rs.getString("STATUS"));
				b.setBoardLike(rs.getInt("BOARD_LIKE"));
				
				list.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pstmt = null;
		rs = null;
		
		try {
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			for(Board b : list) {
				rs.next();
				b.setBoardCommentCount(rs.getInt("COMMENTCOUNT"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public int selectMyPageBoardCommentCount(Connection conn, String nickName) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectMyPageBoardCommentCount");
		int totalCount = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("CNT");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return totalCount;
	}
	
	public List<BoardComment> selectMyPageBoardCommentList(Connection conn, int cPage, int numPerPage, String nickName) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectMyPageBoardCommentList");
		List<BoardComment> list = new ArrayList<BoardComment>();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickName);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardComment b = new BoardComment();
				
				b.setBoardCommentWriter(rs.getString("BOARD_COMMENT_WRITER"));
				b.setBoardCommentContent(rs.getString("BOARD_COMMENT_CONTENT"));
				b.setBoardRef(rs.getInt("BOARD_REF"));
				b.setBoardCommentDate(rs.getDate("BOARD_COMMENT_DATE"));
				
				list.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	

	
	
	
	
	

}
