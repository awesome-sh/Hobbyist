package com.hobbyist.writer.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.hobbyist.writer.model.dao.WriterDao;
import com.hobbyist.writer.model.vo.WriterEnroll;

public class WriterService {

	private WriterDao dao = new WriterDao();
	
	public int insertWriterEnroll(WriterEnroll we) {
		Connection conn = getConnection();
		int result = dao.insertWriterEnroll(conn,we);
		if(result>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	// 검색결과에 따라 리스트 갯수 가져오기
	public int searchCount(String keyword) {
		Connection conn = getConnection();
		int result = dao.searchCount(conn, keyword);
		close(conn);
		return result;
	}
	
	// 리스트 최근 등록순 정렬
	public List<WriterEnroll> descEnroll(String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<WriterEnroll> list = dao.descEnroll(conn, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	// 리스트 이전 등록순 정렬
	public List<WriterEnroll> ascEnroll(String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<WriterEnroll> list = dao.ascEnroll(conn, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	
	// (마이페이지)검색결과에 따라 리스트 갯수 가져오기
	public int myPageSearchCount(String keyword, int memberNo) {
		Connection conn = getConnection();
		int result = dao.myPageSearchCount(conn, keyword, memberNo);
		close(conn);
		return result;
	}
	
	// (마이페이지)리스트 최근 등록순 정렬
	public List<WriterEnroll> myPageDescEnroll(String keyword, int memberNo, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<WriterEnroll> list = dao.myPageDescEnroll(conn, keyword, memberNo, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	// (마이페이지)리스트 이전 등록순 정렬
	public List<WriterEnroll> myPageAscEnroll(String keyword, int memberNo, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<WriterEnroll> list = dao.myPageAscEnroll(conn, keyword, memberNo, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public List<WriterEnroll> selectAll() {
		Connection conn = getConnection();
		List<WriterEnroll> list = dao.selectAll(conn);
		close(conn);
		return list;
	}
	
	public WriterEnroll selectOne(WriterEnroll we) {
		Connection conn = getConnection();
		WriterEnroll result = dao.selectOne(conn,we);
		close(conn);
		return result;
	}
	
	public int passWriterEnroll(int writerEnrollNo) {
		Connection conn = getConnection();
		int result = dao.passWriterEnroll(conn,writerEnrollNo);
		if(result>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int failWriterEnroll(int writerEnrollNo) {
		Connection conn = getConnection();
		int result = dao.failWriterEnroll(conn,writerEnrollNo);
		if(result>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int returnWriterEnroll(int writerEnrollNo) {
		Connection conn = getConnection();
		int result = dao.returnWriterEnroll(conn,writerEnrollNo);
		if(result>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	
	public int UpdateWriterEnroll(WriterEnroll we) {
		Connection conn = getConnection();
		int result = dao.UpdateWriterEnroll(conn,we);
		if(result>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	// (마이페이지) 예비작가 신청 수락 여부
	public int prepRequestYN(int writerEnrollNo, String writerPrepRequestYN) {
		Connection conn = getConnection();
		int result = dao.prepRequestYN(conn, writerEnrollNo, writerPrepRequestYN);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	//작가신청 기간 중복 신청 막는 로직
	public List<WriterEnroll> selectQuarter(String weQuarter) {
		Connection conn = getConnection();
		List<WriterEnroll> result = dao.selectQuarter(conn,weQuarter);
		close(conn);
		return result;
	}
}
