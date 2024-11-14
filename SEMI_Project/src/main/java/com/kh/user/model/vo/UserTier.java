package com.kh.user.model.vo;

public class UserTier {
	
	private int tierNo;
	private String tierPath;
	private String tierOriginFileName;
	private String gradeName;
	
	public UserTier() {
		super();
	}

	public UserTier(int tierNo, String tierPath, String tierOriginFileName, String gradeName) {
		super();
		this.tierNo = tierNo;
		this.tierPath = tierPath;
		this.tierOriginFileName = tierOriginFileName;
		this.gradeName = gradeName;
	}

	public int getTierNo() {
		return tierNo;
	}

	public void setTierNo(int tierNo) {
		this.tierNo = tierNo;
	}

	public String getTierPath() {
		return tierPath;
	}

	public void setTierPath(String tierPath) {
		this.tierPath = tierPath;
	}

	public String getTierOriginFileName() {
		return tierOriginFileName;
	}

	public void setTierOriginFileName(String tierOriginFileName) {
		this.tierOriginFileName = tierOriginFileName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	@Override
	public String toString() {
		return "UserTier [tierNo=" + tierNo + ", tierPath=" + tierPath + ", tierOriginFileName=" + tierOriginFileName
				+ ", gradeName=" + gradeName + "]";
	}
	
}
