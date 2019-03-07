package com.hobbyist.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6185882718682598149L;
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private String boardOriginalFileName;
	private String boardReNamedFileName;
	private Date boardDate;
	private int boardReadCount;
	private String status;
	private int boardLike;
	private int boardCommentCount;
	
	public Board() {}

	public Board(int boardNo, String boardTitle, String boardWriter, String boardContent, String boardOriginalFileName,
			String boardReNamedFileName, Date boardDate, int boardReadCount, String status, int boardLike,
			int boardCommentCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.boardOriginalFileName = boardOriginalFileName;
		this.boardReNamedFileName = boardReNamedFileName;
		this.boardDate = boardDate;
		this.boardReadCount = boardReadCount;
		this.status = status;
		this.boardLike = boardLike;
		this.boardCommentCount = boardCommentCount;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardOriginalFileName() {
		return boardOriginalFileName;
	}

	public void setBoardOriginalFileName(String boardOriginalFileName) {
		this.boardOriginalFileName = boardOriginalFileName;
	}

	public String getBoardReNamedFileName() {
		return boardReNamedFileName;
	}

	public void setBoardReNamedFileName(String boardReNamedFileName) {
		this.boardReNamedFileName = boardReNamedFileName;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public int getBoardReadCount() {
		return boardReadCount;
	}

	public void setBoardReadCount(int boardReadCount) {
		this.boardReadCount = boardReadCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBoardLike() {
		return boardLike;
	}

	public void setBoardLike(int boardLike) {
		this.boardLike = boardLike;
	}

	public int getBoardCommentCount() {
		return boardCommentCount;
	}

	public void setBoardCommentCount(int boardCommentCount) {
		this.boardCommentCount = boardCommentCount;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardContent=" + boardContent + ", boardOriginalFileName=" + boardOriginalFileName
				+ ", boardReNamedFileName=" + boardReNamedFileName + ", boardDate=" + boardDate + ", boardReadCount="
				+ boardReadCount + ", status=" + status + ", boardLike=" + boardLike + ", boardCommentCount="
				+ boardCommentCount + "]";
	}


}
