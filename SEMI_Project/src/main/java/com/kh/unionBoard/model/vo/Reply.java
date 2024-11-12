package com.kh.unionBoard.model.vo;

public class Reply {
    
	private int userNo;
    private String nickname; // 회원번호로 회원의 닉네임을 가져옴
	private int refBno;
	private String replyContent;
	private String replyDate;
	private String status;
	
	public Reply() {
		super();
	}
	
	
	
	public Reply(int userNo, int refBno, String replyContent, String replyDate) {
		super();
		this.userNo = userNo;
		this.refBno = refBno;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
	}



	public Reply(String nickname, String replyContent, String replyDate) {
		super();
		this.nickname = nickname;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
	}

	public Reply(int userNo, String nickname, int refBno, String replyContent, String replyDate, String status) {
		super();
		this.userNo = userNo;
		this.nickname = nickname;
		this.refBno = refBno;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
		this.status = status;
	}

	
	public Reply(String nickname, int refBno, String replyContent, String replyDate) {
		super();
		this.nickname = nickname;
		this.refBno = refBno;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
	}



	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
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
		return "Reply [userNo=" + userNo + ", nickname=" + nickname + ", refBno=" + refBno + ", replyContent="
				+ replyContent + ", replyDate=" + replyDate + ", status=" + status + "]";
	}
	
}
