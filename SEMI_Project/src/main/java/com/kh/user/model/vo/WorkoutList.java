package com.kh.user.model.vo;

public class WorkoutList {

    private int UserNo;
	private String workoutTitle;
	private String workoutContent;
	private String workoutDate;
	
	public WorkoutList() {
		super();
	}
	
	public WorkoutList(int userNo, String workoutTitle, String workoutContent) {
		super();
		UserNo = userNo;
		this.workoutTitle = workoutTitle;
		this.workoutContent = workoutContent;
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

	@Override
	public String toString() {
		return "TB_WorkoutList [UserNo=" + UserNo + ", workoutTitle=" + workoutTitle + ", workoutContent="
				+ workoutContent + ", workoutDate=" + workoutDate + "]";
	}
	
}
