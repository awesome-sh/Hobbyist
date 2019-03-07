package com.hobbyist.shop.model.vo;

import java.util.Date;

public class Review {

	private int reviewNo;
	private int reviewClass;
	private String reviewCate;
	private String reviewWriter;
	private String reviewTitle;
	private String reviewContent;
	private String reviewFilepath;
	private int reviewCount;
	private Date reviewDate;
	private String reviewStatus;
	
	public Review() {
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public int getReviewClass() {
		return reviewClass;
	}

	public void setReviewClass(int reviewClass) {
		this.reviewClass = reviewClass;
	}

	public String getReviewCate() {
		return reviewCate;
	}

	public void setReviewCate(String reviewCate) {
		this.reviewCate = reviewCate;
	}

	public String getReviewWriter() {
		return reviewWriter;
	}

	public void setReviewWriter(String reviewWriter) {
		this.reviewWriter = reviewWriter;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewFilepath() {
		return reviewFilepath;
	}

	public void setReviewFilepath(String reviewFilepath) {
		this.reviewFilepath = reviewFilepath;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	
	
}
