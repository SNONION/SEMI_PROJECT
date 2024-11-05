package com.kh.unionBoard.model.vo;

import java.sql.Date;

public class MediaFile {
    
    private int fileNo;
	private String fileType; // 확장자를 넣어줄 예정
	private String originFileName;
	private String changeFileName;
	private int refBno;
	private String filePath;
	private Date uploadDate;
	private String status;
	
	public MediaFile() {
		super();
	}

	public MediaFile(int fileNo, String fileType, String originFileName, String changeFileName, int refBno,
			String filePath, Date uploadDate, String status) {
		super();
		this.fileNo = fileNo;
		this.fileType = fileType;
		this.originFileName = originFileName;
		this.changeFileName = changeFileName;
		this.refBno = refBno;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.status = status;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getChangeFileName() {
		return changeFileName;
	}

	public void setChangeFileName(String changeFileName) {
		this.changeFileName = changeFileName;
	}

	public int getRefBno() {
		return refBno;
	}

	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MediaFile [fileNo=" + fileNo + ", fileType=" + fileType + ", originFileName=" + originFileName
				+ ", changeFileName=" + changeFileName + ", refBno=" + refBno + ", filePath=" + filePath
				+ ", uploadDate=" + uploadDate + ", status=" + status + "]";
	}

}
