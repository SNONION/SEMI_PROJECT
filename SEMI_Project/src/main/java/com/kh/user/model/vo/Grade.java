package com.kh.user.model.vo;

public class Grade {
	
	private int gradeNo;
	private String gradeName;
	private int minPoint;
	private int maxPoint;
	
	public Grade() {
		super();
	}

	public Grade(int gradeNo, String gradeName, int minPoint, int maxPoint) {
		super();
		this.gradeNo = gradeNo;
		this.gradeName = gradeName;
		this.minPoint = minPoint;
		this.maxPoint = maxPoint;
	}

	public int getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public int getMinPoint() {
		return minPoint;
	}

	public void setMinPoint(int minPoint) {
		this.minPoint = minPoint;
	}

	public int getMaxPoint() {
		return maxPoint;
	}

	public void setMaxPoint(int maxPoint) {
		this.maxPoint = maxPoint;
	}

	@Override
	public String toString() {
		return "Grade [gradeNo=" + gradeNo + ", gradeName=" + gradeName + ", minPoint=" + minPoint + ", maxPoint="
				+ maxPoint + "]";
	}
	
}
