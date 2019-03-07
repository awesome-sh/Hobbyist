package com.hobbyist.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BoardFAQ implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4548902290888836775L;
	private int boardFAQNo;
	private String boardFAQCategory;
	private String boardFAQTitle;
	private String boardFAQContent;
	private Date boardFAQDate;
	
	public BoardFAQ () {}

	public BoardFAQ(int boardFAQNo, String boardFAQCategory, String boardFAQTitle, String boardFAQContent,
			Date boardFAQDate) {
		super();
		this.boardFAQNo = boardFAQNo;
		this.boardFAQCategory = boardFAQCategory;
		this.boardFAQTitle = boardFAQTitle;
		this.boardFAQContent = boardFAQContent;
		this.boardFAQDate = boardFAQDate;
	}

	public int getBoardFAQNo() {
		return boardFAQNo;
	}

	public void setBoardFAQNo(int boardFAQNo) {
		this.boardFAQNo = boardFAQNo;
	}

	public String getBoardFAQCategory() {
		return boardFAQCategory;
	}

	public void setBoardFAQCategory(String boardFAQCategory) {
		this.boardFAQCategory = boardFAQCategory;
	}

	public String getBoardFAQTitle() {
		return boardFAQTitle;
	}

	public void setBoardFAQTitle(String boardFAQTitle) {
		this.boardFAQTitle = boardFAQTitle;
	}

	public String getBoardFAQContent() {
		return boardFAQContent;
	}

	public void setBoardFAQContent(String boardFAQContent) {
		this.boardFAQContent = boardFAQContent;
	}

	public Date getBoardFAQDate() {
		return boardFAQDate;
	}

	public void setBoardFAQDate(Date boardFAQDate) {
		this.boardFAQDate = boardFAQDate;
	}

	@Override
	public String toString() {
		return "BoardFAQ [boardFAQNo=" + boardFAQNo + ", boardFAQCategory=" + boardFAQCategory + ", boardFAQTitle="
				+ boardFAQTitle + ", boardFAQContent=" + boardFAQContent + ", boardFAQDate=" + boardFAQDate + "]";
	}
	
}
