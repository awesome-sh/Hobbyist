package com.hobbyist.myclass.model.vo;

public class MyClass {
	
	private int myClassNo;
	private String myClassMember;
	private int myClassClass;
	private String myClassProgress;
	private String myClassInfo;
	
	public MyClass() {
	}

	public MyClass(int myClassNo, String myClassMember, int myClassClass, String myClassProgress, String myClassInfo) {
		super();
		this.myClassNo = myClassNo;
		this.myClassMember = myClassMember;
		this.myClassClass = myClassClass;
		this.myClassProgress = myClassProgress;
		this.myClassInfo = myClassInfo;
	}




	public int getMyClassNo() {
		return myClassNo;
	}

	public void setMyClassNo(int myClassNo) {
		this.myClassNo = myClassNo;
	}

	public String getMyClassMember() {
		return myClassMember;
	}

	public void setMyClassMember(String myClassMember) {
		this.myClassMember = myClassMember;
	}

	public int getMyClassClass() {
		return myClassClass;
	}

	public void setMyClassClass(int myClassClass) {
		this.myClassClass = myClassClass;
	}

	public String getMyClassProgress() {
		return myClassProgress;
	}

	public void setMyClassProgress(String myClassProgress) {
		this.myClassProgress = myClassProgress;
	}

	public String getMyClassInfo() {
		return myClassInfo;
	}

	public void setMyClassInfo(String myClassInfo) {
		this.myClassInfo = myClassInfo;
	}
	
	
	
	
	
}
