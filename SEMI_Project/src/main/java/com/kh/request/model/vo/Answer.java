package com.kh.request.model.vo;

import java.sql.Date;

public class Answer {
     
     private int requestNo;
	 private String answerContent;
	 private String answerWriter; // 관리자명을 보여줄 예정
	 private Date answerDate;
	 
	public Answer() {
		super();
	}

	public Answer(String answerContent, String answerWriter, Date answerDate) {
		super();
		this.answerContent = answerContent;
		this.answerWriter = answerWriter;
		this.answerDate = answerDate;
	}

	public Answer(int requestNo, String answerContent, String answerWriter, Date answerDate) {
		super();
		this.requestNo = requestNo;
		this.answerContent = answerContent;
		this.answerWriter = answerWriter;
		this.answerDate = answerDate;
	}

	public int getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(int requestNo) {
		this.requestNo = requestNo;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public String getAnswerWriter() {
		return answerWriter;
	}

	public void setAnswerWriter(String answerWriter) {
		this.answerWriter = answerWriter;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	@Override
	public String toString() {
		return "Answer [requestNo=" + requestNo + ", answerContent=" + answerContent + ", answerWriter=" + answerWriter
				+ ", answerDate=" + answerDate + "]";
	}
	 
}
