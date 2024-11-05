package com.kh.common.model.vo;

import java.sql.Date;

public class UserAD {
    
    private int adNo;
	private int buyerNo;
	private int fileNo;
	private Date purchDate;
	private String status;
	
	public UserAD() {
		super();
	}

	public UserAD(int adNo, int buyerNo, int fileNo, Date purchDate, String status) {
		super();
		this.adNo = adNo;
		this.buyerNo = buyerNo;
		this.fileNo = fileNo;
		this.purchDate = purchDate;
		this.status = status;
	}

	public int getAdNo() {
		return adNo;
	}

	public void setAdNo(int adNo) {
		this.adNo = adNo;
	}

	public int getBuyerNo() {
		return buyerNo;
	}

	public void setBuyerNo(int buyerNo) {
		this.buyerNo = buyerNo;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public Date getPurchDate() {
		return purchDate;
	}

	public void setPurchDate(Date purchDate) {
		this.purchDate = purchDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserAD [adNo=" + adNo + ", buyerNo=" + buyerNo + ", fileNo=" + fileNo + ", purchDate=" + purchDate
				+ ", status=" + status + "]";
	}
	
}
