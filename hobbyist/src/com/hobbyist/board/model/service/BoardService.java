package com.hobbyist.board.model.service;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.hobbyist.board.model.dao.BoardDao;
import com.hobbyist.board.model.vo.Board;
import com.hobbyist.board.model.vo.BoardComment;
import com.hobbyist.board.model.vo.BoardDQ;
import com.hobbyist.board.model.vo.BoardFAQ;
import com.hobbyist.board.model.vo.BoardReport;

public class BoardService {
	
	private BoardDao dao = new BoardDao();
	
	public Board selectOne(int boardNo, boolean hasRead) {
		Connection conn = getConnection();
		Board b = dao.selectOne(conn, boardNo);
		if(b != null && !hasRead) {
			int result = dao.updateReadCount(conn, boardNo);
			if(result > 0) commit(conn);
			else rollback(conn);
		}
		close(conn);
		
		return b;
	}

	public BoardDQ selectDQOne(int boardDQNo) {
		Connection conn = getConnection();
		BoardDQ b = dao.selectDQOne(conn, boardDQNo);
		close(conn);
		
		return b;
	}
	
	public List<Board> selectAll() {
		
		Connection conn = getConnection();
		List<Board> list = dao.selectAll(conn);
		close(conn);
		
		return list;
	}
	
	public List<Board> selectList(int cPage, int numPerPage) {
		
		Connection conn = getConnection();
		List<Board> list = dao.selectList(conn, cPage, numPerPage);
		close(conn);
		
		return list;
	}
	
	public List<BoardFAQ> selectFAQList(int cPage, int numPerPage) {
		
		Connection conn = getConnection();
		List<BoardFAQ> list = dao.selectFAQList(conn, cPage, numPerPage);
		close(conn);
		
		return list;
	}
	
	public List<BoardFAQ> selectFAQSearchList(int cPage, int numPerPage, String searchType, String searchKeyword) {
		
		Connection conn = getConnection();
		List<BoardFAQ> list = dao.selectFAQSearchList(conn, cPage, numPerPage, searchType, searchKeyword);
		close(conn);
		
		return list;
	}
	
	public List<BoardDQ> selectDQList(int cPage, int numPerPage, String nickName) {
		
		Connection conn = getConnection();
		List<BoardDQ> list = dao.selectDQList(conn, cPage, numPerPage, nickName);
		close(conn);
		
		return list;
	}
	
	public List<Board> selectSearchList(int cPage, int numPerPage, String searchType, String searchKeyword) {
		
		Connection conn = getConnection();
		List<Board> list = dao.selectSearchList(conn, cPage, numPerPage, searchType, searchKeyword);
		close(conn);
		
		return list;
	}
	
	public int selectCount() {
		
		Connection conn = getConnection();
		int totalCount = dao.selectCount(conn);
		close(conn);
		
		return totalCount;
	}
	
	public int selectFAQCount() {
		
		Connection conn = getConnection();
		int totalCount = dao.selectFAQCount(conn);
		close(conn);
		
		return totalCount;
	}
	
	public int selectFAQSearchCount(String searchType, String searchKeyword) {
		
		Connection conn = getConnection();
		int totalCount = dao.selectFAQSearchCount(conn, searchType, searchKeyword);
		close(conn);
		
		return totalCount;
	}
	
	public int selectDQCount(String nickName) {
		
		Connection conn = getConnection();
		int totalCount = dao.selectDQCount(conn, nickName);
		close(conn);
		
		return totalCount;
	}
	
	public int selectSearchCount(String searchType, String searchKeyword) {
		
		Connection conn = getConnection();
		int totalCount = dao.selectSearchCount(conn, searchType, searchKeyword);
		close(conn);
		
		return totalCount;
	}
	
	public List<BoardComment> selectCommentAll(int boardNo) {
		Connection conn = getConnection();
		List<BoardComment> bcList = dao.selectCommentAll(conn, boardNo);
		close(conn);
		
		return bcList;
	}
	
