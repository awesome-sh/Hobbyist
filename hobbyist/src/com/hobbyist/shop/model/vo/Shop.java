package com.hobbyist.shop.model.vo;

import java.io.Serializable;
import java.util.Date;

public class Shop implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6000114118602600086L;
	private int shopNo;
	private String shopName;
	private String shopCate;
	private String shopInfo;
	private String shopWriter;
	private String shopContent;
	private int shopPrice;
	private int shopPoint;
	private String shopOption1;
	private String shopOption2;
	private String shopOption3;
	private String shopOption4;
	private String shopOption5;
	private String shopImage1;
	private String shopImage2;
	private String shopImage3;
	private String shopImage4;
	private String shopImage5;
	private String shopPolicy1;
	private String shopPolicy2;
	private String shopPolicy3;
	private Date shopDate;
	private String shopStatus;
	// 리뷰개수를 뽑아올 변수
	private int reviewCount;
	
	public Shop() {
	}

	public int getShopNo() {
		return shopNo;
	}

	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopCate() {
		return shopCate;
	}

	public void setShopCate(String shopCate) {
		this.shopCate = shopCate;
	}

	public String getShopInfo() {
		return shopInfo;
	}

	public void setShopInfo(String shopInfo) {
		this.shopInfo = shopInfo;
	}

	public String getShopWriter() {
		return shopWriter;
	}

	public void setShopWriter(String shopWriter) {
		this.shopWriter = shopWriter;
	}

	public String getShopContent() {
		return shopContent;
	}

	public void setShopContent(String shopContent) {
		this.shopContent = shopContent;
	}

	public int getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(int shopPrice) {
		this.shopPrice = shopPrice;
	}

	public int getShopPoint() {
		return shopPoint;
	}

	public void setShopPoint(int shopPoint) {
		this.shopPoint = shopPoint;
	}

	public String getShopOption1() {
		return shopOption1;
	}

	public void setShopOption1(String shopOption1) {
		this.shopOption1 = shopOption1;
	}

	public String getShopOption2() {
		return shopOption2;
	}

	public void setShopOption2(String shopOption2) {
		this.shopOption2 = shopOption2;
	}

	public String getShopOption3() {
		return shopOption3;
	}

	public void setShopOption3(String shopOption3) {
		this.shopOption3 = shopOption3;
	}

	public String getShopOption4() {
		return shopOption4;
	}

	public void setShopOption4(String shopOption4) {
		this.shopOption4 = shopOption4;
	}

	public String getShopOption5() {
		return shopOption5;
	}

	public void setShopOption5(String shopOption5) {
		this.shopOption5 = shopOption5;
	}

	public String getShopImage1() {
		return shopImage1;
	}

	public void setShopImage1(String shopImage1) {
		this.shopImage1 = shopImage1;
	}

	public String getShopImage2() {
		return shopImage2;
	}

	public void setShopImage2(String shopImage2) {
		this.shopImage2 = shopImage2;
	}

	public String getShopImage3() {
		return shopImage3;
	}

	public void setShopImage3(String shopImage3) {
		this.shopImage3 = shopImage3;
	}

	public String getShopImage4() {
		return shopImage4;
	}

	public void setShopImage4(String shopImage4) {
		this.shopImage4 = shopImage4;
	}

	public String getShopImage5() {
		return shopImage5;
	}

	public void setShopImage5(String shopImage5) {
		this.shopImage5 = shopImage5;
	}


	public String getShopPolicy1() {
		return shopPolicy1;
	}

	public void setShopPolicy1(String shopPolicy1) {
		this.shopPolicy1 = shopPolicy1;
	}

	public String getShopPolicy2() {
		return shopPolicy2;
	}

	public void setShopPolicy2(String shopPolicy2) {
		this.shopPolicy2 = shopPolicy2;
	}

	public String getShopPolicy3() {
		return shopPolicy3;
	}

	public void setShopPolicy3(String shopPolicy3) {
		this.shopPolicy3 = shopPolicy3;
	}

	public Date getShopDate() {
		return shopDate;
	}

	public void setShopDate(Date shopDate) {
		this.shopDate = shopDate;
	}

	public String getShopStatus() {
		return shopStatus;
	}

	public void setShopStatus(String shopStatus) {
		this.shopStatus = shopStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	
	
}
