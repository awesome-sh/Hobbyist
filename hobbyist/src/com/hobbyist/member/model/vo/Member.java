package com.hobbyist.member.model.vo;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1142742834131986102L;
	private int memberNo;
	private String memberEmail;
	private String memberPassword;
	private String memberNickname;
	private String memberName;
	private String memberBirthday;
	private String memberPhone;
	private String memberOriginalImage;
	private String memberRenamedImage;
	private Date memberEnrolldate;
	private String memberGrade;
	private String memberWriterYN;
	private String memberStatus;
	private String memberAddress;
	private String memberMemo;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(int memberNo, String memberEmail, String memberPassword, String memberNickname, String memberName,
			String memberBirthday, String memberPhone, String memberOriginalImage, String memberRenamedImage,
			Date memberEnrolldate, String memberGrade, String memberWriterYN, String memberStatus, String memberAddress,
			String memberMemo) {
		super();
		this.memberNo = memberNo;
		this.memberEmail = memberEmail;
		this.memberPassword = memberPassword;
		this.memberNickname = memberNickname;
		this.memberName = memberName;
		this.memberBirthday = memberBirthday;
		this.memberPhone = memberPhone;
		this.memberOriginalImage = memberOriginalImage;
		this.memberRenamedImage = memberRenamedImage;
		this.memberEnrolldate = memberEnrolldate;
		this.memberGrade = memberGrade;
		this.memberWriterYN = memberWriterYN;
		this.memberStatus = memberStatus;
		this.memberAddress = memberAddress;
		this.memberMemo = memberMemo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
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

	public String getMemberOriginalImage() {
		return memberOriginalImage;
	}

	public void setMemberOriginalImage(String memberOriginalImage) {
		this.memberOriginalImage = memberOriginalImage;
	}

	public String getMemberRenamedImage() {
		return memberRenamedImage;
	}

	public void setMemberRenamedImage(String memberRenamedImage) {
		this.memberRenamedImage = memberRenamedImage;
	}

	public Date getMemberEnrolldate() {
		return memberEnrolldate;
	}

	public void setMemberEnrolldate(Date memberEnrolldate) {
		this.memberEnrolldate = memberEnrolldate;
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	public String getMemberWriterYN() {
		return memberWriterYN;
	}

	public void setMemberWriterYN(String memberWriterYN) {
		this.memberWriterYN = memberWriterYN;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberMemo() {
		return memberMemo;
	}

	public void setMemberMemo(String memberMemo) {
		this.memberMemo = memberMemo;
	}
	
	
	
}