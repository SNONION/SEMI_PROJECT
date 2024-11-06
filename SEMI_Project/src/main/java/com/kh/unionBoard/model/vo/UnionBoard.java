package com.kh.unionBoard.model.vo;

import java.sql.Date;

public class UnionBoard{

    private int boardNo;
	private String categoryName;
	private String BoardTitle;
	private String BoardContent;
	private String BoardWriter;
	private int count;
	private Date createDate;
	private Date modifyDate;
	private String status;
	
	public UnionBoard() {
		super();
	}

	public UnionBoard(int boardNo, String categoryName, String boardTitle, String boardContent, int count,
			Date createDate) {
		super();
		this.boardNo = boardNo;
		this.categoryName = categoryName;
		BoardTitle = boardTitle;
		BoardContent = boardContent;
		this.count = count;
		this.createDate = createDate;
	}


	

	public UnionBoard(int boardNo, String categoryName, String boardTitle, String boardContent, String boardWriter,
			int count, Date createDate, Date modifyDate, String status) {
		super();
		this.boardNo = boardNo;
		this.categoryName = categoryName;
		BoardTitle = boardTitle;
		BoardContent = boardContent;
		BoardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBoardTitle() {
		return BoardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		BoardTitle = boardTitle;
	}

	public String getBoardContent() {
		return BoardContent;
	}

	public void setBoardContent(String boardContent) {
		BoardContent = boardContent;
	}

	public String getBoardWriter() {
		return BoardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		BoardWriter = boardWriter;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TB_UnionBoard [boardNo=" + boardNo + ", categoryName=" + categoryName + ", BoardTitle=" + BoardTitle
				+ ", BoardContent=" + BoardContent + ", BoardWriter=" + BoardWriter + ", count=" + count
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", status=" + status + "]";
	}
	
}
