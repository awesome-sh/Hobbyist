package com.hobbyist.award.model.service;

import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.rollback;

import java.sql.*;
import java.util.List;

import com.hobbyist.award.model.dao.AwardDao;
import com.hobbyist.award.model.vo.Award;
import com.hobbyist.award.model.vo.AwardComment;

public class AwardService {

	private AwardDao dao = new AwardDao();

	public List<Award> selectList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Award> list = dao.selectList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectCount() {
		Connection conn = getConnection();
		int result = dao.selectCount(conn);
		close(conn);
		return result;
	}

	public int insertAward(Award a) {
		Connection conn = getConnection();
		int result = dao.insertAward(conn, a);

		if (result > 0) {
			result = dao.selectSeq(conn);
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
		return result;
	}

	public int insertComment(AwardComment comment) {
		Connection conn = getConnection();
		int result = dao.insertComment(conn, comment);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;

	}

	public int deleteAward(Award a) {
		Connection conn = getConnection();
		int result = dao.deleteAward(conn, a);

		if (result > 0) {

			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Award selectOne(int awardNo, boolean hasRead) {
		Connection conn = getConnection();
		Award a = dao.selectOne(conn, awardNo);
System.out.println(hasRead);
		if (a != null && !hasRead) {
			int result = dao.increReadCount(conn, awardNo);
			if (result > 0)
				commit(conn);
			else
				rollback(conn);
		}
		close(conn);
		return a;
	}

	public int updateAward(Award a) {
		Connection conn = getConnection();
		int result = new AwardDao().updateAward(conn, a);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public List<AwardComment> selectCommentAll(int awardNo) {
		Connection conn = getConnection();
		List<AwardComment> list = dao.selectCountAll(conn, awardNo);
		close(conn);
		return list;

	}

	public int deleteComment(int deleteNo) {
		Connection conn = getConnection();
		int result = dao.deleteComment(conn, deleteNo);
		close(conn);
		return result;

	}

	public String selectLikeUser(int awardNo) {
		Connection conn = getConnection();
		String users = dao.selectLikeUsers(conn, awardNo);
		close(conn);
		return users;
	}

	public void increLikeCountsub(int awardNo) {
		Connection conn = getConnection();
		int result = dao.increLikeCountsub(conn, awardNo);
		if (result > 0) commit(conn);
		else rollback(conn);
		close(conn);
	}

	public void increLikeCountAdd(int awardNo) {
		// TODO Auto-generated method stub
		Connection conn=getConnection();
		int result=dao.increLikeCountAdd(conn,awardNo);
		if (result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
	}

	public void increLikeUsers(int awardNo, String users) {
		// TODO Auto-generated method stub
		Connection conn=getConnection();
		int result=dao.increLikeUsers(conn,awardNo,users);
		if (result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		
	}
	public List<Award> selectSearchList(int cPage,int numPerPage,String searchType, String searchKeyword){
		Connection conn=getConnection();
		List<Award> list=dao.selectSearchList(conn,cPage,numPerPage,searchType,searchKeyword);
		close(conn);
		return list;
		
	}

	public int selectSearchCount(String searchType, String searchKeyword) {
		Connection conn=getConnection();
		int totalCount = dao.selectSearchCount(conn, searchType, searchKeyword);
		close(conn);
		
		return totalCount;
	}
	

}
