package com.hobbyist.notice.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.hobbyist.notice.model.dao.NoticeDao;
import com.hobbyist.notice.model.vo.Notice;
import com.hobbyist.notice.model.vo.WeNotice;

public class NoticeService {

	private NoticeDao dao = new NoticeDao();
	
	public int insertNotice(Notice no) {
		Connection conn = getConnection();
		int result = dao.insertNotice(conn,no);
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
	public List<Notice> selectAll(String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Notice> list = dao.selectAll(conn, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	// 검색결과에 따라 리스트 갯수 가져오기(분류기준)
	public int searchCountSort(String sort, String keyword) {
		Connection conn = getConnection();
		int result = dao.searchCountSort(conn, sort, keyword);
		close(conn);
		return result;
	}

	// 리스트 분류별 정렬
	public List<Notice> selectSort(String sort, String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Notice> list = dao.selectSort(conn, sort, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	

	// (삭제된)검색결과에 따라 리스트 갯수 가져오기
	public int searchCountDel(String keyword) {
		Connection conn = getConnection();
		int result = dao.searchCountDel(conn, keyword);
		close(conn);
		return result;
	}

	// (삭제된)리스트 최근 등록순 정렬
	public List<Notice> selectAllDel(String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Notice> list = dao.selectAllDel(conn, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	// (삭제된)검색결과에 따라 리스트 갯수 가져오기(분류기준)
	public int searchCountSortDel(String sort, String keyword) {
		Connection conn = getConnection();
		int result = dao.searchCountSortDel(conn, sort, keyword);
		close(conn);
		return result;
	}

	// (삭제된)리스트 분류별 정렬
	public List<Notice> selectSortDel(String sort, String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Notice> list = dao.selectSortDel(conn, sort, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	// (예약된)검색결과에 따라 리스트 갯수 가져오기
	public int searchCountPre(String keyword) {
		Connection conn = getConnection();
		int result = dao.searchCountPre(conn, keyword);
		close(conn);
		return result;
	}

	// (예약된)리스트 최근 등록순 정렬
	public List<Notice> selectAllPre(String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Notice> list = dao.selectAllPre(conn, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	// (예약된)검색결과에 따라 리스트 갯수 가져오기(분류기준)
	public int searchCountSortPre(String sort, String keyword) {
		Connection conn = getConnection();
		int result = dao.searchCountSortPre(conn, sort, keyword);
		close(conn);
		return result;
	}

	// (예약된)리스트 분류별 정렬
	public List<Notice> selectSortPre(String sort, String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Notice> list = dao.selectSortPre(conn, sort, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public Notice selectOne(int noticeNo, boolean hasRead) {
		Connection conn = getConnection();
		Notice no = dao.selectOne(conn,noticeNo);
		if(no!=null&&!hasRead) {
			int result=dao.increReadCount(conn,noticeNo);
			if(result>0) {
				commit(conn);
			}
			else {
				rollback(conn);
			}
		}
		close(conn);
		return no;
	}
	
	// 작성자 프로필 이미지 찾기
	public String writerImg(String wirter) {
		Connection conn = getConnection();
		String result = dao.writerImg(conn,wirter);
		close(conn);
		return result;
	}
	
	public int delNotice(int noticeNo) {
		Connection conn = getConnection();
		int result = dao.delNotice(conn,noticeNo);
			if(result>0) {
				commit(conn);
			}
			else {
				rollback(conn);
			}
		close(conn);
		return result;
	}
	
	public int wnDelNotice(int noticeNo) {
		Connection conn = getConnection();
		int wnResult = dao.wnDelNotice(conn,noticeNo);
			if(wnResult>0) {
				commit(conn);
			}
			else {
				rollback(conn);
			}
		close(conn);
		return wnResult;
	}
	
	public int reNotice(int noticeNo) {
		Connection conn = getConnection();
		int result = dao.reNotice(conn,noticeNo);
			if(result>0) {
				commit(conn);
			}
			else {
				rollback(conn);
			}
		close(conn);
		return result;
	}
	
	public int wnReNotice(int noticeNo) {
		Connection conn = getConnection();
		int reResult = dao.wnReNotice(conn,noticeNo);
			if(reResult>0) {
				commit(conn);
			}
			else {
				rollback(conn);
			}
		close(conn);
		return reResult;
	}

	public int del_DB(int noticeNo) {
		Connection conn = getConnection();
		int result = dao.del_DB(conn,noticeNo);
			if(result>0) {
				commit(conn);
			}
			else {
				rollback(conn);
			}
		close(conn);
		return result;
	}
	
	//작가신청 공지사항 DB 삭제
	public int wnDel_DB(int noticeNo) {
		Connection conn = getConnection();
		int wnResult = dao.wnDel_DB(conn,noticeNo);
			if(wnResult>0) {
				commit(conn);
			}
			else {
				rollback(conn);
			}
		close(conn);
		return wnResult;
	}
	
	public int updateNotice(Notice no) {
		Connection conn = getConnection();
		int result = dao.updateNotice(conn,no);
			if(result>0) {
				commit(conn);
			}
			else {
				rollback(conn);
			}
		close(conn);
		return result;
	};
	
	public int remainTime(int noticeNo) {
		Connection conn = getConnection();
		int remainTime = dao.remainTime(conn,noticeNo);
		close(conn);
		return remainTime;
	}
	
	public Notice searchNo(Notice no) {
		Connection conn = getConnection();
		Notice noList = dao.searchNo(conn,no);
		close(conn);
		return noList;
	}
	
	public int insertWn(WeNotice wn) {
		Connection conn = getConnection();
		int wnResult = dao.insertWn(conn,wn);
		close(conn);
		return wnResult;
	}
	
	public List<WeNotice> weSelectAll() {
		Connection conn = getConnection();
		List<WeNotice> list = dao.weSelectAll(conn);
		close(conn);
		return list;
	}
	
	//작가신청 공지글 등록할때 날짜 최소값 구하기
	public Date minTime(int no) {
		Connection conn = getConnection();
		Date minTime = dao.minTime(conn,no);
		close(conn);
		return minTime;
	}
	
	public WeNotice weSelectOne(int noticeNo) {
		Connection conn = getConnection();
		WeNotice wnList = dao.weSelectOne(conn,noticeNo);
		close(conn);
		return wnList;
	}
	
	public WeNotice cuWeSelectOne() {
		Connection conn = getConnection();
		WeNotice wnList = dao.cuWeSelectOne(conn);
		close(conn);
		return wnList;
	}
	
	public List<Notice> newLatestList() {
		Connection conn = getConnection();
		List<Notice> list = dao.newLatestList(conn);
		close(conn);
		return list;
	}

}
