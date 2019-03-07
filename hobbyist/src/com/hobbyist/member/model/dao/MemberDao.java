package com.hobbyist.member.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hobbyist.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();

	public MemberDao() {
		String fileName = MemberDao.class.getResource("/sql/member/member.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Member selectOne(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member result = new Member();
		String sql = prop.getProperty("selectOne");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberEmail());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result.setMemberNo(rs.getInt("member_no"));
				result.setMemberEmail(rs.getString("member_email"));
				result.setMemberPassword(rs.getString("member_password"));
				result.setMemberNickname(rs.getString("member_nickname"));
				result.setMemberName(rs.getString("member_name"));
				result.setMemberBirthday(rs.getString("member_birthday"));
				result.setMemberPhone(rs.getString("member_phone"));
				result.setMemberOriginalImage(rs.getString("member_original_image"));
				result.setMemberRenamedImage(rs.getString("member_renamed_image"));
				result.setMemberEnrolldate(rs.getDate("member_enrolldate"));
				result.setMemberGrade(rs.getString("member_grade"));
				result.setMemberWriterYN(rs.getString("member_writer_yn"));
				result.setMemberStatus(rs.getString("member_status"));
				System.out.println("dao에서 result : "+result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println("dao에서 result2 : "+result);
		return result;
		
	}
	
	public int enrollMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("enrollMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberEmail());
			pstmt.setString(2, m.getMemberPassword());
			pstmt.setString(3, m.getMemberNickname());
			pstmt.setString(4, m.getMemberName());
			pstmt.setString(5, m.getMemberBirthday());
			pstmt.setString(6, m.getMemberPhone());
			pstmt.setString(7, m.getMemberOriginalImage());
			pstmt.setString(8, m.getMemberRenamedImage());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int emailCheck(Connection conn, String finalEmail) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs =null;
		String sql = prop.getProperty("emailCheck");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, finalEmail);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1); //가입할수 없음 //기존의 아이디가 있는거
			}
			System.out.println(result);
			System.out.println(finalEmail);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int nicknameCheck(Connection conn, String memberNickname) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("nicknameCheck");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberNickname);
			rs=pstmt.executeQuery();
			
			if(rs.next()) { //가입할수 없음
				result=rs.getInt(1);
			} 
			System.out.println("닉네임 값 : "+result);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public Member selectMemberName(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member result = new Member();
		String sql = prop.getProperty("searchId");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberName());
			pstmt.setString(2, m.getMemberPhone());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result.setMemberNo(rs.getInt("member_no"));
				result.setMemberEmail(rs.getString("member_email"));
				result.setMemberPassword(rs.getString("member_password"));
				result.setMemberNickname(rs.getString("member_nickname"));
				result.setMemberName(rs.getString("member_name"));
				result.setMemberBirthday(rs.getString("member_birthday"));
				result.setMemberPhone(rs.getString("member_phone"));
				result.setMemberOriginalImage(rs.getString("member_original_image"));
				result.setMemberRenamedImage(rs.getString("member_renamed_image"));
				result.setMemberEnrolldate(rs.getDate("member_enrolldate"));
				result.setMemberGrade(rs.getString("member_grade"));
				result.setMemberWriterYN(rs.getString("member_writer_yn"));
				result.setMemberStatus(rs.getString("member_status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public Member searchMemberPwd(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member result = null;
		
		try {
			String sql = prop.getProperty("searchPwd");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberEmail());
			System.out.println("dao 에서 확인 : " + m.getMemberEmail());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = new Member();
				result.setMemberNo(rs.getInt("member_no"));
				result.setMemberEmail(rs.getString("member_email"));
				result.setMemberPassword(rs.getString("member_password"));
				result.setMemberNickname(rs.getString("member_nickname"));
				result.setMemberName(rs.getString("member_name"));
				result.setMemberBirthday(rs.getString("member_birthday"));
				result.setMemberPhone(rs.getString("member_phone"));
				result.setMemberOriginalImage(rs.getString("member_original_image"));
				result.setMemberRenamedImage(rs.getString("member_renamed_image"));
				result.setMemberEnrolldate(rs.getDate("member_enrolldate"));
				result.setMemberGrade(rs.getString("member_grade"));
				result.setMemberWriterYN(rs.getString("member_writer_yn"));
				result.setMemberStatus(rs.getString("member_status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int updateTempPwd(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updateTempPwd");
		int resultPwd=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberPassword());
			pstmt.setString(2, m.getMemberEmail());
			resultPwd=pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return resultPwd;
	}

	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		System.out.println("dao에서 확인 : "+m.getMemberNickname()+m.getMemberPhone()+m.getMemberOriginalImage()+m.getMemberEmail());
		int result =0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberNickname());
			pstmt.setString(2, m.getMemberPhone());
			pstmt.setString(3, m.getMemberOriginalImage());
			pstmt.setString(4, m.getMemberRenamedImage());
			pstmt.setString(5, m.getMemberEmail());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		int result=0;
		System.out.println("dao에서 확인 : "+m.getMemberEmail());
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberEmail());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updatePwd(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwd");
		int result =0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberPassword());
			pstmt.setString(2, m.getMemberEmail());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Member> memberList(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("memberList");
		List<Member> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberEmail(rs.getString("member_email"));
				m.setMemberName(rs.getString("member_name"));
				m.setMemberNickname(rs.getString("member_nickname"));
				m.setMemberGrade(rs.getString("member_grade"));
				m.setMemberBirthday(rs.getString("member_birthday"));
				m.setMemberAddress(rs.getString("member_address"));
				m.setMemberMemo(rs.getString("member_memo"));
				m.setMemberEnrolldate(rs.getDate("member_enrolldate"));
				m.setMemberPhone(rs.getString("member_Phone"));
				list.add(m);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int selectMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("selectMemberCount");
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteAdmin(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteAdmin");
		int result=0;
		System.out.println("dao에서 확인 : "+m.getMemberEmail());
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberEmail());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateAdmin(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql=prop.getProperty("updateAdmin");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberNickname());
			pstmt.setString(2, m.getMemberPhone());
			pstmt.setString(3, m.getMemberGrade());
			pstmt.setString(4, m.getMemberAddress());
			pstmt.setString(5, m.getMemberMemo());
			pstmt.setString(6, m.getMemberEmail());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	
	
	
	//작가신청에서 사용하는 중.. 
	public int writerPassUpdate(Connection conn, String memberEmail) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("writerPassUpdate");
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int writerFailUpdate(Connection conn, String memberEmail) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("writerFailUpdate");
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int writerReUpdate(Connection conn, String memberEmail) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("writerReUpdate");
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
