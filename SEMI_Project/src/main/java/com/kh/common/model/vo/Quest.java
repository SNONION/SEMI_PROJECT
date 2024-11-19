package com.kh.common.model.vo;

public class Quest {

    private int questNo;
	private int refBno;
	private String questTitle;
	private int questContent;
	private int reward;
	private String status;
	
	public Quest() {
		super();
	}
	
	public Quest(int questNo, String questTitle, int questContent, int reward) {
		super();
		this.questNo = questNo;
		this.questTitle = questTitle;
		this.questContent = questContent;
		this.reward = reward;
	}

	public Quest(int questNo, int refBno, String questTitle, int questContent, int reward, String status) {
		super();
		this.questNo = questNo;
		this.refBno = refBno;
		this.questTitle = questTitle;
		this.questContent = questContent;
		this.reward = reward;
		this.status = status;
	}

	public int getQuestNo() {
		return questNo;
	}

	public void setQuestNo(int questNo) {
		this.questNo = questNo;
	}

	public int getRefBno() {
		return refBno;
	}

	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}

	public String getQuestTitle() {
		return questTitle;
	}

	public void setQuestTitle(String questTitle) {
		this.questTitle = questTitle;
	}

	public int getQuestContent() {
		return questContent;
	}

	public void setQuestContent(int questContent) {
		this.questContent = questContent;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TB_Quest [questNo=" + questNo + ", refBno=" + refBno + ", questTitle=" + questTitle + ", questContent="
				+ questContent + ", reward=" + reward + ", status=" + status + "]";
	}
	
}
