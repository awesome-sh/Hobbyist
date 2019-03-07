package com.hobbyist.member.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.hobbyist.member.model.dao.MemberDao;
import com.hobbyist.member.model.vo.Member;

public class MemberService {

	private MemberDao dao = new MemberDao();
	
	public Member selectOne(Member m) {
		Connection conn = getConnection();
		Member result = dao.selectOne(conn,m);
		close(conn);
		return result;
	}
	
	public int enrollMember(Member m) {
		Connection conn = getConnection();
		int result = dao.enrollMember(conn,m);
		if(result>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int emailCheck(String finalEmail) {
		Connection conn = getConnection();
		int result = dao.emailCheck(conn, finalEmail);
		close(conn);
		if(result>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int nicknameCheck(String memberNickname) {
		Connection conn = getConnection();
		int result = dao.nicknameCheck(conn, memberNickname);
		close(conn);
		if(result>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;

	}

	public Member selectMemberName(Member m) {
		Connection conn = getConnection();
		Member result = dao.selectMemberName(conn,m);
		close(conn);
		return result;
		
	}

	public Member searchMemberPwd(Member m) {
		Connection conn = getConnection();
		Member result = dao.searchMemberPwd(conn, m);
		close(conn);
		return result;
	}

	public int updateTempPwd(Member m) {
		Connection conn = getConnection();
		int resultPwd = dao.updateTempPwd(conn, m);
		if(resultPwd>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return resultPwd;
	}

	public int updateMember(Member m) {
		Connection conn = getConnection();
		int result = dao.updateMember(conn, m);
		if(result>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;

	}

	public int deleteMember(Member m) {
		Connection conn = getConnection();
		int result = dao.deleteMember(conn, m);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updatePwd(Member m) {
		Connection conn = getConnection();
		int result = dao.updatePwd(conn, m);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public List<Member> memberList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = dao.memberList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectMemberCount() {
		Connection conn = getConnection();
		int result = dao.selectMemberCount(conn);
		close(conn);
		return result;
	}

	public int deleteAdmin(Member m) {
		Connection conn =getConnection();
		int result = dao.deleteAdmin(conn, m);
		close(conn);
		return result;
	}

	public int updateAdmin(Member m) {
		Connection conn = getConnection();
		int result = dao.updateAdmin(conn, m);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	
	
	
	
	
	//작가신청에서 사용하는 중.. 
	public int writerPassUpdate(String memberEmail) {
		Connection conn =getConnection();
		int result = dao.writerPassUpdate(conn, memberEmail);
		close(conn);
		return result;
	}
	
	public int writerFailUpdate(String memberEmail) {
		Connection conn =getConnection();
		int result = dao.writerFailUpdate(conn, memberEmail);
		close(conn);
		return result;
	}
	
	public int writerReUpdate(String memberEmail) {
		Connection conn =getConnection();
		int result = dao.writerReUpdate(conn, memberEmail);
		close(conn);
		return result;
	}

}
