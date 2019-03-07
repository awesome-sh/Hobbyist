package com.hobbyist.writer.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class WriterEnroll implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2612141306891154127L;
	private int writerEnrollNo;
	private int memberNo;
	private String memberProfileImg;
	private String memberNickname;
	private String memberEmail;
	private String memberName;
	private String memberBirthday;
	private String memberPhone;
	private String writerEnrollQuarter;
	private String writerCategory;
	private String writerAddress;
	private String writerReason;
	private String writerMajorImgfileOriginal;
	private String writerMajorImgfileRenamed;
	private String writerScheduleYN;
	private String writerContractYN;
	private String writerWishMonth;
	private String writerClassName;
	private String writerClassSelectReason;
	private int writerClassLevel;
	private String writerProductTime;
	private String writerClassKitWarningPoint;
	private String writerClassKitPart;
	private String classImgfileOriginal;
	private String classImgfileRenamed;
	private String writerPrepRequestYN;
	private String writerFinalPoint;
	private Date writerEnrolldate;
	private String writerPassYN;
	private String writerStatus;
	
	public WriterEnroll() {
		// TODO Auto-generated constructor stub
	}

	public WriterEnroll(int writerEnrollNo, int memberNo, String memberProfileImg, String memberNickname,
			String memberEmail, String memberName, String memberBirthday, String memberPhone,
			String writerEnrollQuarter, String writerCategory, String writerAddress, String writerReason,
			String writerMajorImgfileOriginal, String writerMajorImgfileRenamed, String writerScheduleYN,
			String writerContractYN, String writerWishMonth, String writerClassName, String writerClassSelectReason,
			int writerClassLevel, String writerProductTime, String writerClassKitWarningPoint,
			String writerClassKitPart, String classImgfileOriginal, String classImgfileRenamed,
			String writerPrepRequestYN, String writerFinalPoint, Date writerEnrolldate, String writerPassYN,
			String writerStatus) {
		super();
		this.writerEnrollNo = writerEnrollNo;
		this.memberNo = memberNo;
		this.memberProfileImg = memberProfileImg;
		this.memberNickname = memberNickname;
		this.memberEmail = memberEmail;
		this.memberName = memberName;
		this.memberBirthday = memberBirthday;
		this.memberPhone = memberPhone;
		this.writerEnrollQuarter = writerEnrollQuarter;
		this.writerCategory = writerCategory;
		this.writerAddress = writerAddress;
		this.writerReason = writerReason;
		this.writerMajorImgfileOriginal = writerMajorImgfileOriginal;
		this.writerMajorImgfileRenamed = writerMajorImgfileRenamed;
		this.writerScheduleYN = writerScheduleYN;
		this.writerContractYN = writerContractYN;
		this.writerWishMonth = writerWishMonth;
		this.writerClassName = writerClassName;
		this.writerClassSelectReason = writerClassSelectReason;
		this.writerClassLevel = writerClassLevel;
		this.writerProductTime = writerProductTime;
		this.writerClassKitWarningPoint = writerClassKitWarningPoint;
		this.writerClassKitPart = writerClassKitPart;
		this.classImgfileOriginal = classImgfileOriginal;
		this.classImgfileRenamed = classImgfileRenamed;
		this.writerPrepRequestYN = writerPrepRequestYN;
		this.writerFinalPoint = writerFinalPoint;
		this.writerEnrolldate = writerEnrolldate;
		this.writerPassYN = writerPassYN;
		this.writerStatus = writerStatus;
	}

	public int getWriterEnrollNo() {
		return writerEnrollNo;
	}

	public void setWriterEnrollNo(int writerEnrollNo) {
		this.writerEnrollNo = writerEnrollNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberProfileImg() {
		return memberProfileImg;
	}

	public void setMemberProfileImg(String memberProfileImg) {
		this.memberProfileImg = memberProfileImg;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberBirthday() {
		return memberBirthday;
	}

	public void setMemberBirthday(String memberBirthday) {
		this.memberBirthday = memberBirthday;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getWriterEnrollQuarter() {
		return writerEnrollQuarter;
	}

	public void setWriterEnrollQuarter(String writerEnrollQuarter) {
		this.writerEnrollQuarter = writerEnrollQuarter;
	}

	public String getWriterCategory() {
		return writerCategory;
	}

	public void setWriterCategory(String writerCategory) {
		this.writerCategory = writerCategory;
	}

	public String getWriterAddress() {
		return writerAddress;
	}

	public void setWriterAddress(String writerAddress) {
		this.writerAddress = writerAddress;
	}

	public String getWriterReason() {
		return writerReason;
	}

	public void setWriterReason(String writerReason) {
		this.writerReason = writerReason;
	}

	public String getWriterMajorImgfileOriginal() {
		return writerMajorImgfileOriginal;
	}

	public void setWriterMajorImgfileOriginal(String writerMajorImgfileOriginal) {
		this.writerMajorImgfileOriginal = writerMajorImgfileOriginal;
	}

	public String getWriterMajorImgfileRenamed() {
		return writerMajorImgfileRenamed;
	}

	public void setWriterMajorImgfileRenamed(String writerMajorImgfileRenamed) {
		this.writerMajorImgfileRenamed = writerMajorImgfileRenamed;
	}

	public String getWriterScheduleYN() {
		return writerScheduleYN;
	}

	public void setWriterScheduleYN(String writerScheduleYN) {
		this.writerScheduleYN = writerScheduleYN;
	}

	public String getWriterContractYN() {
		return writerContractYN;
	}

	public void setWriterContractYN(String writerContractYN) {
		this.writerContractYN = writerContractYN;
	}

	public String getWriterWishMonth() {
		return writerWishMonth;
	}

	public void setWriterWishMonth(String writerWishMonth) {
		this.writerWishMonth = writerWishMonth;
	}

	public String getWriterClassName() {
		return writerClassName;
	}

	public void setWriterClassName(String writerClassName) {
		this.writerClassName = writerClassName;
	}

	public String getWriterClassSelectReason() {
		return writerClassSelectReason;
	}

	public void setWriterClassSelectReason(String writerClassSelectReason) {
		this.writerClassSelectReason = writerClassSelectReason;
	}

	public int getWriterClassLevel() {
		return writerClassLevel;
	}

	public void setWriterClassLevel(int writerClassLevel) {
		this.writerClassLevel = writerClassLevel;
	}

	public String getWriterProductTime() {
		return writerProductTime;
	}

	public void setWriterProductTime(String writerProductTime) {
		this.writerProductTime = writerProductTime;
	}

	public String getWriterClassKitWarningPoint() {
		return writerClassKitWarningPoint;
	}

	public void setWriterClassKitWarningPoint(String writerClassKitWarningPoint) {
		this.writerClassKitWarningPoint = writerClassKitWarningPoint;
	}

	public String getWriterClassKitPart() {
		return writerClassKitPart;
	}

	public void setWriterClassKitPart(String writerClassKitPart) {
		this.writerClassKitPart = writerClassKitPart;
	}

	public String getClassImgfileOriginal() {
		return classImgfileOriginal;
	}

	public void setClassImgfileOriginal(String classImgfileOriginal) {
		this.classImgfileOriginal = classImgfileOriginal;
	}

	public String getClassImgfileRenamed() {
		return classImgfileRenamed;
	}

	public void setClassImgfileRenamed(String classImgfileRenamed) {
		this.classImgfileRenamed = classImgfileRenamed;
	}

	public String getWriterPrepRequestYN() {
		return writerPrepRequestYN;
	}

	public void setWriterPrepRequestYN(String writerPrepRequestYN) {
		this.writerPrepRequestYN = writerPrepRequestYN;
	}

	public String getWriterFinalPoint() {
		return writerFinalPoint;
	}

	public void setWriterFinalPoint(String writerFinalPoint) {
		this.writerFinalPoint = writerFinalPoint;
	}

	public Date getWriterEnrolldate() {
		return writerEnrolldate;
	}

	public void setWriterEnrolldate(Date writerEnrolldate) {
		this.writerEnrolldate = writerEnrolldate;
	}

	public String getWriterPassYN() {
		return writerPassYN;
	}

	public void setWriterPassYN(String writerPassYN) {
		this.writerPassYN = writerPassYN;
	}

	public String getWriterStatus() {
		return writerStatus;
	}

	public void setWriterStatus(String writerStatus) {
		this.writerStatus = writerStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "WriterEnroll [writerEnrollNo=" + writerEnrollNo + ", memberNo=" + memberNo + ", memberProfileImg="
				+ memberProfileImg + ", memberNickname=" + memberNickname + ", memberEmail=" + memberEmail
				+ ", memberName=" + memberName + ", memberBirthday=" + memberBirthday + ", memberPhone=" + memberPhone
				+ ", writerEnrollQuarter=" + writerEnrollQuarter + ", writerCategory=" + writerCategory
				+ ", writerAddress=" + writerAddress + ", writerReason=" + writerReason
				+ ", writerMajorImgfileOriginal=" + writerMajorImgfileOriginal + ", writerMajorImgfileRenamed="
				+ writerMajorImgfileRenamed + ", writerScheduleYN=" + writerScheduleYN + ", writerContractYN="
				+ writerContractYN + ", writerWishMonth=" + writerWishMonth + ", writerClassName=" + writerClassName
				+ ", writerClassSelectReason=" + writerClassSelectReason + ", writerClassLevel=" + writerClassLevel
				+ ", writerProductTime=" + writerProductTime + ", writerClassKitWarningPoint="
				+ writerClassKitWarningPoint + ", writerClassKitPart=" + writerClassKitPart + ", classImgfileOriginal="
				+ classImgfileOriginal + ", classImgfileRenamed=" + classImgfileRenamed + ", writerPrepRequestYN="
				+ writerPrepRequestYN + ", writerFinalPoint=" + writerFinalPoint + ", writerEnrolldate="
				+ writerEnrolldate + ", writerPassYN=" + writerPassYN + ", writerStatus=" + writerStatus + "]";
	}
	
}