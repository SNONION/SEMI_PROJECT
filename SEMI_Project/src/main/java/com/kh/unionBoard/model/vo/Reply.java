package com.kh.unionBoard.model.vo;

import java.sql.Date;

public class Reply {
    
    private int userNo;
	private int refBno;
	private String replyContent;
	private Date replyDate;
	private String status;
	
	public Reply() {
		super();
	}

	public Reply(int userNo, int refBno, String replyContent, Date replyDate, String status) {
		super();
		this.userNo = userNo;
		this.refBno = refBno;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
		this.status = status;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getRefBno() {
		return refBno;
	}

	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TB_Reply [userNo=" + userNo + ", refBno=" + refBno + ", replyContent=" + replyContent + ", replyDate="
				+ replyDate + ", status=" + status + "]";
	}
	
}
