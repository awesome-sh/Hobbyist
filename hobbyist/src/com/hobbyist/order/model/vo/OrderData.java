package com.hobbyist.order.model.vo;

import java.sql.Timestamp;

public class OrderData {
	
	private int orderDataNo;
	private String orderDataMember;
	private String orderDataClass;
	private Timestamp orderDataDate;
	
	public OrderData() {
		// TODO Auto-generated constructor stub
	}

	public int getOrderDataNo() {
		return orderDataNo;
	}

	public void setOrderDataNo(int orderDataNo) {
		this.orderDataNo = orderDataNo;
	}

	public String getOrderDataMember() {
		return orderDataMember;
	}

	public void setOrderDataMember(String orderDataMember) {
		this.orderDataMember = orderDataMember;
	}

	public String getOrderDataClass() {
		return orderDataClass;
	}

	public void setOrderDataClass(String orderDataClass) {
		this.orderDataClass = orderDataClass;
	}

	public Timestamp getOrderDataDate() {
		return orderDataDate;
	}

	public void setOrderDataDate(Timestamp orderDataDate) {
		this.orderDataDate = orderDataDate;
	}
	
	
	
}
