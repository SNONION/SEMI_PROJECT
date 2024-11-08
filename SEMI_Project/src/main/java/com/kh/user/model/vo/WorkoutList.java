package com.kh.user.model.vo;

public class WorkoutList {

    private int UserNo;
	private String workoutTitle;
	private String workoutContent;
	private String workoutDate;
	private int workoutNo;
	
	public WorkoutList() {
		super();
	}
	
	public WorkoutList(int userNo, String workoutTitle, String workoutContent) {
		super();
		UserNo = userNo;
		this.workoutTitle = workoutTitle;
		this.workoutContent = workoutContent;
	}
	
	public WorkoutList(String workoutTitle, String workoutContent, String workoutDate, int workoutNo) {
		super();
		this.workoutTitle = workoutTitle;
		this.workoutContent = workoutContent;
		this.workoutDate = workoutDate;
		this.workoutNo = workoutNo;
	}

	public WorkoutList(String workoutTitle, String workoutContent, String workoutDate) {
		super();
		this.workoutTitle = workoutTitle;
		this.workoutContent = workoutContent;
		this.workoutDate = workoutDate;
	}

	public WorkoutList(int userNo, String workoutTitle, String workoutContent, String workoutDate) {
		super();
		UserNo = userNo;
		this.workoutTitle = workoutTitle;
		this.workoutContent = workoutContent;
		this.workoutDate = workoutDate;
	}

	public int getUserNo() {
		return UserNo;
	}

	public void setUserNo(int userNo) {
		UserNo = userNo;
	}

	public String getWorkoutTitle() {
		return workoutTitle;
	}

	public void setWorkoutTitle(String workoutTitle) {
		this.workoutTitle = workoutTitle;
	}

	public String getWorkoutContent() {
		return workoutContent;
	}

	public void setWorkoutContent(String workoutContent) {
		this.workoutContent = workoutContent;
	}

	public String getWorkoutDate() {
		return workoutDate;
	}

	public void setWorkoutDate(String workoutDate) {
		this.workoutDate = workoutDate;
	}

	public int getWorkoutNo() {
		return workoutNo;
	}

	public void setWorkoutNo(int workoutNo) {
		this.workoutNo = workoutNo;
	}

	@Override
	public String toString() {
		return "WorkoutList [UserNo=" + UserNo + ", workoutTitle=" + workoutTitle + ", workoutContent=" + workoutContent
				+ ", workoutDate=" + workoutDate + ", workoutNo=" + workoutNo + "]";
	}

	
}
