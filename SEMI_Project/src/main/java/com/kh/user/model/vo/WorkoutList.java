package com.kh.user.model.vo;

import java.sql.Date;

public class WorkoutList {

    private int UserNo;
	private String workoutTitle;
	private String workoutContent;
	private Date workoutDate;
	
	public WorkoutList() {
		super();
	}

	public WorkoutList(int userNo, String workoutTitle, String workoutContent, Date workoutDate) {
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

	public Date getWorkoutDate() {
		return workoutDate;
	}

	public void setWorkoutDate(Date workoutDate) {
		this.workoutDate = workoutDate;
	}

	@Override
	public String toString() {
		return "TB_WorkoutList [UserNo=" + UserNo + ", workoutTitle=" + workoutTitle + ", workoutContent="
				+ workoutContent + ", workoutDate=" + workoutDate + "]";
	}
	
}
