package com.kh.request.model.vo;

import java.sql.Date;

public class Request {

    private int requestNo;
	private String requestTitle;
	private String requestContent;
	private String requestWriter; // 작성자 닉네임으로 받아옴
	private Date requestDate;
	private String checkStatus; // 관리자 확인 여부
	private String stauts;
	
	public Request() {
		super();
	}

	public Request(int requestNo, String requestTitle, String requestContent, String requestWriter, Date requestDate,
			String checkStatus, String stauts) {
		super();
		this.requestNo = requestNo;
		this.requestTitle = requestTitle;
		this.requestContent = requestContent;
		this.requestWriter = requestWriter;
		this.requestDate = requestDate;
		this.checkStatus = checkStatus;
		this.stauts = stauts;
	}

	public int getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(int requestNo) {
		this.requestNo = requestNo;
	}

	public String getRequestTitle() {
		return requestTitle;
	}

	public void setRequestTitle(String requestTitle) {
		this.requestTitle = requestTitle;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public String getRequestWriter() {
		return requestWriter;
	}

	public void setRequestWriter(String requestWriter) {
		this.requestWriter = requestWriter;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getStauts() {
		return stauts;
	}

	public void setStauts(String stauts) {
		this.stauts = stauts;
	}

	@Override
	public String toString() {
		return "Request [requestNo=" + requestNo + ", requestTitle=" + requestTitle + ", requestContent="
				+ requestContent + ", requestWriter=" + requestWriter + ", requestDate=" + requestDate
				+ ", checkStatus=" + checkStatus + ", stauts=" + stauts + "]";
	}
	
}
