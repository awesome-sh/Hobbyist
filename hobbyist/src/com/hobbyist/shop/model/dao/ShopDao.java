package com.hobbyist.shop.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hobbyist.board.model.vo.Board;
import com.hobbyist.member.model.vo.Member;
import com.hobbyist.shop.model.vo.Cate;
import com.hobbyist.shop.model.vo.Review;
import com.hobbyist.shop.model.vo.Shop;
import com.hobbyist.shop.model.vo.Study;

public class ShopDao {

	static Properties prop = new Properties();

	public ShopDao() {
		String fileName = ShopDao.class.getResource("/sql/shop/shop.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 기본 샵 리스트 가져오기
	public List<Shop> shopList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Shop> list = new ArrayList<Shop>();

		try {
			String sql = prop.getProperty("shopList");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Shop shop = new Shop();
				shop.setShopNo(rs.getInt("shop_no"));
				shop.setShopName(rs.getString("shop_name"));
				shop.setShopCate(rs.getString("shop_cate"));
				shop.setShopInfo(rs.getString("shop_info"));
				shop.setShopWriter(rs.getString("shop_writer"));
				shop.setShopContent(rs.getString("shop_content"));
				shop.setShopPrice(rs.getInt("shop_price"));
				shop.setShopPoint(rs.getInt("shop_point"));
				shop.setShopOption1(rs.getString("shop_option1"));
				shop.setShopOption2(rs.getString("shop_option2"));
				shop.setShopOption3(rs.getString("shop_option3"));
				shop.setShopOption4(rs.getString("shop_option4"));
				shop.setShopOption5(rs.getString("shop_option5"));
				shop.setShopImage1(rs.getString("shop_image"));
				shop.setShopImage2(rs.getString("shop_image1"));
				shop.setShopImage3(rs.getString("shop_image2"));
				shop.setShopImage4(rs.getString("shop_image3"));
				shop.setShopImage5(rs.getString("shop_image4"));
				shop.setShopPolicy1(rs.getString("shop_policy1"));
				shop.setShopPolicy2(rs.getString("shop_policy2"));
				shop.setShopPolicy3(rs.getString("shop_policy3"));
				shop.setShopDate(rs.getDate("shop_date"));
				shop.setShopStatus(rs.getString("shop_status"));
				list.add(shop);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 검색결과에 따른 총개수 가져오기
	public int searchCount(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			String sql = prop.getProperty("searchCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 등록일순 정렬 리스트 가져오기
	public List<Shop> descEnroll(Connection conn, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Shop> list = new ArrayList<Shop>();

		try {
			String sql = prop.getProperty("descEnroll");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Shop shop = new Shop();
				shop.setShopNo(rs.getInt("shop_no"));
				shop.setShopName(rs.getString("shop_name"));
				shop.setShopCate(rs.getString("shop_cate"));
				shop.setShopInfo(rs.getString("shop_info"));
				shop.setShopWriter(rs.getString("shop_writer"));
				shop.setShopContent(rs.getString("shop_content"));
				shop.setShopPrice(rs.getInt("shop_price"));
				shop.setShopPoint(rs.getInt("shop_point"));
				shop.setShopOption1(rs.getString("shop_option1"));
				shop.setShopOption2(rs.getString("shop_option2"));
				shop.setShopOption3(rs.getString("shop_option3"));
				shop.setShopOption4(rs.getString("shop_option4"));
				shop.setShopOption5(rs.getString("shop_option5"));
				shop.setShopImage1(rs.getString("shop_image"));
				shop.setShopImage2(rs.getString("shop_image1"));
				shop.setShopImage3(rs.getString("shop_image2"));
				shop.setShopImage4(rs.getString("shop_image3"));
				shop.setShopImage5(rs.getString("shop_image4"));
				shop.setShopPolicy1(rs.getString("shop_policy1"));
				shop.setShopPolicy2(rs.getString("shop_policy2"));
				shop.setShopPolicy3(rs.getString("shop_policy3"));
				shop.setShopDate(rs.getDate("shop_date"));
				shop.setShopStatus(rs.getString("shop_status"));
				
				list.add(shop);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		pstmt = null;
		rs = null;
		
		try {
			String sql2 = prop.getProperty("ListReviewCount");
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			for(Shop shop : list) {
				rs.next();
				shop.setReviewCount(rs.getInt("review_count"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}


	// 높은가격순 정렬 리스트 가져오기
	public List<Shop> descPrice(Connection conn, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Shop> list = new ArrayList<Shop>();

		try {
			String sql = prop.getProperty("descPrice");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Shop shop = new Shop();
				shop.setShopNo(rs.getInt("shop_no"));
				shop.setShopName(rs.getString("shop_name"));
				shop.setShopCate(rs.getString("shop_cate"));
				shop.setShopInfo(rs.getString("shop_info"));
				shop.setShopWriter(rs.getString("shop_writer"));
				shop.setShopContent(rs.getString("shop_content"));
				shop.setShopPrice(rs.getInt("shop_price"));
				shop.setShopPoint(rs.getInt("shop_point"));
				shop.setShopOption1(rs.getString("shop_option1"));
				shop.setShopOption2(rs.getString("shop_option2"));
				shop.setShopOption3(rs.getString("shop_option3"));
				shop.setShopOption4(rs.getString("shop_option4"));
				shop.setShopOption5(rs.getString("shop_option5"));
				shop.setShopImage1(rs.getString("shop_image"));
				shop.setShopImage2(rs.getString("shop_image1"));
				shop.setShopImage3(rs.getString("shop_image2"));
				shop.setShopImage4(rs.getString("shop_image3"));
				shop.setShopImage5(rs.getString("shop_image4"));
				shop.setShopPolicy1(rs.getString("shop_policy1"));
				shop.setShopPolicy2(rs.getString("shop_policy2"));
				shop.setShopPolicy3(rs.getString("shop_policy3"));
				shop.setShopDate(rs.getDate("shop_date"));
				shop.setShopStatus(rs.getString("shop_status"));
				list.add(shop);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		pstmt = null;
		rs = null;
		
		try {
			String sql2 = prop.getProperty("ListReviewCount");
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			for(Shop shop : list) {
				rs.next();
				shop.setReviewCount(rs.getInt("review_count"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 낮은가격순 정렬 리스트 가져오기
	public List<Shop> ascPrice(Connection conn, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Shop> list = new ArrayList<Shop>();

		try {
			String sql = prop.getProperty("ascPrice");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Shop shop = new Shop();
				shop.setShopNo(rs.getInt("shop_no"));
				shop.setShopName(rs.getString("shop_name"));
				shop.setShopCate(rs.getString("shop_cate"));
				shop.setShopInfo(rs.getString("shop_info"));
				shop.setShopWriter(rs.getString("shop_writer"));
				shop.setShopContent(rs.getString("shop_content"));
				shop.setShopPrice(rs.getInt("shop_price"));
				shop.setShopPoint(rs.getInt("shop_point"));
				shop.setShopOption1(rs.getString("shop_option1"));
				shop.setShopOption2(rs.getString("shop_option2"));
				shop.setShopOption3(rs.getString("shop_option3"));
				shop.setShopOption4(rs.getString("shop_option4"));
				shop.setShopOption5(rs.getString("shop_option5"));
				shop.setShopImage1(rs.getString("shop_image"));
				shop.setShopImage2(rs.getString("shop_image1"));
				shop.setShopImage3(rs.getString("shop_image2"));
				shop.setShopImage4(rs.getString("shop_image3"));
				shop.setShopImage5(rs.getString("shop_image4"));
				shop.setShopPolicy1(rs.getString("shop_policy1"));
				shop.setShopPolicy2(rs.getString("shop_policy2"));
				shop.setShopPolicy3(rs.getString("shop_policy3"));
				shop.setShopDate(rs.getDate("shop_date"));
				shop.setShopStatus(rs.getString("shop_status"));
				list.add(shop);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		pstmt = null;
		rs = null;
		
		try {
			String sql2 = prop.getProperty("ListReviewCount");
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			for(Shop shop : list) {
				rs.next();
				shop.setReviewCount(rs.getInt("review_count"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 반응형 검색창
	public List<Shop> ajaxSearch(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Shop> list = new ArrayList<Shop>();

		try {
			String sql = prop.getProperty("ajaxSearch");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Shop shop = new Shop();
				shop.setShopNo(rs.getInt("shop_no"));
				shop.setShopName(rs.getString("shop_name"));
				shop.setShopCate(rs.getString("shop_cate"));
				shop.setShopPrice(rs.getInt("shop_price"));
				shop.setShopImage1(rs.getString("shop_image"));
				list.add(shop);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 모든리뷰 가져오기
	public List<Review> reviewListAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Review> list = new ArrayList<Review>();

		try {
			String sql = prop.getProperty("reviewListAll");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Review r = new Review();
				r.setReviewNo(rs.getInt("review_no"));
				r.setReviewClass(rs.getInt("review_class"));
				r.setReviewCate(rs.getString("review_cate"));
				r.setReviewWriter(rs.getString("review_writer"));
				r.setReviewTitle(rs.getString("review_title"));
				r.setReviewContent(rs.getString("review_content"));
				r.setReviewFilepath(rs.getString("review_filepath"));
				r.setReviewDate(rs.getDate("review_date"));
				r.setReviewCount(rs.getInt("review_count"));
				list.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;

	}

	// 리뷰 가져오기
	public List<Review> reviewList(Connection conn, int no,  int rPage, int rPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Review> list = new ArrayList<Review>();

		try {
			String sql = prop.getProperty("reviewList");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, (rPage-1)*rPerPage+1);
			pstmt.setInt(3, rPage*rPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Review r = new Review();
				r.setReviewNo(rs.getInt("review_no"));
				r.setReviewClass(rs.getInt("review_class"));
				r.setReviewCate(rs.getString("review_cate"));
				r.setReviewWriter(rs.getString("review_writer"));
				r.setReviewTitle(rs.getString("review_title"));
				r.setReviewContent(rs.getString("review_content"));
				r.setReviewFilepath(rs.getString("review_filepath"));
				r.setReviewDate(rs.getDate("review_date"));
				r.setReviewCount(rs.getInt("review_count"));
				list.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;

	}

	// 카테고리 불러오기
	public List<Cate> CateList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Cate> list = new ArrayList<Cate>();

		try {
			String sql = prop.getProperty("CateList");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Cate cate = new Cate();
				cate.setCateNo(rs.getInt(1));
				cate.setCateTitle(rs.getString(2));
				list.add(cate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 신규카테고리 생성
	public int insertCate(Connection conn, String newCate) {
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			String sql = prop.getProperty("insertCate");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newCate);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public Shop selectOne(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Shop shop = null;

		try {
			String sql = prop.getProperty("selectOne");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				shop = new Shop();
				shop.setShopNo(rs.getInt(1));
				shop.setShopName(rs.getString(2));
				shop.setShopCate(rs.getString(3));
				shop.setShopInfo(rs.getString(4));
				shop.setShopWriter(rs.getString(5));
				shop.setShopContent(rs.getString(6));
				shop.setShopPrice(rs.getInt(7));
				shop.setShopPoint(rs.getInt(8));
				shop.setShopOption1(rs.getString(9));
				shop.setShopOption2(rs.getString(10));
				shop.setShopOption3(rs.getString(11));
				shop.setShopOption4(rs.getString(12));
				shop.setShopOption5(rs.getString(13));
				shop.setShopImage1(rs.getString(14));
				shop.setShopImage2(rs.getString(15));
				shop.setShopImage3(rs.getString(16));
				shop.setShopImage4(rs.getString(17));
				shop.setShopImage5(rs.getString(18));
				shop.setShopPolicy1(rs.getString(19));
				shop.setShopPolicy2(rs.getString(20));
				shop.setShopPolicy3(rs.getString(21));
				shop.setShopDate(rs.getDate(22));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return shop;
	}

	public List<Shop> selectAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Shop> list = new ArrayList<Shop>();

		try {
			String sql = prop.getProperty("selectAll");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Shop shop = new Shop();
				shop.setShopNo(rs.getInt("shop_no"));
				shop.setShopName(rs.getString("shop_name"));
				shop.setShopCate(rs.getString("shop_cate"));
				shop.setShopInfo(rs.getString("shop_info"));
				shop.setShopWriter(rs.getString("shop_writer"));
				shop.setShopContent(rs.getString("shop_content"));
				shop.setShopPrice(rs.getInt("shop_price"));
				shop.setShopPoint(rs.getInt("shop_point"));
				shop.setShopOption1(rs.getString("shop_option1"));
				shop.setShopOption2(rs.getString("shop_option2"));
				shop.setShopOption3(rs.getString("shop_option3"));
				shop.setShopOption4(rs.getString("shop_option4"));
				shop.setShopOption5(rs.getString("shop_option5"));
				shop.setShopImage1(rs.getString("shop_image"));
				shop.setShopImage2(rs.getString("shop_image1"));
				shop.setShopImage3(rs.getString("shop_image2"));
				shop.setShopImage4(rs.getString("shop_image3"));
				shop.setShopImage5(rs.getString("shop_image4"));
				shop.setShopPolicy1(rs.getString("shop_policy1"));
				shop.setShopPolicy2(rs.getString("shop_policy2"));
				shop.setShopPolicy3(rs.getString("shop_policy3"));
				shop.setShopDate(rs.getDate("shop_date"));
				shop.setShopStatus(rs.getString("shop_status"));
				list.add(shop);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int insertShop(Connection conn, Shop shop) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = prop.getProperty("insertShop");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shop.getShopName());
			pstmt.setString(2, shop.getShopCate());
			pstmt.setString(3, shop.getShopInfo());
			pstmt.setString(4, shop.getShopWriter());
			pstmt.setString(5, 	shop.getShopContent());
			pstmt.setInt(6, shop.getShopPrice());
			pstmt.setInt(7, shop.getShopPoint());
			pstmt.setString(8, shop.getShopOption1());
			pstmt.setString(9, shop.getShopOption2());
			pstmt.setString(10, shop.getShopOption3());
			pstmt.setString(11, shop.getShopOption4());
			pstmt.setString(12, shop.getShopOption5());
			pstmt.setString(13, shop.getShopImage1());
			pstmt.setString(14, shop.getShopImage2());
			pstmt.setString(15, shop.getShopImage3());
			pstmt.setString(16, shop.getShopImage4());
			pstmt.setString(17, shop.getShopImage5());
			pstmt.setString(18, shop.getShopPolicy1());
			pstmt.setString(19, shop.getShopPolicy2());
			pstmt.setString(20, shop.getShopPolicy3());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}


	// 리뷰작성
	public int reviewInsert(Connection conn, Review rv) {
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			String sql = prop.getProperty("reviewInsert");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rv.getReviewClass());
			pstmt.setString(2, rv.getReviewCate());
			pstmt.setString(3, rv.getReviewWriter());
			pstmt.setString(4, rv.getReviewTitle());
			pstmt.setString(5, rv.getReviewContent());
			pstmt.setString(6, rv.getReviewFilepath());
			pstmt.setInt(7, rv.getReviewCount());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	// 해당 상품 리뷰 카운트
	public int reviewCount(Connection conn, int review_class) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			String sql = prop.getProperty("reviewCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_class);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public List<Member> writerSearch(Connection conn, String classWriter) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<Member>();

		try {
			String sql = prop.getProperty("writerSearch");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + classWriter + "%");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Member member = new Member();
				member.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int reviewDelete(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("reviewDelete");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int cateCount(Connection conn, String cate) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			String sql = prop.getProperty("cateCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public List<Shop> selectCateList(Connection conn, String cate, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Shop> list = new ArrayList<Shop>();

		try {
			String sql = prop.getProperty("selectCateList");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Shop shop = new Shop();
				shop.setShopNo(rs.getInt("shop_no"));
				shop.setShopName(rs.getString("shop_name"));
				shop.setShopCate(rs.getString("shop_cate"));
				shop.setShopInfo(rs.getString("shop_info"));
				shop.setShopWriter(rs.getString("shop_writer"));
				shop.setShopContent(rs.getString("shop_content"));
				shop.setShopPrice(rs.getInt("shop_price"));
				shop.setShopPoint(rs.getInt("shop_point"));
				shop.setShopOption1(rs.getString("shop_option1"));
				shop.setShopOption2(rs.getString("shop_option2"));
				shop.setShopOption3(rs.getString("shop_option3"));
				shop.setShopOption4(rs.getString("shop_option4"));
				shop.setShopOption5(rs.getString("shop_option5"));
				shop.setShopImage1(rs.getString("shop_image"));
				shop.setShopImage2(rs.getString("shop_image1"));
				shop.setShopImage3(rs.getString("shop_image2"));
				shop.setShopImage4(rs.getString("shop_image3"));
				shop.setShopImage5(rs.getString("shop_image4"));
				shop.setShopPolicy1(rs.getString("shop_policy1"));
				shop.setShopPolicy2(rs.getString("shop_policy2"));
				shop.setShopPolicy3(rs.getString("shop_policy3"));
				shop.setShopDate(rs.getDate("shop_date"));
				shop.setShopStatus(rs.getString("shop_status"));
				list.add(shop);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int searchCateCount(Connection conn, String cate, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("searchCateCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			pstmt.setString(2, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public List<Shop> descEnrollCate(Connection conn, String cate, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Shop> list = new ArrayList<Shop>();

		try {
			String sql = prop.getProperty("descEnrollCate");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Shop shop = new Shop();
				shop.setShopNo(rs.getInt("shop_no"));
				shop.setShopName(rs.getString("shop_name"));
				shop.setShopCate(rs.getString("shop_cate"));
				shop.setShopInfo(rs.getString("shop_info"));
				shop.setShopWriter(rs.getString("shop_writer"));
				shop.setShopContent(rs.getString("shop_content"));
				shop.setShopPrice(rs.getInt("shop_price"));
				shop.setShopPoint(rs.getInt("shop_point"));
				shop.setShopOption1(rs.getString("shop_option1"));
				shop.setShopOption2(rs.getString("shop_option2"));
				shop.setShopOption3(rs.getString("shop_option3"));
				shop.setShopOption4(rs.getString("shop_option4"));
				shop.setShopOption5(rs.getString("shop_option5"));
				shop.setShopImage1(rs.getString("shop_image"));
				shop.setShopImage2(rs.getString("shop_image1"));
				shop.setShopImage3(rs.getString("shop_image2"));
				shop.setShopImage4(rs.getString("shop_image3"));
				shop.setShopImage5(rs.getString("shop_image4"));
				shop.setShopPolicy1(rs.getString("shop_policy1"));
				shop.setShopPolicy2(rs.getString("shop_policy2"));
				shop.setShopPolicy3(rs.getString("shop_policy3"));
				shop.setShopDate(rs.getDate("shop_date"));
				shop.setShopStatus(rs.getString("shop_status"));
				list.add(shop);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		pstmt = null;
		rs = null;
		
		try {
			String sql2 = prop.getProperty("ListReviewCount");
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			for(Shop shop : list) {
				rs.next();
				shop.setReviewCount(rs.getInt("review_count"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<Shop> ascPriceCate(Connection conn, String cate, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Shop> list = new ArrayList<Shop>();

		try {
			String sql = prop.getProperty("ascPriceCate");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Shop shop = new Shop();
				shop.setShopNo(rs.getInt("shop_no"));
				shop.setShopName(rs.getString("shop_name"));
				shop.setShopCate(rs.getString("shop_cate"));
				shop.setShopInfo(rs.getString("shop_info"));
				shop.setShopWriter(rs.getString("shop_writer"));
				shop.setShopContent(rs.getString("shop_content"));
				shop.setShopPrice(rs.getInt("shop_price"));
				shop.setShopPoint(rs.getInt("shop_point"));
				shop.setShopOption1(rs.getString("shop_option1"));
				shop.setShopOption2(rs.getString("shop_option2"));
				shop.setShopOption3(rs.getString("shop_option3"));
				shop.setShopOption4(rs.getString("shop_option4"));
				shop.setShopOption5(rs.getString("shop_option5"));
				shop.setShopImage1(rs.getString("shop_image"));
				shop.setShopImage2(rs.getString("shop_image1"));
				shop.setShopImage3(rs.getString("shop_image2"));
				shop.setShopImage4(rs.getString("shop_image3"));
				shop.setShopImage5(rs.getString("shop_image4"));
				shop.setShopPolicy1(rs.getString("shop_policy1"));
				shop.setShopPolicy2(rs.getString("shop_policy2"));
				shop.setShopPolicy3(rs.getString("shop_policy3"));
				shop.setShopDate(rs.getDate("shop_date"));
				shop.setShopStatus(rs.getString("shop_status"));
				list.add(shop);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		pstmt = null;
		rs = null;
		
		try {
			String sql2 = prop.getProperty("ListReviewCount");
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			for(Shop shop : list) {
				rs.next();
				shop.setReviewCount(rs.getInt("review_count"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<Shop> descPriceCate(Connection conn, String cate, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Shop> list = new ArrayList<Shop>();

		try {
			String sql = prop.getProperty("descPriceCate");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Shop shop = new Shop();
				shop.setShopNo(rs.getInt("shop_no"));
				shop.setShopName(rs.getString("shop_name"));
				shop.setShopCate(rs.getString("shop_cate"));
				shop.setShopInfo(rs.getString("shop_info"));
				shop.setShopWriter(rs.getString("shop_writer"));
				shop.setShopContent(rs.getString("shop_content"));
				shop.setShopPrice(rs.getInt("shop_price"));
				shop.setShopPoint(rs.getInt("shop_point"));
				shop.setShopOption1(rs.getString("shop_option1"));
				shop.setShopOption2(rs.getString("shop_option2"));
				shop.setShopOption3(rs.getString("shop_option3"));
				shop.setShopOption4(rs.getString("shop_option4"));
				shop.setShopOption5(rs.getString("shop_option5"));
				shop.setShopImage1(rs.getString("shop_image"));
				shop.setShopImage2(rs.getString("shop_image1"));
				shop.setShopImage3(rs.getString("shop_image2"));
				shop.setShopImage4(rs.getString("shop_image3"));
				shop.setShopImage5(rs.getString("shop_image4"));
				shop.setShopPolicy1(rs.getString("shop_policy1"));
				shop.setShopPolicy2(rs.getString("shop_policy2"));
				shop.setShopPolicy3(rs.getString("shop_policy3"));
				shop.setShopDate(rs.getDate("shop_date"));
				shop.setShopStatus(rs.getString("shop_status"));
				list.add(shop);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		pstmt = null;
		rs = null;
		
		try {
			String sql2 = prop.getProperty("ListReviewCount");
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			for(Shop shop : list) {
				rs.next();
				shop.setReviewCount(rs.getInt("review_count"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int updateShop(Connection conn, Shop shop) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateShop");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shop.getShopName());
			pstmt.setString(2, shop.getShopCate());
			pstmt.setString(3, shop.getShopInfo());
			pstmt.setString(4, shop.getShopWriter());
			pstmt.setString(5, shop.getShopContent());
			pstmt.setInt(6, shop.getShopPrice());
			pstmt.setInt(7, shop.getShopPoint());
			pstmt.setString(8, shop.getShopOption1());
			pstmt.setString(9, shop.getShopOption2());
			pstmt.setString(10, shop.getShopOption3());
			pstmt.setString(11, shop.getShopOption4());
			pstmt.setString(12, shop.getShopOption5());
			pstmt.setString(13, shop.getShopImage1());
			pstmt.setString(14, shop.getShopImage2());
			pstmt.setString(15, shop.getShopImage3());
			pstmt.setString(16, shop.getShopImage4());
			pstmt.setString(17, shop.getShopImage5());
			pstmt.setString(18, shop.getShopPolicy1());
			pstmt.setString(19, shop.getShopPolicy2());
			pstmt.setString(20, shop.getShopPolicy3());
			pstmt.setInt(21, shop.getShopNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Shop> bestClass(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Shop> list = new ArrayList<Shop>();
		
		try {
			String sql = prop.getProperty("bestClass");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			Shop shop = new Shop();
			shop.setShopNo(rs.getInt("shop_no"));
			shop.setShopImage1(rs.getString("shop_image"));
			shop.setReviewCount(rs.getInt("CNT"));
			list.add(shop);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int selectSeq(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			String sql = prop.getProperty("selectSeq");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int insertStudy(Connection conn, Study study) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertStudy");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, study.getStudyClass());
			pstmt.setString(2, study.getStudyWriter());
			pstmt.setString(3, study.getStudyTitle());
			pstmt.setString(4, study.getStudySubTitle());
			pstmt.setString(5, study.getStudyVideo());
			pstmt.setString(6, study.getStudyContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Study selectStudyOne(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Study study = null;
		try {
			String sql = prop.getProperty("selectStudyOne");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				study = new Study();
				study.setStudyNo(rs.getInt("study_no"));
				study.setStudyClass(rs.getInt("study_class"));
				study.setStudyWriter(rs.getString("study_writer"));
				study.setStudyTitle(rs.getString("study_title"));
				study.setStudySubTitle(rs.getString("study_subtitle"));
				study.setStudyVideo(rs.getString("study_video"));
				study.setStudyContent(rs.getString("study_content"));
				study.setStudyStatus(rs.getString("study_status"));
				study.setStudyDate(rs.getDate("study_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return study;
	}

	public int deleteShop(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteShop");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
