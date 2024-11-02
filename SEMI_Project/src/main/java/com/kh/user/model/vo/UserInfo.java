package com.kh.user.model.vo;

import java.sql.Date;

public class UserInfo {
    
    private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String nickname;
	private String gender;
	private String phone;
	private String email;
	private String address;
	private String gradeName; // 받아서 출력할때는 등급명으로 표시할예정 (조인구문)
	private int point;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	
	public UserInfo() {
		super();
	}

	public UserInfo(int userNo, String userId, String userName, String nickname, String gender, String phone,
			String email, String address, String gradeName, int point, Date enrollDate, Date modifyDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.nickname = nickname;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.gradeName = gradeName;
		this.point = point;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
	}

	public UserInfo(int userNo, String userId, String userPwd, String userName, String nickname, String gender,
			String phone, String email, String address, String gradeName, int point, Date enrollDate, Date modifyDate,
			String status) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.nickname = nickname;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.gradeName = gradeName;
		this.point = point;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
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
		return "TB_UserInfo [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName="
				+ userName + ", nickname=" + nickname + ", gender=" + gender + ", phone=" + phone + ", email=" + email
				+ ", address=" + address + ", gradeName=" + gradeName + ", point=" + point + ", enrollDate="
				+ enrollDate + ", modifyDate=" + modifyDate + ", status=" + status + "]";
	}
	
}
