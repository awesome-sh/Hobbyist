package com.hobbyist.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BoardReport implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1800880018768329226L;
	private int boardNo;
	private String boardMainCategory;
	private String boardWriter;
	private String boardReporter;
	private String boardContent;
	private String boardReportContent;
	private String boardReportCategory;
	private Date boardReportDate;

	public BoardReport () {}

	public BoardReport(int boardNo, String boardMainCategory, String boardWriter, String boardReporter,
			String boardContent, String boardReportContent, String boardReportCategory, Date boardReportDate) {
		super();
		this.boardNo = boardNo;
		this.boardMainCategory = boardMainCategory;
		this.boardWriter = boardWriter;
		this.boardReporter = boardReporter;
		this.boardContent = boardContent;
		this.boardReportContent = boardReportContent;
		this.boardReportCategory = boardReportCategory;
		this.boardReportDate = boardReportDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardMainCategory() {
		return boardMainCategory;
	}

	public void setBoardMainCategory(String boardMainCategory) {
		this.boardMainCategory = boardMainCategory;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardReporter() {
		return boardReporter;
	}

	public void setBoardReporter(String boardReporter) {
		this.boardReporter = boardReporter;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardReportContent() {
		return boardReportContent;
	}

	public void setBoardReportContent(String boardReportContent) {
		this.boardReportContent = boardReportContent;
	}

	public String getBoardReportCategory() {
		return boardReportCategory;
	}

	public void setBoardReportCategory(String boardReportCategory) {
		this.boardReportCategory = boardReportCategory;
	}

	public Date getBoardReportDate() {
		return boardReportDate;
	}

	public void setBoardReportDate(Date boardReportDate) {
		this.boardReportDate = boardReportDate;
	}

	@Override
	public String toString() {
		return "BoardReport [boardNo=" + boardNo + ", boardMainCategory=" + boardMainCategory + ", boardWriter="
				+ boardWriter + ", boardReporter=" + boardReporter + ", boardContent=" + boardContent
				+ ", boardReportContent=" + boardReportContent + ", boardReportCategory=" + boardReportCategory
				+ ", boardReportDate=" + boardReportDate + "]";
	}

}
