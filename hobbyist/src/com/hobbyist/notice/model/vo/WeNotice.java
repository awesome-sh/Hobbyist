package com.hobbyist.notice.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class WeNotice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1387177064380971103L;
	private int weNoticeNo;
	private int noticeNo;
	private String weQuarter;
	private Date weNoticeStartdate;
	private Date weNoticeEnddate;
	private String weNoticeStatus;
	
	public WeNotice() {
		// TODO Auto-generated constructor stub
	}

	public WeNotice(int weNoticeNo, int noticeNo, String weQuarter, Date weNoticeStartdate, Date weNoticeEnddate,
			String weNoticeStatus) {
		super();
		this.weNoticeNo = weNoticeNo;
		this.noticeNo = noticeNo;
		this.weQuarter = weQuarter;
		this.weNoticeStartdate = weNoticeStartdate;
		this.weNoticeEnddate = weNoticeEnddate;
		this.weNoticeStatus = weNoticeStatus;
	}

	public int getWeNoticeNo() {
		return weNoticeNo;
	}

	public void setWeNoticeNo(int weNoticeNo) {
		this.weNoticeNo = weNoticeNo;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getWeQuarter() {
		return weQuarter;
	}

	public void setWeQuarter(String weQuarter) {
		this.weQuarter = weQuarter;
	}

	public Date getWeNoticeStartdate() {
		return weNoticeStartdate;
	}

	public void setWeNoticeStartdate(Date weNoticeStartdate) {
		this.weNoticeStartdate = weNoticeStartdate;
	}

	public Date getWeNoticeEnddate() {
		return weNoticeEnddate;
	}

	public void setWeNoticeEnddate(Date weNoticeEnddate) {
		this.weNoticeEnddate = weNoticeEnddate;
	}

	public String getWeNoticeStatus() {
		return weNoticeStatus;
	}

	public void setWeNoticeStatus(String weNoticeStatus) {
		this.weNoticeStatus = weNoticeStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "WeNotice [weNoticeNo=" + weNoticeNo + ", noticeNo=" + noticeNo + ", weQuarter=" + weQuarter
				+ ", weNoticeStartdate=" + weNoticeStartdate + ", weNoticeEnddate=" + weNoticeEnddate
				+ ", weNoticeStatus=" + weNoticeStatus + "]";
	}
	
}