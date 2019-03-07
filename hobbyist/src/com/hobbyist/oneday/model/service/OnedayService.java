package com.hobbyist.oneday.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.hobbyist.oneday.model.vo.Oneday;
import com.hobbyist.member.model.vo.Member;
import com.hobbyist.oneday.model.dao.OnedayDao;
import com.hobbyist.oneday.model.vo.Cate;

public class OnedayService {
	
	static OnedayDao dao = new OnedayDao();
	
	// 기본 원데이클래스 목록리스트 가져오기
	public List<Oneday> onedayBasicList() {
		Connection conn = getConnection();
		List<Oneday> list = dao.onedayBasicList(conn);
		close(conn);
		return list;
	}

	// 원데이클래스 등록하기
	public int onedayInsert(Oneday Oneday) {
		Connection conn = getConnection();
		int result = dao.onedayInsert(conn, Oneday);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	// 검색결과에 따른 갯수 가져오기
	public int searchCount(String keyword) {
		Connection conn = getConnection();
		int result = dao.searchCount(conn, keyword);
		close(conn);
		return result;
	}
	
	// 등록순 정렬
	public List<Oneday> descEnroll(String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Oneday> list = dao.descEnroll(conn, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	// 높은가격순
	public List<Oneday> descPrice(String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Oneday> list = dao.descPrice(conn, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}


	// 낮은가격순
	public List<Oneday> ascPrice(String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Oneday> list = dao.ascPrice(conn, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	// 반응형 ajax 검색결과
	public List<Oneday> ajaxSearch(String keyword) {
		Connection conn = getConnection();
		List<Oneday> list = dao.ajaxSearch(conn, keyword);
		close(conn);
		return list;
	}

	// 카테고리 조회
	public List<Cate> CateList() {
		Connection conn = getConnection();
		List<Cate> list = dao.CateList(conn);
		close(conn);
		return list;
	}
	
	// 카테고리 생성
	public int insertCate(String newCate) {
		Connection conn = getConnection();
		int result = dao.insertCate(conn, newCate);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public List<Oneday> selectAll() {
		Connection conn = getConnection();
		List<Oneday> list = dao.selectAll(conn);
		close(conn);
		return list;
	}

	public Oneday selectOne(int no) {
		Connection conn = getConnection();
		Oneday result = dao.selectOne(conn, no);
		close(conn);
		return result;
	}

	// 클래스 등록 -> 작가 찾기
	public List<Member> writerSearch(String classWriter) {
		Connection conn = getConnection();
		List<Member> list = dao.writerSearch(conn, classWriter);
		close(conn);
		return list;
	}

	public int updateOneday(Oneday oneday) {
		Connection conn = getConnection();
		int result = dao.updateOneday(conn, oneday);
		close(conn);
		return result;
	}

	public int searchCateCount(String cate, String keyword) {
		Connection conn = getConnection();
		int result = dao.searchCateCount(conn, cate, keyword);
		close(conn);
		return result;
	}

	public List<Oneday> descEnrollCate(String cate, String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Oneday> list = dao.descEnrollCate(conn, cate, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Oneday> descPriceCate(String cate, String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Oneday> list = dao.descPriceCate(conn, cate, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Oneday> ascPriceCate(String cate, String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Oneday> list = dao.ascPriceCate(conn, cate, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int updateOrderOneday(int classNo) {
		Connection conn = getConnection();
		// 모집정원 먼저 조회하기
		int maxPeople = dao.selectMaxPeople(conn, classNo);
		System.out.println("해당클래스 모집정원 : " + maxPeople);
		// 현재예약한 인원 불러오기
		int currentPeople = dao.selectCurrentPeople(conn, classNo);
		System.out.println("현재예약된 인원 : " + maxPeople);
		
		int result = 0;
		if(currentPeople<maxPeople) {
			// 현재예약인원 < 모집정원, 예약인원 증가
			 dao.updateOrderOneday(conn, classNo);
			 result = 1;
		} else {
			// 아니라면, 예약가능상태 예약불가로 바꾸기
			dao.updateReservation(conn, classNo);
			result = -1;
		}
		
		close(conn);
		return result;
	}

	public int deleteOneday(int no) {
		Connection conn = getConnection();
		int result = dao.deleteOneday(conn, no);
		close(conn);
		return result;
	}

}
