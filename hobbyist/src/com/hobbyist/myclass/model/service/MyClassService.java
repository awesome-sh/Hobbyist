package com.hobbyist.myclass.model.service;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.hobbyist.myclass.model.dao.MyClassDao;
import com.hobbyist.myclass.model.vo.Lecture;
import com.hobbyist.myclass.model.vo.MyClass;

public class MyClassService {

	// 내클래스 등록
	public int insertClass(MyClass mc) {
		Connection conn = getConnection();
		int result = new MyClassDao().insertClass(conn, mc);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	// 내클래스 목록 가져오기
	public List<MyClass> selectMyClass(String member) {
		Connection conn = getConnection();
		List<MyClass> list = new MyClassDao().selectMyClass(conn, member);
		close(conn);
		return list;
	}

	// 강좌불러오기
	public List<Lecture> selectLecture(int no) {
		Connection conn = getConnection();
		List<Lecture> list = new MyClassDao().selectLecture(conn, no);
		close(conn);
		return list;
	}

	// 강좌상세 내용 불러오기 (클래스 번호로 조회)
	public Lecture selectLectureOne(int no) {
		Connection conn = getConnection();
		Lecture lecture = new MyClassDao().selectLectureOne(conn, no);
		close(conn);
		return lecture;
	}

	// 내클래스 진행상태 업데이트
	/*
	 * public int updateClass(int no, String member) { Connection conn =
	 * getConnection(); int result = new MyClassDao().updateClass(conn, no, member);
	 * close(conn); return result; }
	 */

}
