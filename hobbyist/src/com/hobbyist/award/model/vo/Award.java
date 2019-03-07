package com.hobbyist.award.model.vo;

import java.io.Serializable;
import java.util.Date;

public class Award implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2124888176955500333L;
	private int awardNo;
	private String awardName;
	private int likeCount;
	private String awardWriter;
	private String awardContent;
	private int readCount;
	private String awardImage1;
	private String awardImage2;
	private String awardImage3;
	private String awardImage4;
	private String awardImage5;
	private Date awardDate;
	private String awardStatus;
	private String awardOriginalFilename;
	private String awardRenamedFilename;
	
	
	public Award() {
		// TODO Auto-generated constructor stub
	}


	public Award(int awardNo, String awardName, int likeCount, String awardWriter, String awardContent, int readCount,
			String awardImage1, String awardImage2, String awardImage3, String awardImage4, String awardImage5,
			Date awardDate, String awardStatus, String awardOriginalFilename, String awardRenamedFilename) {
		super();
		this.awardNo = awardNo;
		this.awardName = awardName;
		this.likeCount = likeCount;
		this.awardWriter = awardWriter;
		this.awardContent = awardContent;
		this.readCount = readCount;
		this.awardImage1 = awardImage1;
		this.awardImage2 = awardImage2;
		this.awardImage3 = awardImage3;
		this.awardImage4 = awardImage4;
		this.awardImage5 = awardImage5;
		this.awardDate = awardDate;
		this.awardStatus = awardStatus;
		this.awardOriginalFilename = awardOriginalFilename;
		this.awardRenamedFilename = awardRenamedFilename;
	}


	public int getAwardNo() {
		return awardNo;
	}


	public void setAwardNo(int awardNo) {
		this.awardNo = awardNo;
	}


	public String getAwardName() {
		return awardName;
	}


	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}


	public int getLikeCount() {
		return likeCount;
	}


	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}


	public String getAwardWriter() {
		return awardWriter;
	}


	public void setAwardWriter(String awardWriter) {
		this.awardWriter = awardWriter;
	}


	public String getAwardContent() {
		return awardContent;
	}


	public void setAwardContent(String awardContent) {
		this.awardContent = awardContent;
	}


	public int getReadCount() {
		return readCount;
	}


	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}


	public String getAwardImage1() {
		return awardImage1;
	}


	public void setAwardImage1(String awardImage1) {
		this.awardImage1 = awardImage1;
	}


	public String getAwardImage2() {
		return awardImage2;
	}


	public void setAwardImage2(String awardImage2) {
		this.awardImage2 = awardImage2;
	}


	public String getAwardImage3() {
		return awardImage3;
	}


	public void setAwardImage3(String awardImage3) {
		this.awardImage3 = awardImage3;
	}


	public String getAwardImage4() {
		return awardImage4;
	}


	public void setAwardImage4(String awardImage4) {
		this.awardImage4 = awardImage4;
	}


	public String getAwardImage5() {
		return awardImage5;
	}


	public void setAwardImage5(String awardImage5) {
		this.awardImage5 = awardImage5;
	}


	public Date getAwardDate() {
		return awardDate;
	}


	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
	}


	public String getAwardStatus() {
		return awardStatus;
	}


	public void setAwardStatus(String awardStatus) {
		this.awardStatus = awardStatus;
	}


	public String getAwardOriginalFilename() {
		return awardOriginalFilename;
	}


	public void setAwardOriginalFilename(String awardOriginalFilename) {
		this.awardOriginalFilename = awardOriginalFilename;
	}


	public String getAwardRenamedFilename() {
		return awardRenamedFilename;
	}


	public void setAwardRenamedFilename(String awardRenamedFilename) {
		this.awardRenamedFilename = awardRenamedFilename;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Award [awardNo=" + awardNo + ", awardName=" + awardName + ", likeCount=" + likeCount + ", awardWriter="
				+ awardWriter + ", awardContent=" + awardContent + ", readCount=" + readCount + ", awardImage1="
				+ awardImage1 + ", awardImage2=" + awardImage2 + ", awardImage3=" + awardImage3 + ", awardImage4="
				+ awardImage4 + ", awardImage5=" + awardImage5 + ", awardDate=" + awardDate + ", awardStatus="
				+ awardStatus + ", awardOriginalFilename=" + awardOriginalFilename + ", awardRenamedFilename="
				+ awardRenamedFilename + "]";
	}

	
	
	
}