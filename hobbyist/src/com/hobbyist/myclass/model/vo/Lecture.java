package com.hobbyist.myclass.model.vo;

import java.util.Date;

public class Lecture {
	
	private int lectureNo;
	private int lectureClass;
	private String lectureWriter;
	private int lectureTime;
	private String lectureTitle;
	private String lectureInfo;
	private String lectureContent;
	private String lectureVideo;
	private Date lectureDate;
	
	public Lecture() {
		// TODO Auto-generated constructor stub
	}

	public Lecture(int lectureNo, int lectureClass, String lectureWriter, int lectureTime, String lectureTitle,
			String lectureInfo, String lectureContent, String lectureVideo, Date lectureDate) {
		super();
		this.lectureNo = lectureNo;
		this.lectureClass = lectureClass;
		this.lectureWriter = lectureWriter;
		this.lectureTime = lectureTime;
		this.lectureTitle = lectureTitle;
		this.lectureInfo = lectureInfo;
		this.lectureContent = lectureContent;
		this.lectureVideo = lectureVideo;
		this.lectureDate = lectureDate;
	}

	public int getLectureNo() {
		return lectureNo;
	}

	public void setLectureNo(int lectureNo) {
		this.lectureNo = lectureNo;
	}

	public int getLectureClass() {
		return lectureClass;
	}

	public void setLectureClass(int lectureClass) {
		this.lectureClass = lectureClass;
	}

	public String getLectureWriter() {
		return lectureWriter;
	}

	public void setLectureWriter(String lectureWriter) {
		this.lectureWriter = lectureWriter;
	}

	public int getLectureTime() {
		return lectureTime;
	}

	public void setLectureTime(int lectureTime) {
		this.lectureTime = lectureTime;
	}

	public String getLectureTitle() {
		return lectureTitle;
	}

	public void setLectureTitle(String lectureTitle) {
		this.lectureTitle = lectureTitle;
	}

	public String getLectureInfo() {
		return lectureInfo;
	}

	public void setLectureInfo(String lectureInfo) {
		this.lectureInfo = lectureInfo;
	}

	public String getLectureContent() {
		return lectureContent;
	}

	public void setLectureContent(String lectureContent) {
		this.lectureContent = lectureContent;
	}

	public String getLectureVideo() {
		return lectureVideo;
	}

	public void setLectureVideo(String lectureVideo) {
		this.lectureVideo = lectureVideo;
	}

	public Date getLectureDate() {
		return lectureDate;
	}

	public void setLectureDate(Date lectureDate) {
		this.lectureDate = lectureDate;
	}
	
	
	
}
