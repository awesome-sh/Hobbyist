package com.hobbyist.notice.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hobbyist.notice.model.vo.Notice;
import com.hobbyist.notice.model.vo.WeNotice;
import com.hobbyist.writer.model.dao.WriterDao;

public class NoticeDao {

	private Properties prop = new Properties();
	
	public NoticeDao() {
		String fileName = WriterDao.class.getResource("/sql/notice/notice.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int insertNotice(Connection conn, Notice no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertNotice");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no.getNoticeSort());
			pstmt.setString(2, no.getNoticeTitle());
			pstmt.setString(3, no.getNoticeWriter());
			pstmt.setString(4, no.getNoticeContent());
			pstmt.setDate(5, no.getNoticeDate());
			pstmt.setString(6, no.getNoticeFilenameOriginal());
			pstmt.setString(7, no.getNoticeFilenameRenamed());
			pstmt.setString(8, no.getNoticeImgnameOriginal());
			pstmt.setString(9, no.getNoticeImgnameRenamed());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 검색결과에 다른 리스트 갯수 가져오기
	public int searchCount(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("searchCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 리스트 최근 등록일순으로 가져오기
	public List<Notice> selectAll(Connection conn, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList();
		String sql = prop.getProperty("selectAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice no = new Notice();
				
				no.setNoticeNo(rs.getInt("notice_no"));
				no.setNoticeSort(rs.getString("notice_sort"));
				no.setNoticeTitle(rs.getString("notice_title"));
				no.setNoticeWriter(rs.getString("notice_writer"));
				no.setNoticeContent(rs.getString("notice_content"));
				no.setNoticeDate(rs.getDate("notice_date"));
				no.setNoticeFilenameOriginal(rs.getString("notice_filename_original"));
				no.setNoticeFilenameRenamed(rs.getString("notice_filename_renamed"));
				no.setNoticeImgnameOriginal(rs.getString("notice_imgname_original"));
				no.setNoticeImgnameRenamed(rs.getString("notice_imgname_renamed"));
				no.setNoticeReadcount(rs.getInt("notice_readcount"));
				no.setNoticeStatus(rs.getString("notice_status"));
				list.add(no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 검색결과에 다른 리스트 갯수 가져오기
	public int searchCountSort(Connection conn, String sort, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("searchCountSort");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sort);
			pstmt.setString(2, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 리스트 최근 등록일순으로 가져오기
	public List<Notice> selectSort(Connection conn, String sort, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList();
		String sql = prop.getProperty("selectSort");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sort);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice no = new Notice();
				
				no.setNoticeNo(rs.getInt("notice_no"));
				no.setNoticeSort(rs.getString("notice_sort"));
				no.setNoticeTitle(rs.getString("notice_title"));
				no.setNoticeWriter(rs.getString("notice_writer"));
				no.setNoticeContent(rs.getString("notice_content"));
				no.setNoticeDate(rs.getDate("notice_date"));
				no.setNoticeFilenameOriginal(rs.getString("notice_filename_original"));
				no.setNoticeFilenameRenamed(rs.getString("notice_filename_renamed"));
				no.setNoticeImgnameOriginal(rs.getString("notice_imgname_original"));
				no.setNoticeImgnameRenamed(rs.getString("notice_imgname_renamed"));
				no.setNoticeReadcount(rs.getInt("notice_readcount"));
				no.setNoticeStatus(rs.getString("notice_status"));
				list.add(no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// (삭제된)검색결과에 다른 리스트 갯수 가져오기
	public int searchCountDel(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("searchCountDel");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// (삭제된)리스트 최근 등록일순으로 가져오기
	public List<Notice> selectAllDel(Connection conn, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList();
		String sql = prop.getProperty("selectAllDel");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice no = new Notice();
				
				no.setNoticeNo(rs.getInt("notice_no"));
				no.setNoticeSort(rs.getString("notice_sort"));
				no.setNoticeTitle(rs.getString("notice_title"));
				no.setNoticeWriter(rs.getString("notice_writer"));
				no.setNoticeContent(rs.getString("notice_content"));
				no.setNoticeDate(rs.getDate("notice_date"));
				no.setNoticeFilenameOriginal(rs.getString("notice_filename_original"));
				no.setNoticeFilenameRenamed(rs.getString("notice_filename_renamed"));
				no.setNoticeImgnameOriginal(rs.getString("notice_imgname_original"));
				no.setNoticeImgnameRenamed(rs.getString("notice_imgname_renamed"));
				no.setNoticeReadcount(rs.getInt("notice_readcount"));
				no.setNoticeStatus(rs.getString("notice_status"));
				list.add(no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// (삭제된)검색결과에 다른 리스트 갯수 가져오기
	public int searchCountSortDel(Connection conn, String sort, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("searchCountSortDel");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sort);
			pstmt.setString(2, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// (삭제된)리스트 최근 등록일순으로 가져오기
	public List<Notice> selectSortDel(Connection conn, String sort, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList();
		String sql = prop.getProperty("selectSortDel");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sort);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice no = new Notice();
				
				no.setNoticeNo(rs.getInt("notice_no"));
				no.setNoticeSort(rs.getString("notice_sort"));
				no.setNoticeTitle(rs.getString("notice_title"));
				no.setNoticeWriter(rs.getString("notice_writer"));
				no.setNoticeContent(rs.getString("notice_content"));
				no.setNoticeDate(rs.getDate("notice_date"));
				no.setNoticeFilenameOriginal(rs.getString("notice_filename_original"));
				no.setNoticeFilenameRenamed(rs.getString("notice_filename_renamed"));
				no.setNoticeImgnameOriginal(rs.getString("notice_imgname_original"));
				no.setNoticeImgnameRenamed(rs.getString("notice_imgname_renamed"));
				no.setNoticeReadcount(rs.getInt("notice_readcount"));
				no.setNoticeStatus(rs.getString("notice_status"));
				list.add(no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// (예약된)검색결과에 다른 리스트 갯수 가져오기
	public int searchCountPre(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("searchCountPre");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// (예약된)리스트 최근 등록일순으로 가져오기
	public List<Notice> selectAllPre(Connection conn, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList();
		String sql = prop.getProperty("selectAllPre");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice no = new Notice();
				
				no.setNoticeNo(rs.getInt("notice_no"));
				no.setNoticeSort(rs.getString("notice_sort"));
				no.setNoticeTitle(rs.getString("notice_title"));
				no.setNoticeWriter(rs.getString("notice_writer"));
				no.setNoticeContent(rs.getString("notice_content"));
				no.setNoticeDate(rs.getDate("notice_date"));
				no.setNoticeFilenameOriginal(rs.getString("notice_filename_original"));
				no.setNoticeFilenameRenamed(rs.getString("notice_filename_renamed"));
				no.setNoticeImgnameOriginal(rs.getString("notice_imgname_original"));
				no.setNoticeImgnameRenamed(rs.getString("notice_imgname_renamed"));
				no.setNoticeReadcount(rs.getInt("notice_readcount"));
				no.setNoticeStatus(rs.getString("notice_status"));
				list.add(no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// (예약된)검색결과에 다른 리스트 갯수 가져오기
	public int searchCountSortPre(Connection conn, String sort, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("searchCountSortPre");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sort);
			pstmt.setString(2, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// (예약된)리스트 최근 등록일순으로 가져오기
	public List<Notice> selectSortPre(Connection conn, String sort, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList();
		String sql = prop.getProperty("selectSortPre");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sort);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice no = new Notice();
				
				no.setNoticeNo(rs.getInt("notice_no"));
				no.setNoticeSort(rs.getString("notice_sort"));
				no.setNoticeTitle(rs.getString("notice_title"));
				no.setNoticeWriter(rs.getString("notice_writer"));
				no.setNoticeContent(rs.getString("notice_content"));
				no.setNoticeDate(rs.getDate("notice_date"));
				no.setNoticeFilenameOriginal(rs.getString("notice_filename_original"));
				no.setNoticeFilenameRenamed(rs.getString("notice_filename_renamed"));
				no.setNoticeImgnameOriginal(rs.getString("notice_imgname_original"));
				no.setNoticeImgnameRenamed(rs.getString("notice_imgname_renamed"));
				no.setNoticeReadcount(rs.getInt("notice_readcount"));
				no.setNoticeStatus(rs.getString("notice_status"));
				list.add(no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public Notice selectOne(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectOne");
		Notice no = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				no = new Notice();

				no.setNoticeNo(rs.getInt("notice_no"));
				no.setNoticeSort(rs.getString("notice_sort"));
				no.setNoticeTitle(rs.getString("notice_title"));
				no.setNoticeWriter(rs.getString("notice_writer"));
				no.setNoticeContent(rs.getString("notice_content"));
				no.setNoticeDate(rs.getDate("notice_date"));
				no.setNoticeFilenameOriginal(rs.getString("notice_filename_original"));
				no.setNoticeFilenameRenamed(rs.getString("notice_filename_renamed"));
				no.setNoticeImgnameOriginal(rs.getString("notice_imgname_original"));
				no.setNoticeImgnameRenamed(rs.getString("notice_imgname_renamed"));
				no.setNoticeReadcount(rs.getInt("notice_readcount"));
				no.setNoticeStatus(rs.getString("notice_status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return no;
	}
	
	public int increReadCount(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("increReadCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public String writerImg(Connection conn, String writer) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";
		String sql = prop.getProperty("writerImg");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int delNotice(Connection conn, int NoticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("delNotice");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NoticeNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int wnDelNotice(Connection conn, int NoticeNo) {
		PreparedStatement pstmt = null;
		int wnResult = 0;
		String sql = prop.getProperty("wnDelNotice");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NoticeNo);
			wnResult = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return wnResult;
	}

	public int reNotice(Connection conn, int NoticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("reNotice");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NoticeNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int wnReNotice(Connection conn, int NoticeNo) {
		PreparedStatement pstmt = null;
		int reResult = 0;
		String sql = prop.getProperty("wnReNotice");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NoticeNo);
			reResult = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return reResult;
	}

	public int del_DB(Connection conn, int NoticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("del_DB");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NoticeNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//작가신청 공지사항 DB 삭제
	public int wnDel_DB(Connection conn, int NoticeNo) {
		PreparedStatement pstmt = null;
		int wnResult = 0;
		String sql = prop.getProperty("wnDel_DB");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NoticeNo);
			wnResult = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return wnResult;
	}
	
	public int updateNotice(Connection conn, Notice no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateNotice");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no.getNoticeTitle());
			pstmt.setString(2, no.getNoticeContent());
			pstmt.setDate(3, no.getNoticeDate());
			pstmt.setString(4, no.getNoticeFilenameOriginal());
			pstmt.setString(5, no.getNoticeFilenameRenamed());
			pstmt.setString(6, no.getNoticeImgnameOriginal());
			pstmt.setString(7, no.getNoticeImgnameRenamed());
			pstmt.setInt(8, no.getNoticeNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int remainTime(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("remainTime");
		int remainTime = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				remainTime = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return remainTime;
	}
	
	public Notice searchNo(Connection conn, Notice no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("searchNo");
		Notice noList = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no.getNoticeSort());
			pstmt.setString(2, no.getNoticeTitle());
			pstmt.setString(3, no.getNoticeWriter());
			pstmt.setString(4, no.getNoticeContent());
			pstmt.setDate(5, no.getNoticeDate());
			
			rs= pstmt.executeQuery();
			if(rs.next()) {
				noList = new Notice();

				noList.setNoticeNo(rs.getInt("notice_no"));
				noList.setNoticeSort(rs.getString("notice_sort"));
				noList.setNoticeTitle(rs.getString("notice_title"));
				noList.setNoticeWriter(rs.getString("notice_writer"));
				noList.setNoticeContent(rs.getString("notice_content"));
				noList.setNoticeDate(rs.getDate("notice_date"));
				noList.setNoticeFilenameOriginal(rs.getString("notice_filename_original"));
				noList.setNoticeFilenameRenamed(rs.getString("notice_filename_renamed"));
				noList.setNoticeImgnameOriginal(rs.getString("notice_imgname_original"));
				noList.setNoticeImgnameRenamed(rs.getString("notice_imgname_renamed"));
				noList.setNoticeReadcount(rs.getInt("notice_readcount"));
				noList.setNoticeStatus(rs.getString("notice_status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return noList;
	}
	
	public int insertWn(Connection conn, WeNotice wn) {
		PreparedStatement pstmt = null;
		int wnResult = 0;
		String sql = prop.getProperty("insertWn");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, wn.getNoticeNo());
			pstmt.setString(2, wn.getWeQuarter());
			pstmt.setDate(3, wn.getWeNoticeStartdate());
			pstmt.setDate(4, wn.getWeNoticeEnddate());

			wnResult = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return wnResult;
	}
	
	public List<WeNotice> weSelectAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<WeNotice> list = new ArrayList();
		String sql = prop.getProperty("weSelectAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				WeNotice wn = new WeNotice();
				wn.setWeNoticeNo(rs.getInt("we_notice_no"));
				wn.setNoticeNo(rs.getInt("notice_no"));
				wn.setWeQuarter(rs.getString("we_quarter"));
				wn.setWeNoticeStartdate(rs.getDate("we_notice_startdate"));
				wn.setWeNoticeEnddate(rs.getDate("we_notice_enddate"));
				wn.setWeNoticeStatus(rs.getString("we_notice_status"));
				list.add(wn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//작가신청 공지글 등록할때 날짜 최소값 구하기
	public Date minTime(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Date minTime = null;
		String sql = prop.getProperty("minTime");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				minTime = rs.getDate(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return minTime;
	}
	
	public WeNotice weSelectOne(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WeNotice wnList = null;
		String sql = prop.getProperty("weSelectOne");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				wnList = new WeNotice();
				wnList.setWeNoticeNo(rs.getInt("we_notice_no"));
				wnList.setNoticeNo(rs.getInt("notice_no"));
				wnList.setWeQuarter(rs.getString("we_quarter"));
				wnList.setWeNoticeStartdate(rs.getDate("we_notice_startdate"));
				wnList.setWeNoticeEnddate(rs.getDate("we_notice_enddate"));
				wnList.setWeNoticeStatus(rs.getString("we_notice_status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return wnList;
	}
	
	public WeNotice cuWeSelectOne(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WeNotice wnList = null;
		String sql = prop.getProperty("cuWeSelectOne");
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				wnList = new WeNotice();
				wnList.setWeNoticeNo(rs.getInt("we_notice_no"));
				wnList.setNoticeNo(rs.getInt("notice_no"));
				wnList.setWeQuarter(rs.getString("we_quarter"));
				wnList.setWeNoticeStartdate(rs.getDate("we_notice_startdate"));
				wnList.setWeNoticeEnddate(rs.getDate("we_notice_enddate"));
				wnList.setWeNoticeStatus(rs.getString("we_notice_status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return wnList;
	}
	
	public List<Notice> newLatestList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList();
		String sql = prop.getProperty("newLatestList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice no = new Notice();
				
				no.setNoticeNo(rs.getInt("notice_no"));
				no.setNoticeSort(rs.getString("notice_sort"));
				no.setNoticeTitle(rs.getString("notice_title"));
				no.setNoticeWriter(rs.getString("notice_writer"));
				no.setNoticeContent(rs.getString("notice_content"));
				no.setNoticeDate(rs.getDate("notice_date"));
				no.setNoticeFilenameOriginal(rs.getString("notice_filename_original"));
				no.setNoticeFilenameRenamed(rs.getString("notice_filename_renamed"));
				no.setNoticeImgnameOriginal(rs.getString("notice_imgname_original"));
				no.setNoticeImgnameRenamed(rs.getString("notice_imgname_renamed"));
				no.setNoticeReadcount(rs.getInt("notice_readcount"));
				no.setNoticeStatus(rs.getString("notice_status"));
				list.add(no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

}
