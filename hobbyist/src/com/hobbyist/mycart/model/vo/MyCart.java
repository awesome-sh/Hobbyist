package com.hobbyist.mycart.model.vo;

import java.sql.Timestamp;
import java.util.Date;

public class MyCart {
	
	private int myCartNo;
	private String myCartMember;
	private int myCartClass;
	private Timestamp myCartDate;
	private String myCartCate;
	private String myCartClassOption;
	
	public MyCart() {
	}

	
	
	public MyCart(int myCartNo, String myCartMember, int myCartClass, Timestamp myCartDate, String myCartCate,
			String myCartClassOption) {
		super();
		this.myCartNo = myCartNo;
		this.myCartMember = myCartMember;
		this.myCartClass = myCartClass;
		this.myCartDate = myCartDate;
		this.myCartCate = myCartCate;
		this.myCartClassOption = myCartClassOption;
	}



	public int getMyCartNo() {
		return myCartNo;
	}

	public void setMyCartNo(int myCartNo) {
		this.myCartNo = myCartNo;
	}

	public String getMyCartMember() {
		return myCartMember;
	}

	public void setMyCartMember(String myCartMember) {
		this.myCartMember = myCartMember;
	}

	public int getMyCartClass() {
		return myCartClass;
	}

	public void setMyCartClass(int myCartClass) {
		this.myCartClass = myCartClass;
	}

	public Timestamp getMyCartDate() {
		return myCartDate;
	}

	public void setMyCartDate(Timestamp myCartDate) {
		this.myCartDate = myCartDate;
	}

	public String getMyCartCate() {
		return myCartCate;
	}

	public void setMyCartCate(String myCartCate) {
		this.myCartCate = myCartCate;
	}



	public String getMyCartClassOption() {
		return myCartClassOption;
	}



	public void setMyCartClassOption(String myCartClassOption) {
		this.myCartClassOption = myCartClassOption;
	}
	
	
	
}
