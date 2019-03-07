package com.hobbyist.shop.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.hobbyist.member.model.vo.Member;
import com.hobbyist.shop.model.dao.ShopDao;
import com.hobbyist.shop.model.vo.Cate;
import com.hobbyist.shop.model.vo.Review;
import com.hobbyist.shop.model.vo.Shop;
import com.hobbyist.shop.model.vo.Study;

public class ShopService {
	
	// 기본 샵리스트 가져오기
	public List<Shop> shopList() {
		Connection conn = getConnection();
		List<Shop> list = new ShopDao().shopList(conn);
		close(conn);
		return list;
	}

	public int insertShop(Shop shop) {
		Connection conn = getConnection();
		int result = new ShopDao().insertShop(conn, shop);
		if(result>0) {
			commit(conn);
			result = new ShopDao().selectSeq(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	// 검색결과에 따른 갯수 가져오기
	public int searchCount(String keyword) {
		Connection conn = getConnection();
		int result = new ShopDao().searchCount(conn, keyword);
		close(conn);
		return result;
	}
	
	// 등록순 정렬
	public List<Shop> descEnroll(String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Shop> list = new ShopDao().descEnroll(conn, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	// 높은가격순
	public List<Shop> descPrice(String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Shop> list = new ShopDao().descPrice(conn, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}


	// 낮은가격순
	public List<Shop> ascPrice(String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Shop> list = new ShopDao().ascPrice(conn, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	// 반응형 ajax 검색결과
	public List<Shop> ajaxSearch(String keyword) {
		Connection conn = getConnection();
		List<Shop> list = new ShopDao().ajaxSearch(conn, keyword);
		close(conn);
		return list;
	}

	
	// 리뷰모두가져오기
		public List<Review> reviewListAll() {
			Connection conn = getConnection();
			List<Review> list = new ShopDao().reviewListAll(conn);
			close(conn);
			return list;
		}
	
	// 리뷰가져오기
	public List<Review> reviewList(int no,  int rPage, int rPerPage) {
		Connection conn = getConnection();
		List<Review> list = new ShopDao().reviewList(conn, no, rPage, rPerPage);
		close(conn);
		return list;
	}
	
	// 카테고리 조회
	public List<Cate> CateList() {
		Connection conn = getConnection();
		List<Cate> list = new ShopDao().CateList(conn);
		close(conn);
		return list;
	}
	
	// 카테고리 생성
	public int insertCate(String newCate) {
		Connection conn = getConnection();
		int result = new ShopDao().insertCate(conn, newCate);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public List<Shop> selectAll() {
		Connection conn = getConnection();
		List<Shop> list = new ShopDao().selectAll(conn);
		close(conn);
		return list;
	}

	public Shop selectOne(int no) {
		Connection conn = getConnection();
		Shop result = new ShopDao().selectOne(conn, no);
		close(conn);
		return result;
	}
	
	// 리뷰 작성 (Ajax)
	public List<Review> reviewInsert(Review rv, int rPage, int rPerPage) {
		Connection conn = getConnection();
		List<Review> list = null;
		int result = new ShopDao().reviewInsert(conn, rv);
		
		if(result>0) {
			System.out.println("리뷰작성 완료");
			commit(conn);
			list = new ShopDao().reviewList(conn, rv.getReviewClass(), rPage, rPerPage);
		} else {
			rollback(conn);
		}
		close(conn);
		return list;
	}

	public int reviewCount(int review_class) {
		Connection conn = getConnection();
		int count = new ShopDao().reviewCount(conn, review_class);
		close(conn);
		return count;
	}

	public List<Member> writerSearch(String classWriter) {
		Connection conn = getConnection();
		List<Member> list = new ShopDao().writerSearch(conn, classWriter);
		close(conn);
		return list;
	}

	public int reviewDelete(int no) {
		Connection conn = getConnection();
		int result = new ShopDao().reviewDelete(conn, no);
		close(conn);
		return result;
	}

	public int cateCount(String cate) {
		Connection conn = getConnection();
		int result = new ShopDao().cateCount(conn, cate);
		close(conn);
		return result;
	}

	public List<Shop> selectCateList(String cate, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Shop> list = new ShopDao().selectCateList(conn, cate, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int searchCateCount(String cate, String keyword) {
		Connection conn = getConnection();
		int result = new ShopDao().searchCateCount(conn, cate, keyword);
		close(conn);
		return result;
	}

	public List<Shop> descEnrollCate(String cate, String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Shop> list = new ShopDao().descEnrollCate(conn, cate, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Shop> descPriceCate(String cate, String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Shop> list = new ShopDao().descPriceCate(conn, cate, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Shop> ascPriceCate(String cate, String keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Shop> list = new ShopDao().ascPriceCate(conn, cate, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int updateShop(Shop shop) {
		Connection conn = getConnection();
		int result = new ShopDao().updateShop(conn, shop);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public List<Shop> bestClass() {
		Connection conn = getConnection();
		List<Shop> list = new ShopDao().bestClass(conn);
		close(conn);
		return list;
	}

	public int insertStudy(Study study) {
		Connection conn = getConnection();
		int result = new ShopDao().insertStudy(conn, study);
		close(conn);
		return result;
	}

	public Study selectStudyOne(int no) {
		Connection conn = getConnection();
		Study study = new ShopDao().selectStudyOne(conn, no);
		close(conn);
		return study;
	}

	public int deleteShop(int no) {
		Connection conn = getConnection();
		int result = new ShopDao().deleteShop(conn, no);
		close(conn);
		return result;
	}

}