	public int insertBoard(Board b) {
		Connection conn = getConnection();
		int result = dao.insertBoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int insertDQBoard(BoardDQ b) {
		Connection conn = getConnection();
		int result = dao.insertDQBoard(conn, b);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
	
	public int insertComment(BoardComment bc) {
		Connection conn = getConnection();
		int result = dao.insertComment(conn, bc);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
	
	public int insertBoardReport(BoardReport b) {
		Connection conn = getConnection();
		int result = dao.insertBoardReport(conn, b);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
	
	public int selectNextOne(int boardNo) {
		
		Connection conn = getConnection();
		int moveNo = dao.selectNextOne(conn, boardNo);
		close(conn);
		
		return moveNo;
	}
	
	public int selectPrevOne(int boardNo) {
		
		Connection conn = getConnection();
		int moveNo = dao.selectPrevOne(conn, boardNo);
		close(conn);
		
		return moveNo;
	}
	
	public int updateBoard(Board b) {
		
		Connection conn = getConnection();
		int result = dao.updateBoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int updateDQBoard(BoardDQ b) {
		
		Connection conn = getConnection();
		int result = dao.updateDQBoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int updateDQAnswerBoard(int BoardDQNo, String boardDQContent) {
		
		Connection conn = getConnection();
		int result = dao.updateDQAnswerBoard(conn, BoardDQNo, boardDQContent);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public void updateLikeCountAdd(int boardNo) {
		
		Connection conn = getConnection();
		int result = dao.updateLikeCountAdd(conn, boardNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
	}
	
	public void updateLikeCountSub(int boardNo) {
		
		Connection conn = getConnection();
		int result = dao.updateLikeCountSub(conn, boardNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
	}
	
	public void updateLikeUsers(int boardNo, String users) {
		
		Connection conn = getConnection();
		int result = dao.updateLikeUsers(conn, boardNo, users);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
	}
	
	public int deleteBoard(int boardNo) {
		
		Connection conn = getConnection();
		int result = dao.deleteBoard(conn, boardNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int deleteDQBoard(int boardDQNo) {
		
		Connection conn = getConnection();
		int result = dao.deleteDQBoard(conn, boardDQNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int deleteComment(int delNo) {
		Connection conn = getConnection();
		int result = dao.deleteComment(conn, delNo);

		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public String selectLikeUsers(int boardNo) {
		
		Connection conn = getConnection();
		String users = dao.selectLikeUsers(conn, boardNo);
		close(conn);
		
		return users;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	-------------------------------------------------- admin
	
	public int selectAdminCount() {
		
		Connection conn = getConnection();
		int totalCount = dao.selectAdminCount(conn);
		close(conn);
		
		return totalCount;
	}
	
	public List<Board> selectAdminList(int cPage, int numPerPage) {
		
		Connection conn = getConnection();
		List<Board> list = dao.selectAdminList(conn, cPage, numPerPage);
		close(conn);
		
		return list;
	}
	
	public int selectAdminSearchCount(String searchType, String searchKeyword) {
		
		Connection conn = getConnection();
		int totalCount = dao.selectAdminSearchCount(conn, searchType, searchKeyword);
		close(conn);
		
		return totalCount;
	}
	
	public List<Board> selectAdminSearchList(int cPage, int numPerPage, String searchType, String searchKeyword) {
		
		Connection conn = getConnection();
		List<Board> list = dao.selectAdminSearchList(conn, cPage, numPerPage, searchType, searchKeyword);
		close(conn);
		
		return list;
	}
	
	public int selectAdminDQSearchCount(String searchType, String searchKeyword) {
		
		Connection conn = getConnection();
		int totalCount = dao.selectAdminDQSearchCount(conn, searchType, searchKeyword);
		close(conn);
		
		return totalCount;
	}
	
	public List<BoardDQ> selectAdminDQSearchList(int cPage, int numPerPage, String searchType, String searchKeyword) {
		
		Connection conn = getConnection();
		List<BoardDQ> list = dao.selectAdminDQSearchList(conn, cPage, numPerPage, searchType, searchKeyword);
		close(conn);
		
		return list;
	}
	
	public int insertBoardFAQ(BoardFAQ b) {
		
		Connection conn = getConnection();
		int result = dao.insertBoardFAQ(conn, b);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	public BoardFAQ selectFAQOne(int boardFAQNo) {
		
		Connection conn = getConnection();
		BoardFAQ b = dao.selectFAQOne(conn, boardFAQNo);
		
		return b;
	}
	
	public int deleteFAQBoard(int boardFAQNo) {
		
		Connection conn = getConnection();
		int result = dao.deleteFAQBoard(conn, boardFAQNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	public int updateFAQBoard(BoardFAQ b) {
		
		Connection conn = getConnection();
		int result = dao.updateFAQBoard(conn, b);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	public int updateBoardDetail(Board b) {
		Connection conn = getConnection();
		int result = dao.updateBoardDetail(conn, b);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	public int deleteBoardDetail(int no) {
		Connection conn = getConnection();
		int result = dao.deleteBoardDetail(conn, no);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	public int selectAdminReportCount() {
		
		Connection conn = getConnection();
		int totalCount = dao.selectAdminReportCount(conn);
		close(conn);
		
		return totalCount;
	}
	
	public List<BoardReport> selectAdminReportList(int cPage, int numPerPage) {
		
		Connection conn = getConnection();
		List<BoardReport> list = dao.selectAdminReportList(conn, cPage, numPerPage);
		close(conn);
		
		return list;
	}
	
	public int selectDQCountAdmin() {
		
		Connection conn = getConnection();
		int totalCount = dao.selectDQCountAdmin(conn);
		close(conn);
		
		return totalCount;
	}
	
	public List<BoardDQ> selectDQListAdmin(int cPage, int numPerPage) {
		
		Connection conn = getConnection();
		List<BoardDQ> list = dao.selectDQListAdmin(conn, cPage, numPerPage);
		close(conn);
		
		return list;
	}
	
//	--------------------------------------- mypage
	
	public int selectMyPageBoardCount(String nickName) {
		
		Connection conn = getConnection();
		int totalCount = dao.selectMyPageBoardCount(conn, nickName);
		close(conn);
		
		return totalCount;
	}
	
	public List<Board> selectMyPageBoardList(int cPage, int numPerPage, String nickName) {
		
		Connection conn = getConnection();
		List<Board> list = dao.selectMyPageBoardList(conn, cPage, numPerPage, nickName);
		close(conn);
		
		return list;
	}
	
	public int selectMyPageBoardCommentCount(String nickName) {
		
		Connection conn = getConnection();
		int totalCount = dao.selectMyPageBoardCommentCount(conn, nickName);
		close(conn);
		
		return totalCount;
	}
	
	public List<BoardComment> selectMyPageBoardCommentList(int cPage, int numPerPage, String nickName) {
		
		Connection conn = getConnection();
		List<BoardComment> list = dao.selectMyPageBoardCommentList(conn, cPage, numPerPage, nickName);
		close(conn);
		
		return list;
	}
	

}
