package com.hobbyist.order.model.vo;

import java.sql.Timestamp;

public class Pin {
	private int pinCode;
	private int pinClass;
	private String pinMember;
	private String pinStatus;
	private Timestamp pinDate;
	
	public Pin() {
		// TODO Auto-generated constructor stub
	}
	
	public Pin(int pinCode, int pinClass, String pinMember, String pinStatus, Timestamp pinDate) {
		super();
		this.pinCode = pinCode;
		this.pinClass = pinClass;
		this.pinMember = pinMember;
		this.pinStatus = pinStatus;
		this.pinDate = pinDate;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public int getPinClass() {
		return pinClass;
	}

	public void setPinClass(int pinClass) {
		this.pinClass = pinClass;
	}

	public String getPinMember() {
		return pinMember;
	}

	public void setPinMember(String pinMember) {
		this.pinMember = pinMember;
	}

	public String getPinStatus() {
		return pinStatus;
	}

	public void setPinStatus(String pinStatus) {
		this.pinStatus = pinStatus;
	}

	public Timestamp getPinDate() {
		return pinDate;
	}

	public void setPinDate(Timestamp pinDate) {
		this.pinDate = pinDate;
	}
	
	
	
}
