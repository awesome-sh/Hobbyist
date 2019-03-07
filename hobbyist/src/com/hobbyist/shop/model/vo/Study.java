package com.hobbyist.shop.model.vo;

import java.io.Serializable;
import java.util.Date;

public class Study implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2715285899330080205L;
	private int studyNo;
	private int studyClass;
	private String studyWriter;
	private String studyTitle;
	private String studySubTitle;
	private String studyVideo;
	private String studyContent;
	private String studyImage1;
	private String studyImage2;
	private String studyImage3;
	private String studyStatus;
	private Date studyDate;
	
	public Study() {
		// TODO Auto-generated constructor stub
	}
	
	public Study(int studyNo, int studyClass, String studyWriter, String studyTitle, String studySubTitle,
			String studyVideo, String studyContent, String studyImage1, String studyImage2, String studyImage3,
			String studyStatus, Date studyDate) {
		super();
		this.studyNo = studyNo;
		this.studyClass = studyClass;
		this.studyWriter = studyWriter;
		this.studyTitle = studyTitle;
		this.studySubTitle = studySubTitle;
		this.studyVideo = studyVideo;
		this.studyContent = studyContent;
		this.studyImage1 = studyImage1;
		this.studyImage2 = studyImage2;
		this.studyImage3 = studyImage3;
		this.studyStatus = studyStatus;
		this.studyDate = studyDate;
	}
	
	public int getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(int studyNo) {
		this.studyNo = studyNo;
	}
	public int getStudyClass() {
		return studyClass;
	}
	public void setStudyClass(int studyClass) {
		this.studyClass = studyClass;
	}
	public String getStudyWriter() {
		return studyWriter;
	}
	public void setStudyWriter(String studyWriter) {
		this.studyWriter = studyWriter;
	}
	public String getStudyTitle() {
		return studyTitle;
	}
	public void setStudyTitle(String studyTitle) {
		this.studyTitle = studyTitle;
	}
	public String getStudySubTitle() {
		return studySubTitle;
	}
	public void setStudySubTitle(String studySubTitle) {
		this.studySubTitle = studySubTitle;
	}
	public String getStudyVideo() {
		return studyVideo;
	}
	public void setStudyVideo(String studyVideo) {
		this.studyVideo = studyVideo;
	}
	public String getStudyContent() {
		return studyContent;
	}
	public void setStudyContent(String studyContent) {
		this.studyContent = studyContent;
	}
	public String getStudyImage1() {
		return studyImage1;
	}
	public void setStudyImage1(String studyImage1) {
		this.studyImage1 = studyImage1;
	}
	public String getStudyImage2() {
		return studyImage2;
	}
	public void setStudyImage2(String studyImage2) {
		this.studyImage2 = studyImage2;
	}
	public String getStudyImage3() {
		return studyImage3;
	}
	public void setStudyImage3(String studyImage3) {
		this.studyImage3 = studyImage3;
	}
	public String getStudyStatus() {
		return studyStatus;
	}
	public void setStudyStatus(String studyStatus) {
		this.studyStatus = studyStatus;
	}
	public Date getStudyDate() {
		return studyDate;
	}
	public void setStudyDate(Date studyDate) {
		this.studyDate = studyDate;
	}
	
	
	
}
