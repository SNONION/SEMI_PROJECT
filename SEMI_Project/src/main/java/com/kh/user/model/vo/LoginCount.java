package com.kh.user.model.vo;

import java.sql.Date;

public class LoginCount {

    private int userNo;
	private int loginCount;
	private int loginEvent; // 연속 로그인용 (7일 연속 14일 연속)
	private Date loginDate;
	
	public LoginCount() {
		super();
	}

	public LoginCount(int userNo, int loginCount, int loginEvent, Date loginDate) {
		super();
		this.userNo = userNo;
		this.loginCount = loginCount;
		this.loginEvent = loginEvent;
		this.loginDate = loginDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getLoginEvent() {
		return loginEvent;
	}

	public void setLoginEvent(int loginEvent) {
		this.loginEvent = loginEvent;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	@Override
	public String toString() {
		return "TB_LoginCount [userNo=" + userNo + ", loginCount=" + loginCount + ", loginEvent=" + loginEvent
				+ ", loginDate=" + loginDate + "]";
	}
	
}
