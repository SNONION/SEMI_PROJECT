package com.kh.shop.model.vo;

public class ShopMediaFile {
    
    private int shopFileNo;
	private String fileName;
	private String filePath;
	private String originName;		//	ORIGIN_NAME	VARCHAR2(255 BYTE)
	private String changeName;		//	CHANGE_NAME	VARCHAR2(255 BYTE)
	
	private int RefBno;
	
	public ShopMediaFile() {
		super();
	}

	public ShopMediaFile(int shopFileNo, String fileName, String filePath) {
		super();
		this.shopFileNo = shopFileNo;
		this.fileName = fileName;
		this.filePath = filePath;
	}
		
	
	


	public int getRefBno() {
		return RefBno;
	}

	public void setRefBno(int refBno) {
		RefBno = refBno;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
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
