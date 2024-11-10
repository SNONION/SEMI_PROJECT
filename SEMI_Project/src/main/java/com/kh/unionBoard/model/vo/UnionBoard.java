package com.kh.unionBoard.model.vo;

import java.sql.Date;

public class UnionBoard {

    private int boardNo;
	private String categoryName;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private int count;
	private String createDate;
	private Date modifyDate;
	private String status;
	private int recommend;
	private int replyCount;
	
	public UnionBoard() {
		super();
	}

	public UnionBoard(int boardNo, String categoryName, String boardTitle, String boardWriter,
			int count, String createDate) {
		super();
		this.boardNo = boardNo;
		this.categoryName = categoryName;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
	}

	public UnionBoard(int boardNo, String categoryName, String boardTitle, String boardContent, String boardWriter,
			int count, String createDate) {
		super();
		this.boardNo = boardNo;
		this.categoryName = categoryName;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
	}

	public UnionBoard(int boardNo, String categoryName, String boardTitle, String boardContent, String boardWriter,
			int count, String createDate, Date modifyDate, String status) {
		super();
		this.boardNo = boardNo;
		this.categoryName = categoryName;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}
	
	public UnionBoard(int boardNo, String categoryName, String boardTitle, String boardWriter, int count, 
			String createDate, int recommend, int replyCount) {
		super();
		this.boardNo = boardNo;
		this.categoryName = categoryName;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
		this.recommend = recommend;
		this.replyCount = replyCount;
	}

	public UnionBoard(int boardNo, String categoryName, String boardTitle, String boardContent, String boardWriter,
			int count, String createDate, Date modifyDate, String status, int recommend, int replyCount) {
		super();
		this.boardNo = boardNo;
		this.categoryName = categoryName;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
		this.recommend = recommend;
		this.replyCount = replyCount;
	}
	
	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
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
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
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
	
	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	@Override
	public String toString() {
		return "UnionBoard [boardNo=" + boardNo + ", categoryName=" + categoryName + ", boardTitle=" + boardTitle
				+ ", boardContent=" + boardContent + ", boardWriter=" + boardWriter + ", count=" + count
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", status=" + status + ", recommend="
				+ recommend + ", replyCount=" + replyCount + "]";
	}

}
