package com.kh.user.model.vo;

public class MyItems {
    
	private int proNo;
    private String proName; // 보여줄때는 상품명을 보여줄 예정
	private int buyerNo;
	private int proCount;
	
	public MyItems() {
		super();
	}
	
	public MyItems(int proNo, String proName, int proCount) {
		super();
		this.proNo = proNo;
		this.proName = proName;
		this.proCount = proCount;
	}
	
	public MyItems(String proName, int buyerNo) {
		super();
		this.proName = proName;
		this.buyerNo = buyerNo;
	}

	public int getProNo() {
		return proNo;
	}
	
	public void setProNo(int proNo) {
		this.proNo = proNo;
	}
	
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getBuyerNo() {
		return buyerNo;
	}

	public void setBuyerNo(int buyerNo) {
		this.buyerNo = buyerNo;
	}

	public int getProCount() {
		return proCount;
	}

	public void setProCount(int proCount) {
		this.proCount = proCount;
	}

	@Override
	public String toString() {
		return "MyItems [proNo=" + proNo + ", proName=" + proName + ", buyerNo=" + buyerNo + ", proCount=" + proCount
				+ "]";
	}

}
