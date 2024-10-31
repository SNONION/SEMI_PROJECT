package com.kh.user.model.vo;

public class MyItems {
    
    private String proName; // 보여줄때는 상품명을 보여줄 예정
	private int buyerNo;
	
	public MyItems() {
		super();
	}

	public MyItems(String proName, int buyerNo) {
		super();
		this.proName = proName;
		this.buyerNo = buyerNo;
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

	@Override
	public String toString() {
		return "MyItems [proName=" + proName + ", buyerNo=" + buyerNo + "]";
	}
	
}
