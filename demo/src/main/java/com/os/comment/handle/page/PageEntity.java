package com.os.comment.handle.page;

import java.io.Serializable;

/**
 * @author psh
 * 分页实体
 */
public class PageEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//当前页数
	private int currentPage=1;
	//每页显示的条数
	private int pageSize=12;
	//查询的总数
	private int totalCount;
	//总页数
	private int totalPageSize;
	//////////getter setter
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPageSize() {
		return totalPageSize;
	}
	public void setTotalPageSize(int totalPageSize) {
		this.totalPageSize = totalPageSize;
	}
	public boolean isFirst() {
		return getCurrentPage()<=1;
	}
	public void setFirst(boolean first) {
	}
	public boolean isLast() {
		return getCurrentPage() >= getTotalPageSize();
	}
	public void setLast(boolean last) {
	}
	
	
}
