package com.hobbyist.award.model.vo;

import java.io.Serializable;

public class AwardLike implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7297051087301173817L;
	private int awardNo;
	private String users;
	
	public AwardLike() {}

	public AwardLike(int awardNo, String users) {
		super();
		this.awardNo = awardNo;
		this.users = users;
	}

	public int getAwardNo() {
		return awardNo;
	}

	public void setAwardNo(int awardNo) {
		this.awardNo = awardNo;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AwardLike [awardNo=" + awardNo + ", users=" + users + "]";
	};
	
	
}
