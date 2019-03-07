package com.hobbyist.board.model.vo;

import java.io.Serializable;

public class BoardLike implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 174957514375432598L;
	private int boardNo;
	private String userId;
	
	public BoardLike() {}

	public BoardLike(int boardNo, String userId) {
		super();
		this.boardNo = boardNo;
		this.userId = userId;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "BoardLike [boardNo=" + boardNo + ", userId=" + userId + "]";
	}

}
