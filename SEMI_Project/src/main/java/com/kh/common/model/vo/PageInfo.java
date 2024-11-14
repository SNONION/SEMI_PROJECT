package com.kh.common.model.vo;

import java.util.ArrayList;

import com.kh.unionBoard.model.vo.Reply;

public class PageInfo {

	private int wListCount;
	private int currentPage;
	private int pageLimit;
	private int wListLimit;
	private int maxPage;
	private int startPage;
	private int endPage;
	
	public PageInfo() {
		super();
	}


	

	public PageInfo(int currentPage, int pageLimit, int wListLimit, ArrayList<Reply> reListCount, int maxPage,
			int startPage, int endPage) {
		super();
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.wListLimit = wListLimit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public PageInfo(int wListCount, int currentPage, int pageLimit, int wListLimit, int maxPage, int startPage,
			int endPage) {
		super();
		this.wListCount = wListCount;
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.wListLimit = wListLimit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public int getwListCount() {
		return wListCount;
	}

	public void setwListCount(int wListCount) {
		this.wListCount = wListCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getwListLimit() {
		return wListLimit;
	}

	public void setwListLimit(int wListLimit) {
		this.wListLimit = wListLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PageInfo [wListCount=" + wListCount + ", currentPage=" + currentPage + ", pageLimit=" + pageLimit
				+ ", wListLimit=" + wListLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + "]";
	}

}
