package com.hobbyist.award.model.vo;

import java.io.Serializable;
import java.util.Date;

public class AwardComment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5568088189523784428L;
	private int awardCommentNo;
	private int awardCommentLevel;
	private String awardCommentWriter;
	private String awardCommentContent;
	private int awardRef;
	private int awardCommentRef;
	private Date awardCommentDate;
	private String awardCommentStatus;
	
	public AwardComment() {}

	public AwardComment(int awardCommentNo, int awardCommentLevel, String awardCommentWriter,
			String awardCommentContent, int awardRef, int awardCommentRef, Date awardCommentDate,
			String awardCommentStatus) {
		super();
		this.awardCommentNo = awardCommentNo;
		this.awardCommentLevel = awardCommentLevel;
		this.awardCommentWriter = awardCommentWriter;
		this.awardCommentContent = awardCommentContent;
		this.awardRef = awardRef;
		this.awardCommentRef = awardCommentRef;
		this.awardCommentDate = awardCommentDate;
		this.awardCommentStatus = awardCommentStatus;
	}

	public int getAwardCommentNo() {
		return awardCommentNo;
	}

	public void setAwardCommentNo(int awardCommentNo) {
		this.awardCommentNo = awardCommentNo;
	}

	public int getAwardCommentLevel() {
		return awardCommentLevel;
	}

	public void setAwardCommentLevel(int awardCommentLevel) {
		this.awardCommentLevel = awardCommentLevel;
	}

	public String getAwardCommentWriter() {
		return awardCommentWriter;
	}

	public void setAwardCommentWriter(String awardCommentWriter) {
		this.awardCommentWriter = awardCommentWriter;
	}

	public String getAwardCommentContent() {
		return awardCommentContent;
	}

	public void setAwardCommentContent(String awardCommentContent) {
		this.awardCommentContent = awardCommentContent;
	}

	public int getAwardRef() {
		return awardRef;
	}

	public void setAwardRef(int awardRef) {
		this.awardRef = awardRef;
	}

	public int getAwardCommentRef() {
		return awardCommentRef;
	}

	public void setAwardCommentRef(int awardCommentRef) {
		this.awardCommentRef = awardCommentRef;
	}

	public Date getAwardCommentDate() {
		return awardCommentDate;
	}

	public void setAwardCommentDate(Date awardCommentDate) {
		this.awardCommentDate = awardCommentDate;
	}

	public String getAwardCommentStatus() {
		return awardCommentStatus;
	}

	public void setAwardCommentStatus(String awardCommentStatus) {
		this.awardCommentStatus = awardCommentStatus;
	}

	@Override
	public String toString() {
		return "AwardComment [awardCommentNo=" + awardCommentNo + ", awardCommentLevel=" + awardCommentLevel
				+ ", awardCommentWriter=" + awardCommentWriter + ", awardCommentContent=" + awardCommentContent
				+ ", awardRef=" + awardRef + ", awardCommentRef=" + awardCommentRef + ", awardCommentDate="
				+ awardCommentDate + ", awardCommentStatus=" + awardCommentStatus + "]";
	}

	
	
}
