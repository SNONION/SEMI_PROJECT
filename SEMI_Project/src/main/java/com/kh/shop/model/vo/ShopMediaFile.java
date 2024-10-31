package com.kh.shop.model.vo;

public class ShopMediaFile {
    
    private int shopFileNo;
	private String fileName;
	private String filePath;
	
	public ShopMediaFile() {
		super();
	}

	public ShopMediaFile(int shopFileNo, String fileName, String filePath) {
		super();
		this.shopFileNo = shopFileNo;
		this.fileName = fileName;
		this.filePath = filePath;
	}

	public int getShopFileNo() {
		return shopFileNo;
	}

	public void setShopFileNo(int shopFileNo) {
		this.shopFileNo = shopFileNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "ShopMediaFile [shopFileNo=" + shopFileNo + ", fileName=" + fileName + ", filePath=" + filePath + "]";
	}
	
}
