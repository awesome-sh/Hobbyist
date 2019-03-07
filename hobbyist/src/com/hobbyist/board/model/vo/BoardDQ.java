package com.hobbyist.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BoardDQ implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8276762870252782375L;
	private int boardDQNo;
	private String boardDQTitle;
	private String boardDQWriter;
	private String boardDQContent;
	private String boardDQOriginalFileName;
	private String boardDQReNameFileName;
	private Date boardDQDate;
	private String boardDQProcess;
	private String boardDQAnswer;
	
	public BoardDQ() {}

	public BoardDQ(int boardDQNo, String boardDQTitle, String boardDQWriter, String boardDQContent,
			String boardDQOriginalFileName, String boardDQReNameFileName, Date boardDQDate, String boardDQProcess,
			String boardDQAnswer) {
		super();
		this.boardDQNo = boardDQNo;
		this.boardDQTitle = boardDQTitle;
		this.boardDQWriter = boardDQWriter;
		this.boardDQContent = boardDQContent;
		this.boardDQOriginalFileName = boardDQOriginalFileName;
		this.boardDQReNameFileName = boardDQReNameFileName;
		this.boardDQDate = boardDQDate;
		this.boardDQProcess = boardDQProcess;
		this.boardDQAnswer = boardDQAnswer;
	}

	public int getBoardDQNo() {
		return boardDQNo;
	}

	public void setBoardDQNo(int boardDQNo) {
		this.boardDQNo = boardDQNo;
	}

	public String getBoardDQTitle() {
		return boardDQTitle;
	}

	public void setBoardDQTitle(String boardDQTitle) {
		this.boardDQTitle = boardDQTitle;
	}

	public String getBoardDQWriter() {
		return boardDQWriter;
	}

	public void setBoardDQWriter(String boardDQWriter) {
		this.boardDQWriter = boardDQWriter;
	}

	public String getBoardDQContent() {
		return boardDQContent;
	}

	public void setBoardDQContent(String boardDQContent) {
		this.boardDQContent = boardDQContent;
	}

	public String getBoardDQOriginalFileName() {
		return boardDQOriginalFileName;
	}

	public void setBoardDQOriginalFileName(String boardDQOriginalFileName) {
		this.boardDQOriginalFileName = boardDQOriginalFileName;
	}

	public String getBoardDQReNameFileName() {
		return boardDQReNameFileName;
	}

	public void setBoardDQReNameFileName(String boardDQReNameFileName) {
		this.boardDQReNameFileName = boardDQReNameFileName;
	}

	public Date getBoardDQDate() {
		return boardDQDate;
	}

	public void setBoardDQDate(Date boardDQDate) {
		this.boardDQDate = boardDQDate;
	}

	public String getBoardDQProcess() {
		return boardDQProcess;
	}

	public void setBoardDQProcess(String boardDQProcess) {
		this.boardDQProcess = boardDQProcess;
	}

	public String getBoardDQAnswer() {
		return boardDQAnswer;
	}

	public void setBoardDQAnswer(String boardDQAnswer) {
		this.boardDQAnswer = boardDQAnswer;
	}

	@Override
	public String toString() {
		return "BoardDQ [boardDQNo=" + boardDQNo + ", boardDQTitle=" + boardDQTitle + ", boardDQWriter=" + boardDQWriter
				+ ", boardDQContent=" + boardDQContent + ", boardDQOriginalFileName=" + boardDQOriginalFileName
				+ ", boardDQReNameFileName=" + boardDQReNameFileName + ", boardDQDate=" + boardDQDate
				+ ", boardDQProcess=" + boardDQProcess + ", boardDQAnswer=" + boardDQAnswer + "]";
	}

}
